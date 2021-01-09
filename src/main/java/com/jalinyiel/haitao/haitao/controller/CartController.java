package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.CartItemVo;
import com.jalinyiel.haitao.haitao.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 获取购物车中的商品
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/getCartItems", method = RequestMethod.GET)
    ResponseResult<List<CartItemVo>> getCartItems(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getSession().getAttribute("username").toString();
        List<CartItemVo> cartItemVos = cartService.getCartItems(username);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, cartItemVos);
    }

    /**
     * 往购物车中新增商品
     *
     * @param buyItem
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/addItemToCart", method = RequestMethod.POST)
    ResponseResult addItemsToCart(@RequestBody BuyItemsVo.BuyItem buyItem, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getSession().getAttribute("username").toString();
        if(cartService.addItemToCart(username, buyItem)) {
            return ResponseResult.successResult(CommonResultCode.SUCCESS);
        }
        return ResponseResult.failedResult(CommonResultCode.FAILED);
    }

    /**
     * 移除购物车中的商品
     *
     * @param buyItem
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/removeItemFromCart", method = RequestMethod.POST)
    ResponseResult removeItemsFromCart(@RequestBody BuyItemsVo.BuyItem buyItem, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getSession().getAttribute("username").toString();
        if(cartService.removeItemFromCart(username, buyItem)) {
            return ResponseResult.successResult(CommonResultCode.SUCCESS);
        }
        return ResponseResult.failedResult(CommonResultCode.FAILED);
    }

    /**
     * 调节购物车中的商品数量
     *
     * @param itemId
     * @param amount
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/adjustItemAmountInCart", method = RequestMethod.POST)
    ResponseResult<Integer> adjustItemAmountInCart(@RequestParam("itemId") Long itemId,
                                                   @RequestParam("amount") Integer amount,
                                                   HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getSession().getAttribute("username").toString();
        Integer row = cartService.adjustItemAmountInCart(username, itemId, amount);
        return ResponseResult.successResult(CommonResultCode.SUCCESS, row);
    }

}
