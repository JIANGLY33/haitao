package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("order")
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

}
