package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.common.UserUtil;
import com.jalinyiel.haitao.haitao.mapper.UserMapper;
import com.jalinyiel.haitao.haitao.model.domain.User;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.LogInVo;
import com.jalinyiel.haitao.haitao.model.vo.UserInfoVo;
import com.jalinyiel.haitao.haitao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean logIn(LogInVo logInVo) throws DaoException{
        User user = userMapper.findByUsername(logInVo.getUsername());
        if (null == user)
            throw new DaoException("failed to find user.");
        if (user.getUserName().equals(logInVo.getUsername()) && user.getPassword().equals(logInVo.getPassword())) {
            return true;
        }
        return false;

    }

    @Override
    public User register(LogInVo logInVo) throws DaoException {
        User user = userMapper.findByUsername(logInVo.getUsername());
        if (null != user)
            throw new DaoException("failed to create repeated user.");
        User newUser = new User();
        user.setUserName(logInVo.getUsername());
        user.setPassword(UserUtil.encryBase64(logInVo.getPassword()));
        userMapper.createUser(user);
        Long newUserId = newUser.getId();
        if (null == newUserId)
            throw new DaoException("failed to create user.");
        newUser = userMapper.findById(newUserId);
        return newUser;
    }

    @Override
    public User update(UserInfoVo userInfo) throws DaoException {
        return null;
    }
}
