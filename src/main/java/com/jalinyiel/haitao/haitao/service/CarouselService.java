package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.Activity;
import com.jalinyiel.haitao.haitao.model.domain.Carousel;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.model.vo.CarouselVo;

import java.util.List;

public interface CarouselService {

    List<Carousel> getAll() throws DaoException;

    List<Carousel> getCarouselsByStatus(Integer status) throws DaoException;

    Carousel update(Carousel carousel) throws DaoException;

    Carousel add(Carousel carousel) throws DaoException;

    Carousel delete(Long id) throws DaoException;

    List<CarouselVo> getAllCarouselWithItem() throws DaoException;

}
