package com.imooc.security.core.validate.core;

import com.imooc.security.core.image.ImageCodeGenerator;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.sms.DefaultSmsCodeSender;
import com.imooc.security.core.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/5 0:26
 * @description：
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }

}