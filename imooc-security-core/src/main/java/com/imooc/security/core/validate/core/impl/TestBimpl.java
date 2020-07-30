package com.imooc.security.core.validate.core.impl;

import org.springframework.stereotype.Component;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/6 23:26
 * @description：
 */
@Component("testBimpl")
public class TestBimpl extends AbstractTestImpl{
    @Override
    public void run() {
        System.out.println("TestBimpl");
    }
}