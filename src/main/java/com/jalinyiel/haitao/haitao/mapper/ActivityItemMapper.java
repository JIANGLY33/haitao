package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.ActivityItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * cz
 */
@Mapper
public interface ActivityItemMapper {

    @Select("SELECT * FROM activity_item")
    @Results(id = "activityItemDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "activity_id", property = "activityId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<ActivityItem> findAll();

    @Select("SELECT * FROM activity_item WHERE activity_id = #{activityId}")
    @ResultMap("activityItemDo")
    List<ActivityItem> findByActivity(Long activityId);

}
