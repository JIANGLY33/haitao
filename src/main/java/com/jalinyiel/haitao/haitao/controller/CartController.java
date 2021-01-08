package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.CartItemVo;
import com.jalinyiel.haitao.haitao.service.CartService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/getCartItems")
    ResponseResult<List<CartItemVo>> getCartItems(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getSession().getAttribute("username").toString();
        List<CartItemVo> cartItemVos = redisTemplate.boundListOps(username).range(0,-1);
        return ResponseResult.successResult(CommonResultCode.SUCCESS,cartItemVos);
    }

    @RequestMapping("/updateCartDetail")
    ResponseResult<List<CartItemVo>> updateCartDetail(@RequestBody BuyItemsVo buyItemsVo, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getSession().getAttribute("username").toString();
        List<CartItemVo> cartItemVos = buyItemsVo.getBuyItems().stream().map(this::generateCartItemVo).collect(Collectors.toList());
        cartItemVos.stream().forEach(cartItemVo -> {
            redisTemplate.boundListOps(username).leftPush(cartItemVo);
        });
        return ResponseResult.successResult(CommonResultCode.SUCCESS, cartItemVos);
    }

    private CartItemVo generateCartItemVo(BuyItemsVo.BuyItem buyItem) {
        CartItemVo cartItemVo = new CartItemVo();
        cartItemVo.setImage(buyItem.getItemImage());
        cartItemVo.setItemName(buyItem.getItemName());
        cartItemVo.setPrice(buyItem.getActualPrice());
        cartItemVo.setAmount(buyItem.getAmount());
        return cartItemVo;
    }
}
