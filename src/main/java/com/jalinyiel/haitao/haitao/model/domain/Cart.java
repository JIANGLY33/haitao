package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车
 *
 * @author Jalinyiel
 */
@Data
public class Cart implements Serializable {

    private static final long serialVersionUID = -5193509483960659521L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 购物车中包含的商品，由商品id拼接而成，;来分隔
     */
    private String itemsDetail;

    /**
     * 购物车拥有者的id
     */
    private Long ownerId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
