package com.imooc.security.core.config;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 17:33
 * @description：
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityPropertyConfig {
}