package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图
 *
 * @author Jalinyiel
 */
@Data
public class Carousel implements Serializable {

    private static final long serialVersionUID = 452733256249053123L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 轮播开始时间
     */
    private Date startTime;

    /**
     * 轮播图片
     */
    private String image;

    /**
     * 轮播结束时间
     */
    private Date endTime;

    /**
     * 轮播状态
     */
    private Byte status;

    /**
     * 描述信息
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
