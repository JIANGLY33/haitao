package com.jalinyiel.haitao.haitao.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 2252411577938238264L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户描述
     */
    private String description;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 类型
     */


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

}
