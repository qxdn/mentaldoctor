package com.mentaldoctor.mentaldoctor.daotest;

import com.mentaldoctor.mentaldoctor.dao.UserDao;
import com.mentaldoctor.mentaldoctor.model.entity.Role;
import com.mentaldoctor.mentaldoctor.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserDaoTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void insertTest(){
        User user=new User();
        List<Role> roles=new ArrayList<>();
        Role role=new Role();
        role.setName("ROLE_admin");
        role.setDescription("管理员");
        roles.add(role);
        Role role2=new Role();
        role2.setName("ROLE_normal");
        role2.setDescription("普通用户");
        roles.add(role2);
        user.setUsername("qxdn");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRoles(roles);
        user.setEmail("1464238196@qq.com");
        user.setCreateTime(new Date());
        userDao.save(user);

    }

    @Test
    void deleteUser(){
        userDao.deleteById((long) 1);
    }
}
