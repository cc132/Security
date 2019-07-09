package com.example.demo.mybatis.dao;

import org.apache.ibatis.annotations.Param;

import com.example.demo.mybatis.domain.City;

public interface CityDao {
    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
}
