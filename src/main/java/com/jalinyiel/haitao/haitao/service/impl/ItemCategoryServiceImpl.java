package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.mapper.ItemCategoryMapper;
import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    ItemCategoryMapper itemCategoryMapper;

    @Override
    public List<ItemCategory> getAll() throws DaoException {
        return itemCategoryMapper.getAll();
    }

    @Override
    public ItemCategory getItemCategory(Long id) throws DaoException {
        return itemCategoryMapper.findById(id);
    }

    @Override
    public ItemCategory update(ItemCategory itemCategory) throws DaoException {
        itemCategoryMapper.updateItemCategory(itemCategory);
        return itemCategoryMapper.findById(itemCategory.getId());
    }

    @Override
    public ItemCategory add(ItemCategory itemCategory) throws DaoException {
        itemCategoryMapper.createItemCategory(itemCategory);
        return itemCategory;
    }

    @Override
    public ItemCategory delete(Long id) throws DaoException {
        ItemCategory itemCategory = itemCategoryMapper.findById(id);
        if(itemCategory != null){
            itemCategoryMapper.delItemCategory(id);
            return itemCategory;
        }
        return null;
    }
}
