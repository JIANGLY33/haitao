package com.jalinyiel.haitao.haitao.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 物流订单
 *
 * @author Jalinyiel
 */
@Data
public class LogisOrder implements Serializable {

    private static final long serialVersionUID = 2154439838240786655L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 收件人昵称
     */
    private String receiver;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
