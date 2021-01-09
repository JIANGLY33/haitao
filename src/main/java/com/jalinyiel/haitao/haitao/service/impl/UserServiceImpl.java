package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.common.UserUtil;
import com.jalinyiel.haitao.haitao.mapper.UserMapper;
import com.jalinyiel.haitao.haitao.model.constant.UserConstant;
import com.jalinyiel.haitao.haitao.model.domain.User;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.LogInVo;
import com.jalinyiel.haitao.haitao.model.vo.UserInfoVo;
import com.jalinyiel.haitao.haitao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean logIn(LogInVo logInVo) throws DaoException{
        User user = userMapper.findByUsername(logInVo.getUsername());
        if (null == user)
            throw new DaoException("failed to find user.");
        if (user.getUserName().equals(logInVo.getUsername()) && user.getPassword().equals(UserUtil.encryBase64(logInVo.getPassword()))) {
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
        newUser.setUserName(logInVo.getUsername());
        newUser.setPassword(UserUtil.encryBase64(logInVo.getPassword()));
        newUser.setType(UserConstant.NORMAL_USER);
        userMapper.createUser(newUser);
        Long newUserId = newUser.getId();
        if (null == newUserId)
            throw new DaoException("failed to create user.");
        newUser = userMapper.findById(newUserId);
        return newUser;
    }

    @Override
    public void update(User user) throws DaoException {
        userMapper.updateUser(user);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return userMapper.findAll();
    }

    @Override
    public List<UserInfoVo> getByType(Integer type) throws DaoException {
        return userMapper.findByType(type);
    }
}
