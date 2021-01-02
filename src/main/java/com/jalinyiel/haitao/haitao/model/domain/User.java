package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author Jalinyiel
 */
@Data
public class User {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

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
     * 用户类型
     */
    private Byte type;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
