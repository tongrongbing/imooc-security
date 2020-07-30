package com.imooc.security.core.validate.core;

import org.springframework.security.core.AuthenticationException;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 22:46
 * @description：
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}