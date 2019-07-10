package com.example.demo.multidatasource.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
    
    @Autowired
    @Qualifier("userTemplate")
    private RedisTemplate<String, User> redisTemplate;
    
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User findByName(String userName) {
    	ValueOperations<String, User> operations = redisTemplate.opsForValue();
    	User user = null;
        boolean hasKey = redisTemplate.hasKey(userName);
        System.out.println("hasKey is : " + hasKey);
        System.out.println("username is :" + userName);
        if(hasKey) {
        	user = operations.get(userName);
        	System.out.println("user:" + user.toString());
        	log.info("这是从redis中获取的数据");
        }else {
        	user = userDao.findByName(userName);
        	System.out.println("user:" + user.toString());
        	log.info("这是从mysql中获取的数据");
        	operations.set(userName, user);
        }
        System.out.println(user==null);
        City city = cityDao.findByName("盐城市");
        user.setCity(city);
        return user;
    }
}
