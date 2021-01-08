package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ItemCategoryController {

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

}
