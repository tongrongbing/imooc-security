package com.imooc.security.core.validate.core.impl;

import org.springframework.stereotype.Component;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/6 23:25
 * @description：
 */
@Component("testAimpl")
public class TestAimpl extends AbstractTestImpl{
    @Override
    public void run() {
        System.out.println("TestAimpl");
    }
}