package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.ActivityItem;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;

import java.util.List;

public interface ActivityItemService {

    List<ActivityItem> getAll() throws DaoException;

    List<ActivityItem> getByActivity(Long activityId) throws DaoException;

}
