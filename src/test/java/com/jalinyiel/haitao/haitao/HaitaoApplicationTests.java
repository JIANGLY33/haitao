package com.jalinyiel.haitao.haitao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalinyiel.haitao.haitao.mapper.UserMapper;
import com.jalinyiel.haitao.haitao.model.domain.ActivityItem;
import com.jalinyiel.haitao.haitao.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
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
        user.setUserName("admin2");
        user.setPassword("admin2");
        user.setType((byte) 0);
        userMapper.createUser(user);
        Long newUser = user.getId();
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

    @Test
    void serializableTest() {
        ActivityItem a1 = new ActivityItem();
        a1.setActivityId(1L);
        a1.setItemId(1L);
        ActivityItem a2 = new ActivityItem();
        a2.setActivityId(2L);
        a2.setItemId(2L);
        List a = Arrays.asList(a1,a2);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String result = objectMapper.writeValueAsString(a);
            System.out.println(result);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, ActivityItem.class);
            List<ActivityItem> activityItems = objectMapper.readValue(result,javaType);
            activityItems.stream().forEach(activityItem -> System.out.println(activityItem));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
