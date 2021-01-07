package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.Carousel;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CarouselMapper {

    @Select("SELECT * FROM carousel WHERE id = #{id}")
    @Results(id = "carouselDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "status", property = "status"),
            @Result(column = "start_time", property = "startTIme"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "memo", property = "memo"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "image", property = "image")
    })
    Carousel findById(Long id);
}
