package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.CategoryNumVo;
import com.jalinyiel.haitao.haitao.model.vo.ItemVo;

import java.util.List;

public interface ItemService {

    Item getById(Long id) throws DaoException;

    Item update(Long id) throws DaoException;

    Long create(Item item) throws DaoException;

    Long delete(Long id) throws DaoException;

    List<Item> getByCategory(Long categoryId) throws DaoException;

    List<ItemVo> getAllItems() throws DaoException;

    /**
     * cz: 查询所有类目对应的商品数目
     * @return
     * @throws DaoException
     */
    List<CategoryNumVo> getCategoryNum() throws DaoException;

    /**
     * cz: 根据价格查询商品
     * @return
     * @throws DaoException
     */
    List<Item> getItemUnderPrice(Long price) throws DaoException;

    /**
     * cz: 根据类型和价格筛选商品
     * @param categoryId
     * @param price
     * @return
     * @throws DaoException
     */
    List<Item> getItemByCategoryUnderPrice(Long categoryId, Long price) throws DaoException;

    /**
     * cz: 根据活动id查询商品
     * @param activityId
     * @return
     */
    List<ItemVo> getItemsByActivity(Long activityId) throws DaoException;

}
