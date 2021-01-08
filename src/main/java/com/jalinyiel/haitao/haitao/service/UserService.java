package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.User;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.LogInVo;

import java.util.List;

public interface UserService {

    boolean logIn(LogInVo logInVo) throws DaoException;

    User register(LogInVo logInVo) throws DaoException;

    void update(User user) throws DaoException;

    List<User> getAll() throws DaoException;

}
