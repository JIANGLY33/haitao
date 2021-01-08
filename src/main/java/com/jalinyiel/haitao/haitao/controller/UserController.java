package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.UserUtil;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.LogInVo;
import com.jalinyiel.haitao.haitao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController("user")
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
            request.getSession().setAttribute("username",username);
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
}
