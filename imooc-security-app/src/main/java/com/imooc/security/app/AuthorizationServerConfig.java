package com.imooc.security.app;

import com.imooc.security.core.properties.OAuth2ClientProperties;
import com.imooc.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/12 18:00
 * @description：
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    //配置客户端信息服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // clients.withClientDetails(clientDetailsService);
        // 动态加载客户端配置信息--加载多个第三方客户端
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if(ArrayUtils.isNotEmpty(securityProperties.getAuth().getClients())){
            for(OAuth2ClientProperties config : securityProperties.getAuth().getClients()){
                builder.withClient(config.getClientId())
                        .secret(new BCryptPasswordEncoder().encode(config.getClientSecret()))
                        .accessTokenValiditySeconds(config.getAccessTokenValidateSeconds())
                        .authorizedGrantTypes("authorization_code", "password","client_credentials","refresh_token")// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
                        .scopes("all")// 允许的授权范围
                        .autoApprove(false)  // 跳转到授权页面
                        .redirectUris("http://www.baidu.com"); //加上验证回调地址
            }
        }
       /* clients.inMemory()// 内存存储
                .withClient("imooc")// client_id
                .secret(new BCryptPasswordEncoder().encode("secret")) //这里springboot2.0之后 secret必须是加密后才能使用
                .accessTokenValiditySeconds(7200)  // 令牌的有效期
                .authorizedGrantTypes("authorization_code", "password","client_credentials","refresh_token")// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("all")// 允许的授权范围
                .autoApprove(false)  // 跳转到授权页面
                .redirectUris("http://www.baidu.com"); //加上验证回调地址*/
    }

    // 令牌访问管理服务
    @Bean(name = "authorizationServerTokenServices")
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices service = new DefaultTokenServices();
        service.setSupportRefreshToken(true);  // 是否支持刷新令牌

        // 令牌增强：：通过增强器TokenEnhance来改变成Jwt令牌方式
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter,jwtTokenEnhancer));
        service.setTokenEnhancer(enhancerChain);

        service.setClientDetailsService(clientDetailsService);
        service.setTokenStore(tokenStore); // 令牌存储策略
        service.setRefreshTokenValiditySeconds(7200); //令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); //刷新令牌默认有效期3天
        return service;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) // 密码管理需要
                .authorizationCodeServices(authorizationCodeServices)  // 授权码管理需要 并且一个授权码只能使用一次
                .tokenServices(tokenServices())  // 令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
        .userDetailsService(userDetailsService);
        if(jwtAccessTokenConverter != null){
            endpoints.accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    // 令牌访问端点的安全约束配置
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") // /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话
                .checkTokenAccess("permitAll()") ///oauth/check_token：用于资源服务访问的令牌解析端点 这里公开
                .allowFormAuthenticationForClients();  // 允许表单认证
    }
}