package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.util.Date;

/**
 * 商品类目
 *
 * @author Jalinyiel
 */
@Data
public class ItemCategory {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品类目名
     */
    private String name;

    /**
     * 类目描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
