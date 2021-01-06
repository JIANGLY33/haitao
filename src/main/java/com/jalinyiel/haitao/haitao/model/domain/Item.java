package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 *
 * @author Jalinyiel
 */
@Data
public class Item implements Serializable {

    private static final long serialVersionUID = 6562797484236942246L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品对应的类目id
     */
    private Long categoryId;

    /**
     * 商品展示图片
     */
    private String images;

    /**
     * 商品原价
     */
    private Long price;

    /**
     * 商品状态
     */
    private Byte status;

    /**
     * 折扣
     */
    private Integer rate;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
