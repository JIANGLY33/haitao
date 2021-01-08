package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.vo.CategoryNumVo;
import com.jalinyiel.haitao.haitao.model.vo.ItemVo;
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
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "number", property = "number")
    })
    Item findById(Long id);

    /**
     * 查询所有活动的所有商品(子查询)
     * @param activityId
     * @return
     */
    @Select({"<script>",
            "SELECT *, ic.name category_name FROM item i",
            "LEFT JOIN item_category ic",
            "ON i.category_id = ic.id",
            "WHERE i.id IN (",
            "SELECT item_id FROM activity_item WHERE activity_id = 1)",
            "</script>"})
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "images", property = "images"),
            @Result(column = "price", property = "price"),
            @Result(column = "status", property = "status"),
            @Result(column = "rate", property = "rate"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "number", property = "number")
    })
    List<ItemVo> findItemsByActivityId(Long activityId);

    /**
     * cz: 查询所有商品
     * @return
     */
    @Select({"<script>",
            "SELECT *, ic.name category_name FROM item i",
            "LEFT JOIN item_category ic",
            "ON i.category_id = ic.id",
            "</script>"})
    @Results(id = "itemWithCNameDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "images", property = "images"),
            @Result(column = "price", property = "price"),
            @Result(column = "status", property = "status"),
            @Result(column = "rate", property = "rate"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
            @Result(column = "number", property = "number")
    })
    List<ItemVo> findAllItems();

    /**
     * cz: 根据活动id查询商品
     * @param activityId
     * @return
     */
    @Select({"<script>",
            "SELECT *, ic.name category_name FROM item i",
            "LEFT JOIN item_category ic",
            "ON i.category_id = ic.id",
            "WHERE i.id IN (",
            "SELECT item_id FROM activity_item WHERE activity_id = 1)",
            "</script>"})
    @ResultMap("itemWithCNameDo")
    List<ItemVo> findItemsByActivity(Long activityId);

    /**
     * 根据类型查商品
     * @param categoryId
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM item WHERE category_id = #{categoryId}",
            "</script>"})
    @ResultMap("itemDo")
    List<Item> findItemsByCategory(Long categoryId);

    /**
     * cz: 查询所有类目对应的商品数目
     * @return
     */
    @Select({"<script>",
            "SELECT i.category_id, ic.name, ic.description, count(*) num FROM haitao.item i",
            "LEFT JOIN haitao.item_category ic",
            "ON i.category_id=ic.id",
            "GROUP BY category_id;",
            "</script>"})
    @Results(id = "categoryNumDo", value = {
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "num", property = "num")
    })
    List<CategoryNumVo> getCategoryNum();

    /**
     * cz: 根据价格查询商品
     * @param price
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM haitao.item WHERE price&lt;=#{price}",
            "</script>"})
    @ResultMap("itemDo")
    List<Item> getItemUnderPrice(Long price);

    /**
     * cz: 根据类型和价格筛选商品
     * @param categoryId
     * @param price
     * @return
     */
    @Select({"<script>",
            "SELECT * FROM item WHERE category_id = #{categoryId} AND price&lt;=#{price}",
            "</script>"})
    @ResultMap("itemDo")
    List<Item> getItemByCategoryUnderPrice(Long categoryId, Long price);


    @Select("SELECT * FROM item WHERE id = #{id}")
    @ResultMap("itemDo")
    Item findByAcvivity(Long id);

}
