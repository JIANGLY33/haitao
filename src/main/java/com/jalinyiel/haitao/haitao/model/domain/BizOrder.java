package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务订单
 *
 * @author Jalinyiel
 */
@Data
@Builder
public class BizOrder implements Serializable {

    private static final long serialVersionUID = 4133239948624012172L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 下单者
     */
    private String buyer;

    /**
     * 订单状态
     */
    private Byte status;

    /**
     * 商品ID
     */
    private Long item_id;

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
    private String payer;

    /**
     * 备注信息
     */
    private String memo;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 父订单号
     */
    private Long parentId;

    /**
     * 订单类型: 0-父子订单 1-父订单 2-子订单
     */
    private Byte type;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
