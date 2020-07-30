package com.imooc.security.core.properties;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/14 23:21
 * @description：
 */
public class OAuth2Properties {

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

    private String jwtSigningKey = "imooc";

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}