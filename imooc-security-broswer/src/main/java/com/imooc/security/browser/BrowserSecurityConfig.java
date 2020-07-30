package com.imooc.security.browser;

import com.imooc.security.core.authentication.FormAuthenticationConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.core.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 15:47
 * @description：
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    // 定义用户加密 对象。 springnboot2以后 必须需要加密才能访问
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
       // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfig.configure(http);
            http.apply(validateCodeSecurityConfig)
                    .and()
                    .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                    .authorizeRequests()
                    .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                            SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                            securityProperties.getBrowser().getLoginPage(), //定义登陆页面
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .csrf().disable();


        ///-----------------------未重构写法--------------//
       /* ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setFailureHandler(failureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setFailureHandler(failureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();*/

        /*http.addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/imooc-signIn.html","/authentication/require","/code/*",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);*/

    }
}