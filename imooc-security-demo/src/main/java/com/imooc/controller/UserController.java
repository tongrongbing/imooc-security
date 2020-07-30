package com.imooc.controller;

import com.imooc.dto.User;
import com.imooc.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 15:31
 * @description：
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SecurityProperties properties;
    @GetMapping("/list")
    public List<User> query(){
        List<User> userList = Arrays.asList(new User("tong","123456"));
        return userList;
    }


    @GetMapping("/context")
    public Object getSecurityContext(Authentication authentication, HttpServletRequest request) throws Exception{
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "Bearer ");
        Claims claims = Jwts.parser().setSigningKey(properties.getAuth().getJwtSigningKey().getBytes("UTF-8")).parseClaimsJws(token).getBody();
        System.out.println("======================================"+claims.get("wiki"));
        return authentication;
    }

    @GetMapping("/me")
    public Object getUserInfo(Authentication authentication){
        return authentication;
    }
}