package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController("itemCategory")
public class ItemCategoryController {

    /**
     * cz
     */
    @Autowired
    ItemCategoryService itemCategoryService;

    /**
     * cz: 获取所有类型
     * @return
     */
    @RequestMapping(value = "/getallcategorys", method = RequestMethod.GET)
    ResponseResult getAll() {
        try {
            List<ItemCategory> itemCategories = itemCategoryService.getAll();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, itemCategories);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/getcategory/{id}", method = RequestMethod.GET)
    ResponseResult getItemCategory(@PathVariable("id") Long id) {
        try {
            ItemCategory itemCategory = itemCategoryService.getItemCategory(id);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, itemCategory);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/updatecategory/{id}", method = RequestMethod.PUT)
    ResponseResult updateItemCategory(ItemCategory itemCategory) {
        try {
            ItemCategory newItemCategory = itemCategoryService.update(itemCategory);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newItemCategory);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/addcategory", method = RequestMethod.POST)
    ResponseResult addItemCategory(@RequestBody ItemCategory itemCategory) {
        try {
            ItemCategory newItemCategory = itemCategoryService.add(itemCategory);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newItemCategory);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/delcategory/{id}", method = RequestMethod.DELETE)
    ResponseResult delItemCategory(@PathVariable("id") Long id){
        try {
            ItemCategory itemCategory = itemCategoryService.delete(id);
            if(itemCategory != null) return ResponseResult.successResult(CommonResultCode.SUCCESS, itemCategory);
            else return ResponseResult.failedResult(CommonResultCode.FAILED, "not exist");
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

}
