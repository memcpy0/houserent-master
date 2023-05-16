package com.memcpy0.houserent.service;

import com.memcpy0.houserent.base.BaseService;
import com.memcpy0.houserent.entity.Order;

/**
 * 订单服务接口
 */
public interface OrderService extends BaseService<Order,Long> {

    /**
     * 查询当前有效订单
     */
    public Order getCurrentEffectiveOrder(Long houseId);
}
