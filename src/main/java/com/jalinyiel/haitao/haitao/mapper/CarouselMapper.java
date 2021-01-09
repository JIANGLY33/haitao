package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.Carousel;
import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import com.jalinyiel.haitao.haitao.model.vo.CarouselVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface CarouselMapper {

    @Select("SELECT * FROM carousel WHERE id = #{id}")
    @Results(id = "carouselDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "status", property = "status"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "memo", property = "memo"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "image", property = "image")
    })
    Carousel findById(Long id);

    @Select("SELECT * FROM carousel")
    @Results(id = "carouselItemNameDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "status", property = "status"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "memo", property = "memo"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "image", property = "image")
    })
    List<Carousel> findAll();

    @Select("SELECT * FROM carousel")
    @Results(id = "carouselWithItemDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "item_id", property = "item", one = @One(select = "com.jalinyiel.haitao.haitao.mapper.ItemMapper.findById")),
            @Result(column = "status", property = "status"),
            @Result(column = "start_time", property = "startTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "memo", property = "memo"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "image", property = "image")
    })
    List<CarouselVo> findWithItem();

    @Select("SELECT * FROM carousel WHERE status = #{status}")
    @ResultMap("carouselDo")
    List<Carousel> findByStatus(Integer status);

    @Delete("DELETE FROM carousel WHERE id = #{id}")
    Integer delCarousel(Long id);

    @Insert({"<script>",
            "INSERT INTO carousel(id, item_id, status, start_time, end_time, gmt_create, gmt_modified, memo, image)",
            "VALUES (#{id}, #{itemId}, #{status}, #{startTime}, #{endTime}, now(), now(), #{memo}, #{image}",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long createCarousel(Carousel carousel);

    @UpdateProvider(type = CarouselProvider.class, method = "updateCarousel")
    Integer updateCarousel(Carousel carousel);

    class CarouselProvider {
        public String updateCarousel(Carousel carousel) {
            return new SQL(){{
                UPDATE("carousel");
                if (null != carousel.getItemId()) {
                    SET("item_id = #{itemId}");
                }
                if (null != carousel.getStatus()) {
                    SET("status = #{status}");
                }
                if (null != carousel.getEndTime()) {
                    SET("end_time = #{endTime}");
                }
                if (null != carousel.getMemo()) {
                    SET("memo = #{memo}");
                }
                if (null != carousel.getImage()) {
                    SET("image = #{image}");
                }
                SET("gmt_modified = now()");
                WHERE("id = #{id}");
            }
            }.toString();
        }
    }

}
