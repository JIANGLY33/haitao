package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BizOrderMapper {

    @Select("SELECT * FROM biz_order WHERE id = #{id}")
    @Results(id = "bizOrderDO", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buyer_id", property = "buyerId"),
            @Result(column = "status", property = "status"),
            @Result(column = "logis_order_id", property = "logisOrderId"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "pay_price", property = "payPrice"),
            @Result(column = "sum_price", property = "payPrice"),
            @Result(column = "discount", property = "discount"),
            @Result(column = "pay_time", property = "payTime"),
            @Result(column = "memo", property = "memo"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gtm_modified", property = "gmtModified")
    })
    BizOrder findById(Long id);
}
