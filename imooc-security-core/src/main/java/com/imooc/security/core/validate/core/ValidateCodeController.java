package com.imooc.security.core.validate.core;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.validate.core.impl.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 22:22
 * @description：
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private Map<String, TestInterface> inters;

    /**
     * @author: tongrongbing
     * @description:  创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
     * @time: 2020/7/5 16:31
     * @param request
     * @param response
     * @param type
     * @return void
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request,
                           HttpServletResponse response,
                           @PathVariable String type) throws Exception{
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

}