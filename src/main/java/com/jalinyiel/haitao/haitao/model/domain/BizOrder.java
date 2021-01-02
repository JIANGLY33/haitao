package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.util.Date;

/**
 * 业务订单
 *
 * @author Jalinyiel
 */
@Data
public class BizOrder {

    /**
     * 主键
     */
    private Long id;

    /**
     * 下单者id
     */
    private Long buyerId;

    /**
     * 订单状态
     */
    private Byte status;

    /**
     * 对应的物流订单号
     */
    private Long logisOrderId;

    /**
     * 订单支付金额
     */
    private Long payPrice;

    /**
     * 订单商品总价
     */
    private Long sumPrice;

    /**
     * 订单总折扣
     */
    private Long discount;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 交易结束时间
     */
    private Date endTime;

    /**
     * 支付者id
     */
    private Long payerId;

    /**
     * 备注信息
     */
    private String memo;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
