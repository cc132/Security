package com.example.demo.mybatis.service;

import com.example.demo.mybatis.domain.City;

public interface CityService {
    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);
}
