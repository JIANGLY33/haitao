package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.mapper.BizOrderMapper;
import com.jalinyiel.haitao.haitao.mapper.ItemMapper;
import com.jalinyiel.haitao.haitao.mapper.LogisOrderMapper;
import com.jalinyiel.haitao.haitao.model.constant.BizOrderConstant;
import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.domain.LogisOrder;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.HistoryOrderVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderItemVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderVo;
import com.jalinyiel.haitao.haitao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @Override
    public Boolean pay(Long orderId) {
        BizOrder bizOrder = bizOrderMapper.findById(orderId);
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(bizOrder.getId());
        Integer row = 0;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String username = request.getHeader("username");
        if (BizOrderConstant.PARENT_SUB == bizOrder.getType()) {
            row = bizOrderMapper.updateStatusToCanceled(orderIds, BizOrderConstant.TRANSPORTING.intValue());
            bizOrderMapper.setPayInfo(bizOrder.getId(), username);
            return row > 0;
        }
        if (BizOrderConstant.ONLY_SUB == bizOrder.getType()) {
            return false;
        }
        List<BizOrder> subOrders = bizOrderMapper.findByParentId(orderId);
        subOrders.stream().forEach(b -> {
            orderIds.add(b.getId());
        });
        bizOrderMapper.batchSetPayInfo(orderIds,username);
        row = bizOrderMapper.updateStatusToCanceled(orderIds, BizOrderConstant.TRANSPORTING.intValue());
        return row > 0;
    }

    @Override
    public Boolean cancel(Long orderId) {
        BizOrder bizOrder = bizOrderMapper.findById(orderId);
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(bizOrder.getId());
        Integer row = 0;
        if (BizOrderConstant.PARENT_SUB == bizOrder.getType()) {
            row = bizOrderMapper.updateStatusToCanceled(orderIds, BizOrderConstant.CANCELED.intValue());
        }
        if (BizOrderConstant.ONLY_SUB == bizOrder.getType()) {
            return false;
        }
        List<BizOrder> subOrders = bizOrderMapper.findByParentId(orderId);
        subOrders.stream().forEach(b -> orderIds.add(b.getId()));
        row = bizOrderMapper.updateStatusToCanceled(orderIds, BizOrderConstant.CANCELED.intValue());
        return row > 0;
    }

    @Override
    public List<HistoryOrderVo> getHisOrdersByStatus(Byte status) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String username = request.getHeader("username");
        List<BizOrder> parentBizOrders = bizOrderMapper.findParentByStatusAndUser(username,status);
        List<HistoryOrderVo> historyOrderVos = parentBizOrders.stream().map(this::generateHistoryOrderVo).collect(Collectors.toList());
        return historyOrderVos;
    }

    private HistoryOrderVo generateHistoryOrderVo(BizOrder bizOrder) {
        HistoryOrderVo historyOrderVo = new HistoryOrderVo();
        historyOrderVo.setBizOrderId(bizOrder.getId());
        List<OrderItemVo> orderItemVos = new ArrayList<>();
        if (BizOrderConstant.PARENT_SUB == bizOrder.getType()) {
            orderItemVos.add(generateOrderItemVo(bizOrder));
        } else {
            List<BizOrder> bizOrders = bizOrderMapper.findByParentId(bizOrder.getId());
            bizOrders.stream().forEach(b -> orderItemVos.add(generateOrderItemVo(b)));
        }
        historyOrderVo.setBuyItems(orderItemVos);
        return historyOrderVo;
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
                .discount(0L)
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

    @Override
    public List<BizOrder> getAllOrders() {
        return bizOrderMapper.getAllBizOrder();
    }
}
