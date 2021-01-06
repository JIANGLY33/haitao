package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 促销活动
 *
 * @author Jalinyiel
 */
@Data
public class Activity implements Serializable {

    private static final long serialVersionUID = -4052683284507649963L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动展示图片
     */
    private String image;

    /**
     * 活动状态
     */
    private Byte status;

    /**
     * 活动开始时间
     */
    private Long startTime;

    /**
     * 活动结束时间
     */
    private Long endTime;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
