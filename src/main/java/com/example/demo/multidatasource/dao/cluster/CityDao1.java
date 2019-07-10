package com.example.demo.multidatasource.dao.cluster;

import org.apache.ibatis.annotations.Param;

import com.example.demo.multidatasource.domain.City;

public interface CityDao1 {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
}
