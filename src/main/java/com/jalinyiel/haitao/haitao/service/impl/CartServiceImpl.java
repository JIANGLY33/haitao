package com.jalinyiel.haitao.haitao.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalinyiel.haitao.haitao.mapper.CartMapper;
import com.jalinyiel.haitao.haitao.model.domain.Cart;
import com.jalinyiel.haitao.haitao.model.vo.BuyItemsVo;
import com.jalinyiel.haitao.haitao.model.vo.CartItemVo;
import com.jalinyiel.haitao.haitao.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<CartItemVo> getCartItems(String username) {
//        List<CartItemVo> cartItemVos = redisTemplate.boundListOps(username).range(0, -1);
        String value = (String) redisTemplate.boundValueOps(username).get();
        List<CartItemVo> cartItemVos = null;
        try {
            cartItemVos = jsonToCartItemVoList(value);
            if (cartItemVos.size() < 1) {
                Cart cart = cartMapper.findByOwner(username);

                cartItemVos = jsonToCartItemVoList(cart.getItemsDetail());

//                cartItemVos.stream().forEach(cartItemVo -> {
//                    redisTemplate.boundListOps(username).leftPush(cartItemVo);
//                });

                redisTemplate.boundValueOps(username).set(cart.getItemsDetail());
            }
        } catch (JsonProcessingException e) {
            log.error("failed to parse cart string.", e);
            return new ArrayList<>();
        }

        return cartItemVos;
    }

    @Override
    public Boolean addItemToCart(String username, BuyItemsVo.BuyItem buyItem) {
        if (null == buyItem) {
            return false;
        }
        Cart cart = cartMapper.findByOwner(username);

        if (null == cart) {
            cart = initCart(username);
            cartMapper.insertCart(cart);
        }
        Integer count = 0;
        try {
//            List<CartItemVo> cartItemVos = redisTemplate.boundListOps(username).range(0, -1);
            String cacheItems = (String) redisTemplate.boundValueOps(username).get();
            List<CartItemVo> cartItemVos = null;
            if (!"".equals(cart.getItemsDetail())) {
                cartItemVos = jsonToCartItemVoList(cart.getItemsDetail());
            } else {
                cartItemVos = new ArrayList<>();
            }

            CartItemVo cartItemVo = generateCartItemVo(buyItem);
            //新增商品在list中的下标
            int index = -1;
            for (int i = 0; i < cartItemVos.size(); i++) {
                if (cartItemVos.get(i).getItemId().equals(buyItem.getItemId())) {
                    index = i;
                    break;
                }
            }
            //新增商品存在于购物车中
            if (-1 != index) {
                //更新redis中的商品数量
                CartItemVo itemInCart = cartItemVos.get(index);
                itemInCart.setAmount(itemInCart.getAmount() + buyItem.getAmount());
                cartItemVos.set(index, itemInCart);
            } else { //新增商品不存在于购物车中
                cartItemVos.add(cartItemVo);
            }
            String newCartDetail = cartItemVosToJson(cartItemVos);
            redisTemplate.boundValueOps(username).set(newCartDetail);
            cartMapper.updateItemDetail(cartItemVosToJson(cartItemVos), username);
        } catch (JsonProcessingException e) {
            log.error("failed to parse cart items.", e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean removeItemFromCart(String username, BuyItemsVo.BuyItem buyItem) {
        if (null == buyItem) return false;
        Cart cart = cartMapper.findByOwner(username);
        try {
            List<CartItemVo> cartItemVos = jsonToCartItemVoList(cart.getItemsDetail());
            CartItemVo cartItemVo = generateCartItemVo(buyItem);
//            List<CartItemVo> cacheCartItems = redisTemplate.boundListOps(username).range(0, -1);
            List<CartItemVo> cacheCartItems =
                    jsonToCartItemVoList((String) redisTemplate.boundValueOps(username).get());
            int index = -1;
            for (int i = 0; i < cartItemVos.size(); i++) {
                if (cartItemVos.get(i).getItemId().equals(buyItem.getItemId())) {
//                    cartItemVo.setPrice(cacheCartItems.get(i).getPrice());
//                    cartItemVo.setItemName(cacheCartItems.get(i).getItemName());
//                    cartItemVo.setImage(cacheCartItems.get(i).getImage());
//                    cartItemVo.setAmount(cacheCartItems.get(i).getAmount());
                    index = i;
                    break;
                }
            }
            if (-1 == index) return false;
            cartItemVos.remove(index);
            String newCartDetail = cartItemVosToJson(cartItemVos);
//            Long res = redisTemplate.boundListOps(username).remove(1, cartItemVo);
            redisTemplate.boundValueOps(username).set(newCartDetail);
            cartMapper.updateItemDetail(newCartDetail, username);
        } catch (JsonProcessingException e) {
            log.error("failed to parse cart items.", e);
            return false;
        }
        return true;
    }

    @Override
    public Integer adjustItemAmountInCart(String username, Long itemId, Integer amount) {
        try {
            Cart cart = cartMapper.findByOwner(username);
            List<CartItemVo> cartItemVos = jsonToCartItemVoList(cart.getItemsDetail());
            CartItemVo cartItemVo = null;
            int i = 0;
            for (; i < cartItemVos.size(); i++) {
                if (cartItemVos.get(i).getItemId().equals(itemId)) {
                    cartItemVo = cartItemVos.get(i);
                    break;
                }
            }
            //todo amount数值校验
            cartItemVo.setAmount(cartItemVo.getAmount() + amount);
            String newCartJson = cartItemVosToJson(cartItemVos);
            redisTemplate.boundValueOps(username).set(newCartJson);
            cartMapper.updateItemDetail(newCartJson, username);
        } catch (JsonProcessingException e) {
            return 0;
        }
        return 1;
    }

    private String cartItemVosToJson(List<CartItemVo> cartItemVos) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(cartItemVos);
        return result;
    }

    private List<CartItemVo> jsonToCartItemVoList(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, CartItemVo.class);
        List<CartItemVo> cartItemVos = objectMapper.readValue(json, javaType);
        return cartItemVos;
    }

    private CartItemVo generateCartItemVo(BuyItemsVo.BuyItem buyItem) {
        CartItemVo cartItemVo = new CartItemVo();
        cartItemVo.setItemId(buyItem.getItemId());
        cartItemVo.setImage(buyItem.getItemImage());
        cartItemVo.setItemName(buyItem.getItemName());
        cartItemVo.setPrice(buyItem.getActualPrice());
        cartItemVo.setAmount(buyItem.getAmount());
        return cartItemVo;
    }

    private Cart initCart(String username) {
        Cart cart = new Cart();
        cart.setOwner(username);
        cart.setItemsDetail("");
        return cart;
    }
}
