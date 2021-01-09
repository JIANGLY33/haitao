package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.mapper.BizOrderMapper;
import com.jalinyiel.haitao.haitao.mapper.ItemMapper;
import com.jalinyiel.haitao.haitao.mapper.LogisOrderMapper;
import com.jalinyiel.haitao.haitao.model.constant.BizOrderConstant;
import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.domain.LogisOrder;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderItemVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderVo;
import com.jalinyiel.haitao.haitao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    BizOrderMapper bizOrderMapper;

    @Autowired
    LogisOrderMapper logisOrderMapper;

    @Autowired
    ItemMapper itemMapper;

    @Override
    public Long buy(BuyItemsVo buyItemsVo) {
        List<BuyItemsVo.BuyItem> buyItems = buyItemsVo.getBuyItems();
        if (1 == buyItems.size() && 1 == buyItems.get(0).getAmount()) {
            LogisOrder logisOrder = generateLogisOrder(buyItemsVo);
            logisOrderMapper.insertLogisOrder(logisOrder);
            BuyItemsVo.BuyItem buyItem = buyItems.get(0);
            BizOrder bizOrder = generateBizOrder(buyItem, BizOrderConstant.PARENT_SUB);
            bizOrder.setLogisOrderId(logisOrder.getId());
            bizOrderMapper.insertBizOrder(bizOrder);
            return bizOrder.getId();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String buyer = request.getHeader("username");
        LogisOrder logisOrder = generateLogisOrder(buyItemsVo);
        logisOrderMapper.insertLogisOrder(logisOrder);
        BizOrder parentOrder =
                BizOrder.builder()
                        .logisOrderId(logisOrder.getId())
                        .buyer(buyer)
                        .status(BizOrderConstant.NOT_PAY)
                        .type(BizOrderConstant.ONLY_PARENT)
                        .build();
        bizOrderMapper.insertBizOrder(parentOrder);
        buyItems.stream().forEach(buyItem -> {
            BizOrder bizOrder = generateBizOrder(buyItem, BizOrderConstant.ONLY_SUB);
            bizOrder.setLogisOrderId(logisOrder.getId());
            bizOrder.setParentId(parentOrder.getId());
            bizOrderMapper.insertBizOrder(bizOrder);
        });
        return parentOrder.getId();
    }

    @Override
    public List<OrderItemVo> getOrderAllItems(Long orderId) {
        BizOrder bizOrder = bizOrderMapper.findById(orderId);
        List<OrderItemVo> orderItemVos = new ArrayList<>();
        if (BizOrderConstant.PARENT_SUB == bizOrder.getType()) {
            OrderItemVo buyItem = generateOrderItemVo(bizOrder);
            orderItemVos.add(buyItem);
            return orderItemVos;
        }
        if (BizOrderConstant.ONLY_SUB == bizOrder.getType()) {
            return new ArrayList<>();
        }
        List<BizOrder> subOrders = bizOrderMapper.findByParentId(orderId);
        orderItemVos = subOrders.stream().map(this::generateOrderItemVo).collect(Collectors.toList());
        return orderItemVos;
    }

    private LogisOrder generateLogisOrder(BuyItemsVo buyItemsVo) {
        return LogisOrder.builder()
                .address(buyItemsVo.getAddress())
                .phoneNumber(buyItemsVo.getPhoneNumber())
                .receiver(buyItemsVo.getReceiver())
                .build();
    }

    private BizOrder generateBizOrder(BuyItemsVo.BuyItem buyItem, Byte type) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Item item = itemMapper.findById(buyItem.getItemId());
        return BizOrder.builder()
                .itemImage(item.getImages())
                .itemName(item.getName())
                .sumPrice(item.getPrice())
                .payPrice(item.getPrice() * item.getRate() / 100)
                .type(type)
                .buyer(request.getHeader("username"))
                .status(BizOrderConstant.NOT_PAY)
                .itemId(buyItem.getItemId())
                .build();
    }

    private OrderItemVo generateOrderItemVo(BizOrder bizOrder) {
        Item item = itemMapper.findById(bizOrder.getItemId());
        return OrderItemVo.builder()
                .itemName(bizOrder.getItemName())
                .image(bizOrder.getItemImage())
                .price(bizOrder.getPayPrice())
                .build();

    }
}
