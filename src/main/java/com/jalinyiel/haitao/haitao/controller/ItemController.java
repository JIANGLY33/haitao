package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.CategoryNumVo;
import com.jalinyiel.haitao.haitao.model.vo.ItemVo;
import com.jalinyiel.haitao.haitao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * cz
 */
@CrossOrigin
@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    /**
     * 所有商品
     * @return
     */
    @RequestMapping(value = "/getallitems", method = RequestMethod.GET)
    ResponseResult getAll() {
        try {
            List<ItemVo> items = itemService.getAllItems();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, items);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    /**
     * 根据商品类型查询商品
     * @param id
     * @return
     */
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

    /**
     * cz: 根据活动id查询商品
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/getitemsbyactivity/{activityId}", method = RequestMethod.GET)
    ResponseResult getItemsByActivity(@PathVariable("activityId") Long activityId){
        try {
            List<ItemVo> items = itemService.getItemsByActivity(activityId);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, items);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/updateitem/{id}", method = RequestMethod.PUT)
    ResponseResult updateItem(Item item) {
        try {
            Item newItem = itemService.update(item);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newItem);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    ResponseResult addItem(@RequestBody Item item) {
        try {
            Item newItem = itemService.add(item);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newItem);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/delitem/{id}", method = RequestMethod.DELETE)
    ResponseResult delItem(@PathVariable("id") Long id) {
        try {
            Item item = itemService.delete(id);
            if(item != null) return ResponseResult.successResult(CommonResultCode.SUCCESS, item);
            else return ResponseResult.failedResult(CommonResultCode.FAILED, "not exist");
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

}
