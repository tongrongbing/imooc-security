package com.imooc.security.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/12 20:29
 * @description：
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() .authorizeRequests() .antMatchers("/r/r1").hasAnyAuthority("p1")
                .antMatchers("/login/*").permitAll() .anyRequest().authenticated()
                .and().formLogin();
    }
}