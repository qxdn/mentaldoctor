package com.mentaldoctor.mentaldoctor.controller;

import com.mentaldoctor.mentaldoctor.model.entity.User;
import com.mentaldoctor.mentaldoctor.service.api.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "用户登陆注册接口")
public class LoginController {

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "登陆",notes = "根据用户名密码登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户名",required = true),
            @ApiImplicitParam(paramType = "query",name = "password",value = "密码",required = true)
    })
    @PostMapping("/login")
    public String Login(@RequestParam("username") String username,@RequestParam("password") String password){
        String token=authService.login(username,password);
        return token;
    }

    @ApiOperation(value = "注册",notes = "用户注册")
    @ApiImplicitParam(paramType ="body")
    @PostMapping("/register")
    public User register(@RequestBody  @Validated User user){
        //TODO:有待修改
        return authService.register(user);
    }
}
