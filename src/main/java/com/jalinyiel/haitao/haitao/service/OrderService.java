package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.vo.OrderVo;

public interface OrderService {

    Long createOrder(OrderVo orderVo);

    OrderVo getOrderById(Long id);
}
