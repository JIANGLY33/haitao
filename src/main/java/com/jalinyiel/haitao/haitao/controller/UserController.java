package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.UserUtil;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.mapper.UserMapper;
import com.jalinyiel.haitao.haitao.model.domain.User;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.ActivityItemVo;
import com.jalinyiel.haitao.haitao.model.vo.LogInVo;
import com.jalinyiel.haitao.haitao.model.vo.UserInfoVo;
import com.jalinyiel.haitao.haitao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    ResponseResult<String> login(@RequestBody LogInVo logInVo) {
        try {
            boolean success = userService.logIn(logInVo);
            if (!success) {
                return ResponseResult.failedResult(CommonResultCode.UNAUTHORIZED);
            }
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            String username = logInVo.getUsername();
            String logInTag = UserUtil.getLogInTag(username);
            request.getSession().setAttribute(username,logInTag);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, logInTag);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    ResponseResult<String> setTest(@RequestParam String username) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        request.getSession().setAttribute("username",username);
        return ResponseResult.successResult(CommonResultCode.SUCCESS,request.getSession().getAttribute("username").toString());
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    ResponseResult<String> getTest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        return ResponseResult.successResult(CommonResultCode.SUCCESS,request.getSession().getAttribute("username").toString());
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    ResponseResult getAll(){
        try {
            List<User> users = userService.getAll();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, users);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT)
    ResponseResult updateUser(User user){
        try {
            userService.update(user);
            System.out.println(user);
            return ResponseResult.successResult(CommonResultCode.SUCCESS);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    /**
     * 用户注册
     * @param logInVo
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseResult register(@RequestBody LogInVo logInVo){
        try {
            User user = userService.register(logInVo);
            System.out.println(user);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, user);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED, daoException.getLocalizedMessage());
        }
    }


}
