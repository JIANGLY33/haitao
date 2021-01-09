package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.BizOrder;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.HistoryOrderVo;
import com.jalinyiel.haitao.haitao.model.vo.OrderItemVo;
import com.jalinyiel.haitao.haitao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "buy", method = RequestMethod.POST)
    public ResponseResult<Long> buy(@RequestBody BuyItemsVo buyItemsVos) {
        List<BuyItemsVo.BuyItem> buyItems = buyItemsVos.getBuyItems();
        if (null == buyItems || buyItems.size() < 1) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
        Long parentOrderId = orderService.buy(buyItemsVos);
        return ResponseResult.successResult(CommonResultCode.SUCCESS,parentOrderId);
    }

    @RequestMapping(value = "getOrderItems", method = RequestMethod.GET)
    public ResponseResult<List<OrderItemVo>> getOrderItems(@RequestParam("orderId") Long orderId) {
        List<OrderItemVo> buyItems = orderService.getOrderAllItems(orderId);
        return ResponseResult.successResult(CommonResultCode.SUCCESS,buyItems);
    }

    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public ResponseResult pay(@RequestParam("orderId") Long orderId) {
        if (orderService.pay(orderId)) {
            return ResponseResult.successResult(CommonResultCode.SUCCESS);
        }
        return ResponseResult.failedResult(CommonResultCode.FAILED);
    }

    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    public ResponseResult cancel(@RequestParam("orderId") Long orderId) {
        if (orderService.cancel(orderId)) {
            return ResponseResult.successResult(CommonResultCode.SUCCESS);
        }
        return ResponseResult.failedResult(CommonResultCode.FAILED);
    }

    @RequestMapping(value = "/getOrderByStatus", method = RequestMethod.GET)
    public ResponseResult<List<HistoryOrderVo>> getOrdersStatus(@RequestParam("status") Byte status) {
        List<HistoryOrderVo> historyOrderVos = orderService.getHisOrdersByStatus(status);
        return ResponseResult.successResult(CommonResultCode.SUCCESS,historyOrderVos);
    }

    @RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
    public ResponseResult<List<BizOrder>> getAllOrders(){
        List<BizOrder> bizOrders = orderService.getAllOrders();
        return ResponseResult.successResult(CommonResultCode.SUCCESS, bizOrders);
    }

}
