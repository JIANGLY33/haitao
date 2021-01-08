package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.Activity;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.ActivityItemVo;
import com.jalinyiel.haitao.haitao.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * cz
 */
@CrossOrigin
@RestController
public class ActivityController {

    @Autowired
    ActivityService activityService;

    /**
     * cz: 查询所有活动
     * @return
     */
    @RequestMapping(value = "/getallactivity", method = RequestMethod.GET)
    ResponseResult getAll(){
        try {
            List<Activity> activities = activityService.getAllActivity();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, activities);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

}
