package com.jalinyiel.haitao.haitao.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ItemVo implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 商品对应的类目名称
     */
    private String categoryName;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 商品数量
     */
    private Integer number;

}
