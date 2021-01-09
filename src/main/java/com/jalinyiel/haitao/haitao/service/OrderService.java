package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.HistoryOrderVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderItemVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderVo;

import java.util.List;

public interface OrderService {

    Long buy(BuyItemsVo buyItemsVo);

    List<OrderItemVo> getOrderAllItems(Long orderId);

    Boolean pay(Long orderId);

    Boolean cancel(Long orderId);

    List<HistoryOrderVo> getHisOrdersByStatus(Byte status);

    List<BizOrder> getAllOrders();
}
