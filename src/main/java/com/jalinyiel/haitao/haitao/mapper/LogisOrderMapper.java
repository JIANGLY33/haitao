package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.LogisOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LogisOrderMapper {

    @Select("SELECT *  FROM logis_order WHERE id = #{id}")
    @Results(id = "logisOrderDo", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zip_code", property = "zipCode"),
            @Result(column = "phone_number", property = "phoneNumber"),
            @Result(column = "address", property = "address"),
            @Result(column = "receiver", property = "receiver"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    LogisOrder findByBizOrderId(Long Id);
}
