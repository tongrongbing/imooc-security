package com.imooc.security.core.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/5 12:29
 * @description：
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) throws Exception {
        log.info("请配置真实的短信验证码发送器(SmsCodeSender)");
        log.info("向手机"+mobile+"发送短信验证码"+code);
    }
}