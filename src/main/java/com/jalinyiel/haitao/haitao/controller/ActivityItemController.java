package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.ActivityItem;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.ActivityItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * cz
 */
@CrossOrigin
@RestController
public class ActivityItemController {

    @Autowired
    ActivityItemService activityItemService;

    /**
     * cz: 查询所有活动-商品
     * @return
     */
    @RequestMapping(value = "/getallactivityitem", method = RequestMethod.GET)
    ResponseResult getAll(){
        try {
            List<ActivityItem> activityItems = activityItemService.getAll();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, activityItems);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    /**
     * cz: 根据活动id查询商品
     * @param id
     * @return
     */
    @RequestMapping(value = "/getitembyactivity/{id}", method = RequestMethod.GET)
    ResponseResult getByActivity(@PathVariable("id") Long id){
        try {
            List<ActivityItem> activityItems = activityItemService.getByActivity(id);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, activityItems);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

}
