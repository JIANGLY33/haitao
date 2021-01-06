package com.jalinyiel.haitao.haitao.service;

import com.jalinyiel.haitao.haitao.model.domain.Carousel;

import java.util.List;

public interface CarouselService {

    List<Carousel> getCarouselsByStatus(Integer status);

}
