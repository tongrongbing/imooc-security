package com.imooc.security.core.sms;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/5 12:29
 * @description：
 */
public interface SmsCodeSender {
    void send(String mobile, String code) throws Exception;
}
