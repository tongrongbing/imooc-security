package com.imooc.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.enums.LoginResponseType;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.support.ApiResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 18:46
 * @description：
 */

@Slf4j
@Component("failureHandler")
public class BrowserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties properties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {


        log.info("登录失败");
        if (LoginResponseType.JSON.equals(properties.getBrowser().getLoginResponseType())){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new ApiResponseUtil(null,401,exception.getMessage())));
        }else {
            // 不是json 则继续跳转
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}