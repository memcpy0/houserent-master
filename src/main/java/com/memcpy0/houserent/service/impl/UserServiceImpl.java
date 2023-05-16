package com.memcpy0.houserent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memcpy0.houserent.service.UserService;
import com.memcpy0.houserent.entity.User;
import com.memcpy0.houserent.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户接口实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * mapper 对象
     * @return
     */
    @Override
    public BaseMapper<User> getRepository() {
        return userMapper;
    }

    /**
     * 获得查询器
     */
    @Override
    public QueryWrapper<User> getQueryWrapper(User user) {
        return null;
    }

    /**
     * 获得带参数的查询器
     * @param condition
     * @return
     */
    @Override
    public QueryWrapper<User> getQueryWrapper(Map<String, Object> condition) {
        return null;
    }

    /**
     * 根据用户名查询用户
     * 等同于 select * from t_user where user_name = ?
     */
    @Override
    public User findByUserName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        return userMapper.selectOne(queryWrapper);
    }
}
