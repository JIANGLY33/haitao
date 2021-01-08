package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.domain.LogisOrder;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BizOrderMapper {

    @Select("SELECT * FROM biz_order WHERE id = #{id}")
    @Results(id = "bizOrderDO", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buyer", property = "buyer"),
            @Result(column = "status", property = "status"),
            @Result(column = "logis_order_id", property = "logisOrderId"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "pay_price", property = "payPrice"),
            @Result(column = "sum_price", property = "payPrice"),
            @Result(column = "discount", property = "discount"),
            @Result(column = "pay_time", property = "payTime"),
            @Result(column = "memo", property = "memo"),
            @Result(column = "type", property = "type"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gtm_modified", property = "gmtModified")
    })
    BizOrder findById(Long id);

    @Insert({"<script>",
            "INSERT INTO biz_order(id, buyer, status, logis_order_id, pay_price, sum_price,",
            "discount, pay_time, end_time, payer, memo, gmt_create, gmt_modified, item_id, parent_id, type)",
            "VALUES (#{id}, #{buyer}, #{status} ,#{logisOrderId},#{payPrice},#{sumPrice},",
            "#{discount},#{payTime},#{endTime},#{payer},#{memo},now(),now(),#{itemId},#{parentId},#{type}",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insertBizOrder(BizOrder bizOrder);
}
