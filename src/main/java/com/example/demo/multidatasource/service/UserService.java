package com.example.demo.multidatasource.service;

import com.example.demo.multidatasource.domain.User;

public interface UserService {

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    User findByName(String userName);
}
