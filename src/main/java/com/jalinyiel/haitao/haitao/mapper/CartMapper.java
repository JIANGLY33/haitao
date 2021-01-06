package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CartMapper {

    @Select("SELECT * FROM cart WHERE owner_id = #{ownerId}")
    @Results(id = "cartDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "items_detail", property = "itemsDetail"),
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    Cart findByOwnerId(Long ownerId);
}
