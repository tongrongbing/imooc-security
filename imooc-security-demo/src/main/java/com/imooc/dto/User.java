package com.imooc.dto;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 15:33
 * @description：
 */
public class User {
    private String name;

    private String password;

    public User() {
       
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}