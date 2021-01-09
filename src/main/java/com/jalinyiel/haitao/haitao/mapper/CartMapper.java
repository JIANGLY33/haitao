package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.Cart;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CartMapper {

    @Select("SELECT * FROM cart WHERE owner = #{owner}")
    @Results(id = "cartDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "items_detail", property = "itemsDetail"),
            @Result(column = "owner", property = "owner"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    Cart findByOwner(String owner);

    @Update({"<script>",
            "UPDATE cart SET items_detail = #{itemDetail} ",
            "WHERE owner = #{username}",
            "</script>"})
    Integer updateItemDetail(String itemDetail, String username);

    @Insert({"<script>",
            "INSERT INTO cart(id, items_detail, owner, gmt_create, gmt_modified) ",
            "VALUES (#{id}, #{itemsDetail}, #{owner} ,now(), now()",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insertCart(Cart cart);
}
