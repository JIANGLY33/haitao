package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import javax.sql.rowset.serial.SerialStruct;
import java.util.List;

@Mapper
public interface ItemCategoryMapper {

    @Select("SELECT * FROM item_category")
    @Results(id = "itemCategoryDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "description", property = "description"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<ItemCategory> getAll();

    @Select("SELECT * FROM item_category WHERE id = #{id}")
    @ResultMap("itemCategoryDo")
    ItemCategory findById(Long id);

    @Delete("DELETE FROM item_category WHERE id = #{id}")
    Integer delItemCategory(Long id);

    @Insert({"<script>",
            "INSERT INTO item_category(id, name, description, gmt_create, gmt_modified) ",
            "VALUES (#{id}, #{name}, #{description}, now(), now()",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long createItemCategory(ItemCategory itemCategory);

    @UpdateProvider(type = ItemCategoryProvider.class, method = "updateItemCategory")
    Integer updateItemCategory(ItemCategory itemCategory);

    class ItemCategoryProvider {

        public String updateItemCategory(ItemCategory itemCategory) {
            return new SQL(){{
                UPDATE("item_category");
                if (null != itemCategory.getName()) {
                    SET("name = #{name}");
                }
                if (null != itemCategory.getDescription()) {
                    SET("description = #{description}");
                }
                SET("gmt_modified = now()");
                WHERE("id = #{id}");
            }
            }.toString();
        }
    }

}
