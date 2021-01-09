package com.jalinyiel.haitao.haitao.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目
 *
 * @author Jalinyiel
 */
@Data
public class ItemCategory implements Serializable {

    private static final long serialVersionUID = 3702164868024388692L;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;
}
