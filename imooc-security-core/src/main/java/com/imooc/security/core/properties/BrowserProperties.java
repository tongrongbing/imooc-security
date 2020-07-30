package com.imooc.security.core.properties;

import com.imooc.security.core.enums.LoginResponseType;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 17:29
 * @description：
 */
public class BrowserProperties {

    private String loginPage = "/imooc-signIn.html";

    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginResponseType() {
        return loginResponseType;
    }

    public void setLoginResponseType(LoginResponseType loginResponseType) {
        this.loginResponseType = loginResponseType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}