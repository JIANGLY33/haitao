package com.jalinyiel.haitao.haitao.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemVo implements Serializable {

    private static final long serialVersionUID = -6741161338265907842L;

    private String itemName;

    private Long price;

    private String image;

    private Integer amount;
}
