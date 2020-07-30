package com.imooc.security.core.validate.core;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/5 0:13
 * @description：校验码生成器
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);

}
