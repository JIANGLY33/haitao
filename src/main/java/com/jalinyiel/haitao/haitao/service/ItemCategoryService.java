package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.ItemCategory;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;

import java.util.List;

public interface ItemCategoryService {

    List<ItemCategory> getAll() throws DaoException;

}
