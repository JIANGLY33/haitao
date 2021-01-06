package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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


}
