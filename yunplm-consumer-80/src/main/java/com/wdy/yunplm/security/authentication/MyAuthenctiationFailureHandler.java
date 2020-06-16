package com.wdy.yunplm.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdy.yunplm.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登陆失败");

        if ("json".equals("json")) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            String msg = "登录失败!";
            if(exception instanceof LockedException){
                msg = "账户被锁定，登录失败!";
            }else if(exception instanceof BadCredentialsException){
                msg = "账户名或密码输入错误，登录失败!";
            }else if(exception instanceof DisabledException){
                msg = "账户被禁用，登录失败!";
            }else if(exception instanceof AccountExpiredException){
                msg = "账户已过期，登录失败!";
            }else if(exception instanceof CredentialsExpiredException){
                msg = "密码已过期，登录失败!";
            }
            response.getWriter().write(objectMapper.writeValueAsString(Result.failure(msg)));
        }else{
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
