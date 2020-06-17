package com.mentaldoctor.mentaldoctor.controller;

import com.mentaldoctor.mentaldoctor.model.dto.RespBean;
import com.mentaldoctor.mentaldoctor.model.entity.User;
import com.mentaldoctor.mentaldoctor.service.api.AuthService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public RespBean Login(@RequestParam("username") String username, @RequestParam("password") String password){
        String token=authService.login(username,password);
        return RespBean.ok("登陆成功",token);
    }

    @ApiOperation(value = "注册",notes = "用户注册")
    @ApiImplicitParam(paramType ="body")
    @PostMapping("/register")
    public User register(@RequestBody  @Validated User user){
        //TODO:有待修改
        return authService.register(user);
    }

    @ApiOperation(value = "是否登录")
    @RequestMapping("/isLogin")
    public RespBean isLogin(){
        User user=authService.isLogin();
        RespBean respBean;
        if(user==null){
           respBean = RespBean.error(null);
        }else {
            respBean = RespBean.ok("",user);
        }
        return respBean;
    }
}
