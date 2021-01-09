package com.jalinyiel.haitao.haitao.service.impl;

import com.jalinyiel.haitao.haitao.mapper.CarouselMapper;
import com.jalinyiel.haitao.haitao.model.domain.Carousel;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    CarouselMapper carouselMapper;

    @Override
    public List<Carousel> getCarouselsByStatus(Integer status) {
        return carouselMapper.findByStatus(status);
    }

    @Override
    public List<Carousel> getAll() {
        return carouselMapper.findAll();
    }

    @Override
    public Carousel update(Carousel carousel) throws DaoException {
        carouselMapper.createCarousel(carousel);
        return carouselMapper.findById(carousel.getId());
    }

    @Override
    public Carousel add(Carousel carousel) throws DaoException {
        carouselMapper.createCarousel(carousel);
        return carousel;
    }

    @Override
    public Carousel delete(Long id) throws DaoException {
        Carousel carousel = carouselMapper.findById(id);
        if(carousel != null){
            carouselMapper.delCarousel(id);
            return carousel;
        }
        return null;
    }
}
