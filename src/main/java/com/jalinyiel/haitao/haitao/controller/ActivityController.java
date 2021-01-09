package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.Activity;
import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.ActivityItemVo;
import com.jalinyiel.haitao.haitao.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * cz
 */
@CrossOrigin
@RestController("activity")
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

    /**
     * 查询所有活动的所有商品
     * @return
     */
    @RequestMapping(value = "/getactivities", method = RequestMethod.GET)
    ResponseResult getAllAct(){
        try {
            List<ActivityItemVo> activities = activityService.getActivityAllItem();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, activities);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/updateactivity", method = RequestMethod.PUT)
    ResponseResult updateActivity(@RequestBody Activity activity) {
        try {
            Activity newActivity = activityService.update(activity);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newActivity);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/addactivity", method = RequestMethod.POST)
    ResponseResult addActivity(@RequestBody Activity activity) {
        try {
            Activity newActivity = activityService.add(activity);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newActivity);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/delactivity/{id}", method = RequestMethod.DELETE)
    ResponseResult delActivity(@PathVariable("id") Long id){
        try {
            Activity activity = activityService.delete(id);
            if(activity != null) return ResponseResult.successResult(CommonResultCode.SUCCESS, activity);
            else return ResponseResult.failedResult(CommonResultCode.FAILED, "not exist");
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

}
