package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.mapper.ActivityMapper;
import com.jalinyiel.haitao.haitao.model.domain.Activity;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.ActivityItemVo;
import com.jalinyiel.haitao.haitao.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public List<Activity> getAllActivity() throws DaoException {
        return activityMapper.findAll();
    }

    /**
     * 查询所有活动的所有商品(嵌套)
     */
    @Override
    public List<ActivityItemVo> getActivityAllItem() throws DaoException {
        return activityMapper.findActivityAllItem();
    }
}
