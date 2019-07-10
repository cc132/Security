package com.example.demo.multidatasource.dao.master;

import org.apache.ibatis.annotations.Param;

import com.example.demo.multidatasource.domain.User;

public interface UserDao {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    User findByName(@Param("userName") String userName);
}
