package com.mentaldoctor.mentaldoctor.service.impl;

import com.mentaldoctor.mentaldoctor.dao.UserDao;
import com.mentaldoctor.mentaldoctor.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }
}
