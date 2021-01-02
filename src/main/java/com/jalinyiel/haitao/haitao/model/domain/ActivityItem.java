package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.util.Date;

/**
 * 活动与商品关联关系
 *
 * @author Jalinyiel
 */
@Data
public class ActivityItem {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
