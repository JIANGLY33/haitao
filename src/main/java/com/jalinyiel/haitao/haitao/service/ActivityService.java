package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.Activity;
import com.jalinyiel.haitao.haitao.model.domain.Item;

import java.util.List;

public interface ActivityService {

    List<Activity> getAllActivity();

    List<Item> getActivityAllItem(Long activityId);
}
