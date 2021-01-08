package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.mapper.ItemMapper;
import com.jalinyiel.haitao.haitao.model.domain.Item;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.CategoryNumVo;
import com.jalinyiel.haitao.haitao.model.vo.ItemVo;
import com.jalinyiel.haitao.haitao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public List<ItemVo> getAllItems() throws DaoException {
        return itemMapper.findAllItems();
    }

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
        return itemMapper.findItemsByCategory(categoryId);
    }

    /**
     * cz: 查询所有类目对应的商品数目
     * @return
     * @throws DaoException
     */
    @Override
    public List<CategoryNumVo> getCategoryNum() throws DaoException {
        return itemMapper.getCategoryNum();
    }

    /**
     * cz: 根据价格查询商品
     * @return
     * @throws DaoException
     */
    @Override
    public List<Item> getItemUnderPrice(Long price) throws DaoException {
        return itemMapper.getItemUnderPrice(price);
    }

    /**
     * cz: 根据类型和价格筛选商品
     * @param categoryId
     * @param price
     * @return
     * @throws DaoException
     */
    @Override
    public List<Item> getItemByCategoryUnderPrice(Long categoryId, Long price) throws DaoException {
        return itemMapper.getItemByCategoryUnderPrice(categoryId, price);
    }

    /**
     * cz: 根据活动id查询商品
     * @param activityId
     * @return
     */
    @Override
    public List<ItemVo> getItemsByActivity(Long activityId) throws DaoException {
        return itemMapper.findItemsByActivity(activityId);
    }
}
