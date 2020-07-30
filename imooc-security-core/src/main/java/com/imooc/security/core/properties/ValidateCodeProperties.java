package com.imooc.security.core.properties;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 23:35
 * @description：
 */
public class ValidateCodeProperties {

    private ImageCodeProperties imageCode = new ImageCodeProperties();

    private SmsCodeProperties smsCode = new SmsCodeProperties();

    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

    public SmsCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SmsCodeProperties smsCode) {
        this.smsCode = smsCode;
    }
}