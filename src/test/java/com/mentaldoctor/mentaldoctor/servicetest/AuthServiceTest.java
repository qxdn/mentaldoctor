package com.mentaldoctor.mentaldoctor.servicetest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mentaldoctor.mentaldoctor.service.api.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @ParameterizedTest
    @CsvSource({"qxdn,true","normal,false"})
    public void userExistedTest(String username,boolean isExisted){
        boolean exist=authService.isUserExisted(username);
        assertTrue(exist==isExisted);
    }

    @ParameterizedTest
    @CsvSource({"qxdn,123456","normal,123456"})
    public void loginTest(String username,String password){
        String token=authService.login(username, password);
        log.debug(username+" with token: "+token);
        System.out.println(token);
    }

}
