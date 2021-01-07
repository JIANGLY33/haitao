package com.jalinyiel.haitao.haitao.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * cz: 用于查询所有类目对应的商品数目
 */
@Data
public class CategoryNumVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对应商品类目的id
     */
    private Long categoryId;

    /**
     * 对应类目的名称
     */
    private String name;

    /**
     * 对应类目的描述
     */
    private String description;

    /**
     * 对应类目的商品数目
     */
    private int num;

}
