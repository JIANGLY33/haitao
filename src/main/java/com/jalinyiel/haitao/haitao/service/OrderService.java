package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderVo;

import java.util.List;

public interface OrderService {

    Long createOrder(OrderVo orderVo);

    OrderVo getOrderById(Long id);

    Long buy(BuyItemsVo buyItemsVo);
}
