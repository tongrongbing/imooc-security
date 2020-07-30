package com.imooc.security.core.validate.core;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/5 15:32
 * @description：校验码处理器，封装不同的处理方式的逻辑
 */
public interface ValidateCodeProcessor {

    /**
     * @author: tongrongbing
     * @description: 创建校验码
     * @time: 2020/7/5 15:35
     * @param ServletWebRequest 封装请求和响应对象
     * @return void
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * @author: tongrongbing
     * @description: 校验验证码
     * @time: 2020/7/5 22:08
     * @param servletWebRequest
     * @return void
     */
    void validate(ServletWebRequest servletWebRequest);

}
