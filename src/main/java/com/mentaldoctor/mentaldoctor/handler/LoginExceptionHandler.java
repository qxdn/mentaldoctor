package com.mentaldoctor.mentaldoctor.handler;

import com.mentaldoctor.mentaldoctor.model.dto.RespBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public RespBean authenticationExceptionHandler(AuthenticationException exception){
        RespBean respBean=RespBean.error("登陆失败");
        if(exception instanceof BadCredentialsException){
            respBean.setObj("用户名或者密码错误");
        }else if(exception instanceof UsernameNotFoundException){
            respBean.setObj("用户名不存在");
        }else if(exception instanceof DisabledException){
            respBean.setObj("账号被禁用");
        }

        return respBean;
    }
}
