package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.CategoryNumVo;
import com.jalinyiel.haitao.haitao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@CrossOrigin
@RestController
public class ItemController {

    /**
     * cz
     * @return
     */
    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/getallitems", method = RequestMethod.GET)
    ResponseResult getAll() {
        try {
            List<Item> items = itemService.getAllItems();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, items);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/getallitembycategory/{id}", method = RequestMethod.GET)
    ResponseResult getItemByCategory(@PathVariable("id") Long id) {
        try {
            List<Item> items = itemService.getByCategory(id);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, items);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    /**
     * cz: 返回所有类目对应的商品数目
     * @return
     */
    @RequestMapping(value = "/getcategorynum", method = RequestMethod.GET)
    ResponseResult getCategoryNum() {
        try {
            List<CategoryNumVo> categoryNumVos = itemService.getCategoryNum();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, categoryNumVos);
        } catch (DaoException daoExpection) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    /**
     * cz: 根据价格查询商品
     * @return
     */
    @RequestMapping(value = "/getitemunderprice/{price}", method = RequestMethod.GET)
    ResponseResult getItemUnderPrice(@PathVariable("price") Long price){
        try {
            List<Item> items = itemService.getItemUnderPrice(price);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, items);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    /**
     * cz: 根据类型和价格筛选商品
     * @param categoryId
     * @param price
     * @return
     */
    @RequestMapping(value = "/getitembycategoryunderprice/{categoryId}/{price}", method = RequestMethod.GET)
    ResponseResult getgetItemByCategoryUnderPrice(@PathVariable("categoryId") Long categoryId, @PathVariable("price") Long price){
        try {
            List<Item> items = itemService.getItemByCategoryUnderPrice(categoryId, price);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, items);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

}
