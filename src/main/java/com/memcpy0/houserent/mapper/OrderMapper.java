package com.memcpy0.houserent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memcpy0.houserent.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
