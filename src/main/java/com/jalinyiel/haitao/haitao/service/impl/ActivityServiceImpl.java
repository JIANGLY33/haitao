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

    @Override
    public Activity getById(Long id) throws DaoException {
        return activityMapper.findById(id);
    }

    /**
     * 查询所有活动的所有商品(嵌套)
     */
    @Override
    public List<ActivityItemVo> getActivityAllItem() throws DaoException {
        return activityMapper.findActivityAllItem();
    }

    @Override
    public Activity update(Activity activity) throws DaoException {
        activityMapper.updateActivity(activity);
        return activityMapper.findById(activity.getId());
    }

    @Override
    public Activity add(Activity activity) throws DaoException {
        activityMapper.createActivity(activity);
        return activity;
    }

    @Override
    public Activity delete(Long id) throws DaoException {
        Activity activity = activityMapper.findById(id);
        activityMapper.delActivity(id);
        return activity;
    }
}
