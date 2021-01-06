package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public Item getById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Item update(Long id) throws DaoException {
        return null;
    }

    @Override
    public Long create(Item item) throws DaoException {
        return null;
    }

    @Override
    public Long delete(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<Item> getByCategory(Long categoryId) throws DaoException {
        return null;
    }
}
