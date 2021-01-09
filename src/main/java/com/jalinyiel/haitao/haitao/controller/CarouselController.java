package com.jalinyiel.haitao.haitao.controller;

import com.jalinyiel.haitao.haitao.common.CommonResultCode;
import com.jalinyiel.haitao.haitao.common.ResponseResult;
import com.jalinyiel.haitao.haitao.model.domain.Carousel;
import com.jalinyiel.haitao.haitao.model.exception.DaoException;
import com.jalinyiel.haitao.haitao.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @RequestMapping(value = "/getallcarousel",method = RequestMethod.GET)
    ResponseResult getAll() {
        try {
            List<Carousel> carousels = carouselService.getAll();
            return ResponseResult.successResult(CommonResultCode.SUCCESS, carousels);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/getcarouselbystatus/{id}",method = RequestMethod.GET)
    ResponseResult getCarouselsByStatus(@PathVariable("id") Integer id) {
        try {
            List<Carousel> carousels = carouselService.getCarouselsByStatus(id);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, carousels);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/updatecarousel",method = RequestMethod.PUT)
    ResponseResult updateCarousel(@RequestBody Carousel carousel) {
        try {
            Carousel newCarousel = carouselService.update(carousel);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newCarousel);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/addcarousel",method = RequestMethod.POST)
    ResponseResult addCarousel(@RequestBody Carousel carousel){
        try {
            Carousel newCarousel = carouselService.add(carousel);
            return ResponseResult.successResult(CommonResultCode.SUCCESS, newCarousel);
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/delcarousel/{id}",method = RequestMethod.DELETE)
    ResponseResult delCarousel(@PathVariable("id") Long id){
        try {
            Carousel carousel = carouselService.delete(id);
            if(carousel != null) return ResponseResult.successResult(CommonResultCode.SUCCESS, carousel);
            else return ResponseResult.failedResult(CommonResultCode.FAILED, "not exist");
        } catch (DaoException daoException) {
            return ResponseResult.failedResult(CommonResultCode.FAILED);
        }
    }
}
