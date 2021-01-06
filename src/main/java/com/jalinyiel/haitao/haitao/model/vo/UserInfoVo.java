package com.jalinyiel.haitao.haitao.model.vo;

import java.io.Serializable;

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

}
