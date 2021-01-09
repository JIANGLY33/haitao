package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.Activity;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.ActivityItemVo;

import java.util.List;

public interface ActivityService {

    List<Activity> getAllActivity() throws DaoException;

    Activity getById(Long id) throws DaoException;

    /**
     * 查询所有活动的所有商品(嵌套)
     */
    List<ActivityItemVo> getActivityAllItem() throws DaoException;

    Activity update(Activity activity) throws DaoException;

    Activity add(Activity activity) throws DaoException;

    Activity delete(Long id) throws DaoException;

}
