package com.jalinyiel.haitao.haitao.mapper;

import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.domain.LogisOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BizOrderMapper {

    @Select("SELECT * FROM biz_order WHERE id = #{id}")
    @Results(id = "bizOrderDO", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buyer", property = "buyer"),
            @Result(column = "status", property = "status"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "logis_order_id", property = "logisOrderId"),
            @Result(column = "pay_price", property = "payPrice"),
            @Result(column = "sum_price", property = "sumPrice"),
            @Result(column = "discount", property = "discount"),
            @Result(column = "pay_time", property = "payTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "payer", property = "payer"),
            @Result(column = "memo", property = "memo"),
            @Result(column = "item_image", property = "itemImage"),
            @Result(column = "item_name", property = "itemName"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "type", property = "type"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified"),
    })
    BizOrder findById(Long id);

    @Select("SELECT * FROM biz_order WHERE parent_id = #{parentId}")
    @ResultMap("bizOrderDO")
    List<BizOrder> findByParentId(Long parentId);

    @Insert({"<script>",
            "INSERT INTO biz_order(id, buyer, status, logis_order_id, pay_price, sum_price,",
            "discount, pay_time, end_time, payer, memo, gmt_create, gmt_modified, item_id, parent_id, type, item_image, item_name)",
            "VALUES (#{id}, #{buyer}, #{status} ,#{logisOrderId},#{payPrice},#{sumPrice},",
            "#{discount},#{payTime},#{endTime},#{payer},#{memo},now(),now(),#{itemId},#{parentId},#{type}, #{itemImage}, #{itemName}",
            ")",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insertBizOrder(BizOrder bizOrder);

    @Update({"<script>",
            "UPDATE biz_order SET status = #{status} ",
            "WHERE id IN ",
            "<foreach item='item' index='index' collection='orderIds' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    Integer updateStatusToCanceled(@Param("orderIds") List<Long> orderId, int status);

    @Update({"<script>",
            "UPDATE biz_order SET payer = #{payer} ",
            "WHERE id = #{id}",
            "</script>"})
    Integer setPayer(Long orderId, String payer);

    @Select({"<script>",
            "SELECT * FROM biz_order WHERE buyer = #{username} AND status = #{status} AND type != 2",
            "</script>"})
    @ResultMap("bizOrderDO")
    List<BizOrder> findParentByStatusAndUser(String username, Byte status);

    @Select("SELECT * FROM biz_order")
    @ResultMap("bizOrderDO")
    List<BizOrder> getAllBizOrder();
}
