package com.imooc.security.core.properties;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 23:33
 * @description：
 */
public class SmsCodeProperties {

    private int length = 6;

    private int expiredIn = 60;

    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(int expiredIn) {
        this.expiredIn = expiredIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}