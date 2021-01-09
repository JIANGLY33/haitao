package com.jalinyiel.haitao.haitao.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HistoryOrderVo implements Serializable {

    private static final long serialVersionUID = -184099560481038386L;

    Long bizOrderId;

    private List<OrderItemVo> buyItems;

}
