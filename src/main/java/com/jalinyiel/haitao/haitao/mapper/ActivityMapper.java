package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.Activity;
import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import com.jalinyiel.haitao.haitao.model.vo.ActivityItemVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface ActivityMapper {

    @Select("SELECT * FROM activity")
    @Results(id = "activityDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "image", property = "image"),
            @Result(column = "status", property = "status"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<Activity> findAll();

    @Select("SELECT * FROM activity WHERE id = #{id}")
    @ResultMap("activityDo")
    Activity findById(Long id);

    /**
     * 查询所有活动的所有商品(父查询)
     * @return
     */
    @Select("SELECT * FROM activity")
    @Results(id = "activityAllItemDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "image", property = "image"),
            @Result(column = "status", property = "status"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "id", property = "items", many = @Many(select = "com.jalinyiel.haitao.haitao.mapper.ItemMapper.findItemsByActivityId"))
    })
    List<ActivityItemVo> findActivityAllItem();

    @Delete("DELETE FROM activity WHERE id = #{id}")
    Integer delActivity(Long id);

    @Insert({"<script>",
            "INSERT INTO activity(id, name, description, image, status, start_time, end_time, gmt_create, gmt_modified)",
            "VALUES (#{id}, #{name}, #{description}, #{image}, #{status}, now(), #{endTime}, now(), now()",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long createActivity(Activity activity);

    @UpdateProvider(type = ActivityProvider.class, method = "updateActivity")
    Integer updateActivity(Activity activity);

    class ActivityProvider {

        public String updateActivity(Activity activity) {
            return new SQL(){{
                UPDATE("activity");
                if (null != activity.getName()) {
                    SET("name = #{name}");
                }
                if (null != activity.getDescription()) {
                    SET("description = #{description}");
                }
                if (null != activity.getImage()) {
                    SET("image = #{image}");
                }
                if (null != activity.getStatus()) {
                    SET("status = #{status}");
                }
                if (null != activity.getStartTime()) {
                    SET("start_time = #{startTime}");
                }
                if (null != activity.getEndTime()) {
                    SET("endTime = #{endTime}");
                }
                SET("gmt_modified = now()");
                WHERE("id = #{id}");
            }
            }.toString();
        }
    }

}
