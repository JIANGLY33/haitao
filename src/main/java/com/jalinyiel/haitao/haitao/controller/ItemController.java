package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
