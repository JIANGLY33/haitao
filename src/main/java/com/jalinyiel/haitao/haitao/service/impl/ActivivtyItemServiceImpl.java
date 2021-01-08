package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.mapper.ActivityItemMapper;
import com.jalinyiel.haitao.haitao.model.domain.ActivityItem;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.ActivityItemService;
import com.jalinyiel.haitao.haitao.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * cz
 */
@Service
public class ActivivtyItemServiceImpl implements ActivityItemService {

    @Autowired
    ActivityItemMapper activityItemMapper;

    @Override
    public List<ActivityItem> getAll() throws DaoException {
        return activityItemMapper.findAll();
    }

    @Override
    public List<ActivityItem> getByActivity(Long activityId) throws DaoException {
        return activityItemMapper.findByActivity(activityId);
    }


}
