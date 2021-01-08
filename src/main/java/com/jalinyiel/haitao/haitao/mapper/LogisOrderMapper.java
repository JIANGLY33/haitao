package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.LogisOrder;
import org.apache.ibatis.annotations.*;

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

    @Insert({"<script>",
            "INSERT INTO logis_order(id, zip_code, phone_number, address, receiver, gmt_create, gmt_modified) ",
            "VALUES (#{id}, #{zipCode}, #{phoneNumber} ,#{address},#{receiver},now(),now()",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insertLogisOrder(LogisOrder logisOrder);
}
