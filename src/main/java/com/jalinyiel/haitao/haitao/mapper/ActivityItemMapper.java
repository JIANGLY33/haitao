package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.ActivityItem;
import com.jalinyiel.haitao.haitao.model.domain.Item;
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

    @Select("SELECT * FROM activity_item WHERE item_id = #{itemId} AND activity_id = #{activityId}")
    @ResultMap("activityItemDo")
    ActivityItem findByItemActivity(Long itemId, Long activityId);


    @Select("SELECT * FROM activity_item WHERE activity_id = #{activityId}")
    @ResultMap("activityItemDo")
    List<ActivityItem> findByActivity(Long activityId);

    @Insert({"<script>",
            "INSERT INTO activity_item(id, item_id, activity_id, gmt_create, gmt_modified) ",
            "VALUES (#{id}, #{itemId}, #{activityId}, now(), now()",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long createActivityItem(ActivityItem activityItem);

    @Delete("DELETE FROM activity_item WHERE item_id = #{itemId} AND activity_id = #{activityId}")
    Integer delActivityItem(Long itemId, Long activityId);


}
