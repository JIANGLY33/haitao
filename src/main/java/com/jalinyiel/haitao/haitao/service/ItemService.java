package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;

import java.util.List;

public interface ItemService {

    Item getById(Long id) throws DaoException;

    Item update(Long id) throws DaoException;

    Long create(Item item) throws DaoException;

    Long delete(Long id) throws DaoException;

    List<Item> getByCategory(Long categoryId) throws DaoException;

}
