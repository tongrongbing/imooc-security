package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 17:29
 * @description：
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    private OAuth2Properties auth = new OAuth2Properties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }

    public OAuth2Properties getAuth() {
        return auth;
    }

    public void setAuth(OAuth2Properties auth) {
        this.auth = auth;
    }
}