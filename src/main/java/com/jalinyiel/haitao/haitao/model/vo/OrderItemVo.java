package com.jalinyiel.haitao.haitao.model.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OrderItemVo implements Serializable {

    private static final long serialVersionUID = -4656166194380911153L;

    private String itemName;

    private Long price;

    private String image;
}
