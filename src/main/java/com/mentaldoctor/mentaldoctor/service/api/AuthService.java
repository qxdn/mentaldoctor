package com.mentaldoctor.mentaldoctor.service.api;

import com.mentaldoctor.mentaldoctor.model.entity.User;

public interface AuthService {

    /**
     * 注册
     * @param user
     * @return
     */
    public User register(User user);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    public String login(String username,String password);

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    public boolean isUserExisted(String username);

    public User isLogin();
}
