package com.mentaldoctor.mentaldoctor.service.impl;

import com.mentaldoctor.mentaldoctor.dao.UserDao;
import com.mentaldoctor.mentaldoctor.model.entity.User;
import com.mentaldoctor.mentaldoctor.service.api.AuthService;
import com.mentaldoctor.mentaldoctor.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public User register(User user) {
        if(isUserExisted(user.getUsername())){
            //TODO: 考虑换exception
            return null;
        }
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //用户生成时间
        user.setCreateTime(new Date());
        return userDao.save(user);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        String token= JwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public boolean isUserExisted(String username) {
        boolean existed=false;
        User user=userDao.findByUsername(username);
        if(user!=null){
            existed=true;
        }
        return existed;
    }
}