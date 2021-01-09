package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.Cart;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.CartItemVo;

import java.util.List;

public interface CartService {

    List<CartItemVo> getCartItems(String username);

    Boolean addItemToCart(String username, BuyItemsVo.BuyItem buyItem);

    Boolean removeItemFromCart(String username, BuyItemsVo.BuyItem buyItem);

    Integer adjustItemAmountInCart(String username, Long itemId, Integer amount);
}
