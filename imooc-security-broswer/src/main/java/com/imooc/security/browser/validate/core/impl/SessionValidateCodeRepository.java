package com.imooc.security.browser.validate.core.impl;

import com.imooc.security.core.validate.core.ValidateCode;
import com.imooc.security.core.validate.core.ValidateCodeRepository;
import com.imooc.security.core.validate.core.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/5 22:13
 * @description： 基于session的验证码存取器 浏览器端是基于session管理的
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {


    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), code);
    }

    /**
     * @author: tongrongbing
     * @description: 构建验证码放入session时的key
     * @time: 2020/7/5 22:15
     * @param request
     * @param validateCodeType
     * @return java.lang.String
     */
    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, codeType));
    }
}