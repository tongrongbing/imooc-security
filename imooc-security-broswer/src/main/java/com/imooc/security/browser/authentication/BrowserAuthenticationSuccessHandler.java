package com.imooc.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.enums.LoginResponseType;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.support.ApiResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 18:25
 * @description：
 */
@Component("successHandler")
@Slf4j
public class BrowserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties properties;
    /**
     * @author: tongrongbing
     * @description:        Authentication:封装认证信息 包括用户信息等
     * @time: 2020/7/4 18:26
     * @param request
     * @param response
     * @param authentication
     * @return void
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
         log.info("登陆成功...........");
         if(LoginResponseType.JSON.equals(properties.getBrowser().getLoginResponseType())){
             response.setContentType("application/json");
             String type = authentication.getClass().getName();
             response.getWriter().write(objectMapper.writeValueAsString(new ApiResponseUtil(authentication,200,type)));
         }else {
             // 不是json 则继续跳转
             super.onAuthenticationSuccess(request, response, authentication);
         }
    }
}