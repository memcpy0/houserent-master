package com.memcpy0.houserent.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memcpy0.houserent.base.BaseService;
import com.memcpy0.houserent.vo.HouseSearchVO;
import com.memcpy0.houserent.entity.House;

import java.util.List;

/**
 * 房子服务层接口
 */

public interface HouseService extends BaseService<House,Long> {

    /**
     * 根据用户ID 和 房产证号来查询合租房子
     */
    List<House> findByUserIdAndCetificateNoAndRentType(long userId,String cetificateNo,String rentType);

    /**
     * 根据出租类型获取最新的 n 条房子信息
     */
    List<House> findTopList(String rentType,Integer limit);

    /**
     * 获得房子分页数据
     */
    Page<House> getHousePage(HouseSearchVO houseSearchVO, Page<House> page);
}
