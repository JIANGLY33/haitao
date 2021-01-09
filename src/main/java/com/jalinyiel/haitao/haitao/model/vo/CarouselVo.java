package com.jalinyiel.haitao.haitao.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import lombok.Data;

import java.util.Date;

/**
 * @author: chenzhe
 * @date: 2021/1/9 22:38
 */
@Data
public class CarouselVo {

    private static final long serialVersionUID = 1L;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 轮播图片
     */
    private String image;

    /**
     * 轮播结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 联合查询item信息
     */
    Item item;

}
