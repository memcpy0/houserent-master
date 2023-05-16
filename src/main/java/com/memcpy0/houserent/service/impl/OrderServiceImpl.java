package com.memcpy0.houserent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memcpy0.houserent.enums.OrderStatusEnum;
import com.memcpy0.houserent.entity.Order;
import com.memcpy0.houserent.mapper.OrderMapper;
import com.memcpy0.houserent.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 订单服务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * mapper 对象
     * @return
     */
    @Override
    public BaseMapper<Order> getRepository() {
        return orderMapper;
    }

    /**
     * 获得查询器
     * @param order
     * @return
     */
    @Override
    public QueryWrapper<Order> getQueryWrapper(Order order) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (order != null && order.getCustomerUserId() != null) { // 订单中存在租户名字，则查询对应租户的订单
            queryWrapper.eq("customer_user_id", order.getCustomerUserId());
        }
        if (order != null && order.getOwnerUserId() != null) { // 订单中存在房东名字，则查询对应房东的订单
            queryWrapper.eq("owner_user_id", order.getOwnerUserId());
        }
        return queryWrapper;
    }
    /**
     * 获得带参数的查询器
     * @param condition
     * @return
     */
    @Override
    public QueryWrapper<Order> getQueryWrapper(Map<String, Object> condition) {
        return null;
    }

    /**
     * 查询当前有效订单
     */
    @Override
    public Order getCurrentEffectiveOrder(Long houseId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("house_id", houseId);
        queryWrapper.eq("status", OrderStatusEnum.NORMAL.getValue());
        return orderMapper.selectOne(queryWrapper);
    }
}
