package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.serviceImpl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUserService {
    @Autowired
    UserServiceImpl userService;

    @Test
    public void test1(){
        User user = new User();
        user.setUsername("jack");
        user.setPassword("123456");
        userService.insert(user);
    }
    @DisplayName(value = "登入测试")
    @Test
    public void test2(){
        User tom = userService.login("tom", "123456");
        System.out.println(tom);
    }

    @Test
    public void test3(){
        userService.updateForPassword(3,"陈景","123","123456");

    }
    @Test
    public void  test4(){
        User userByUid = userService.findUserByUid(3);
        System.out.println(userByUid);
    }
    @Test
    public void test5(){
        User user = new User();
        user.setEmail("1933@qq.com");
        user.setPhone("10086");
        user.setGender(0);
        userService.updateForUserResource(3,"陈",user);
    }
    @Test
    public void test6(){

        userService.updateUserAvatar(3,"/upload/11111.jpg","小老赋");
    }

}
