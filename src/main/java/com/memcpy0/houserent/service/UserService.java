package com.memcpy0.houserent.service;


import com.memcpy0.houserent.base.BaseService;
import com.memcpy0.houserent.entity.User;

/**
 * 用户服务层接口
 */
public interface UserService extends BaseService<User,Long> {

    /**
     * 根据用户名查询用户
     */
    public User findByUserName(String userName);
}
