package com.imooc.security.core.sms;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.core.ValidateCode;
import com.imooc.security.core.validate.core.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/5 0:16
 * @description： 短信验证码生成器
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.random(securityProperties.getValidateCode().getSmsCode().getLength());
        return new ValidateCode("123456",securityProperties.getValidateCode().getSmsCode().getExpiredIn());
    }

    public SecurityProperties getProperties() {
        return securityProperties;
    }

    public void setProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}