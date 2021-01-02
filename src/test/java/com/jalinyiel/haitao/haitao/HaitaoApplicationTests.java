package com.jalinyiel.haitao.haitao;

import com.jalinyiel.haitao.haitao.mapper.UserMapper;
import com.jalinyiel.haitao.haitao.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HaitaoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.findAll();
        for(User user : users) System.out.println(user);
    }

    @Test
    void insertUserTest() {
        User user = new User();
        user.setUserName("root");
        user.setPassword("root");
        user.setType((byte) 0);
        Long newUser = userMapper.createUser(user);
        System.out.println(newUser);
    }

    @Test
    void updateUserTest() {
        User user = new User();
        user.setId(1L);
        user.setGender((byte) 0);
        user.setDescription("happy new year!");
        Integer res = userMapper.updateUser(user);
        System.out.println(res);
    }

}
