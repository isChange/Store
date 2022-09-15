package com.cy.store.mapper;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUserMapper {
    @Autowired
    UserMapper userMapper;
    @Disabled("测试注册持久层")
    @Test
    public void test1(){
        User user = new User();
        user.setUsername("陈亮");
        user.setPassword("123456");

        Integer integer = userMapper.insertUser(user);
        User userByUsername = userMapper.findUserByUsername(user.getUsername());
        System.out.println("影响行数==》" + integer);
        System.out.println("查询到的记录==>>" + userByUsername);
    }

    @Test
    public void test2(){
        User userByUid = userMapper.findUserByUid(2);
        System.out.println(userByUid);

        Integer integer = userMapper.updateUserForPassword(userByUid.getUid(), "123",
                userByUid.getModifiedUser(), userByUid.getModifiedTime());
        System.out.println(integer);
    }
    @Test
    public void test3(){
        userMapper.updateUserForPersonResource(3,"1234567890","666@qq.com",
                                            1,"刘",new Date());
    }
    @Test
    public void test4(){
        userMapper.updateUserAvatar("/upload/123.jpg","jack" ,new Date(),3);
    }
}
