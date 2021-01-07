package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Select("SELECT * FROM item WHERE id = #{id}")
    @Results(id = "itemDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "images", property = "images"),
            @Result(column = "price", property = "price"),
            @Result(column = "status", property = "status"),
            @Result(column = "rate", property = "rate"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    Item findById(Long id);

    @Select({"<script>",
            "SELECT * FROM item WHERE id IN (",
            "SELECT item_id FROM activity_item WHERE activity_id = #{activityId})",
            "</script>"})
    @ResultMap("itemDo")
    List<Item> findItemsByActivityId(Long activityId);

    /**
     * cz: get all items
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM item",
            "</script>"})
    @ResultMap("itemDo")
    List<Item> findAllItems();

    @Select({"<script>",
            "SELECT * FROM item WHERE category_id = #{categoryId}",
            "</script>"})
    @ResultMap("itemDo")
    List<Item> findItemsByCategory(Long categoryId);

}
