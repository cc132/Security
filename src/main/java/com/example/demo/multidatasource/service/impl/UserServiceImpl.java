package com.example.demo.multidatasource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.multidatasource.dao.cluster.CityDao1;
import com.example.demo.multidatasource.dao.master.UserDao;
import com.example.demo.multidatasource.domain.City;
import com.example.demo.multidatasource.domain.User;
import com.example.demo.multidatasource.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao1 cityDao; // 从数据源
    
    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("盐城市");
        user.setCity(city);
        return user;
    }
}
