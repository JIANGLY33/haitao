package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.mapper.BizOrderMapper;
import com.jalinyiel.haitao.haitao.mapper.ItemMapper;
import com.jalinyiel.haitao.haitao.mapper.LogisOrderMapper;
import com.jalinyiel.haitao.haitao.model.constant.BizOrderConstant;
import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.domain.LogisOrder;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderVo;
import com.jalinyiel.haitao.haitao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    BizOrderMapper bizOrderMapper;

    @Autowired
    LogisOrderMapper logisOrderMapper;

    @Autowired
    ItemMapper itemMapper;

    @Override
    public Long createOrder(OrderVo orderVo) {
        return null;
    }

    @Override
    public OrderVo getOrderById(Long id) {
        return null;
    }

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
        String buyer = request.getSession().getAttribute("username").toString();
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
            BizOrder bizOrder = generateBizOrder(buyItem,BizOrderConstant.ONLY_SUB);
            bizOrder.setLogisOrderId(logisOrder.getId());
            bizOrder.setParentId(parentOrder.getId());
            bizOrderMapper.insertBizOrder(bizOrder);
        });
        return parentOrder.getId();
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
                .image(item.getImages())
                .sumPrice(item.getPrice())
                .payPrice(item.getPrice() * item.getRate() / 100)
                .type(type)
                .buyer(request.getSession().getAttribute("username").toString())
                .status(BizOrderConstant.NOT_PAY)
                .build();
    }
}
