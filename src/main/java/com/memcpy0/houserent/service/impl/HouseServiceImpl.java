package com.memcpy0.houserent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memcpy0.houserent.vo.HouseSearchVO;
import com.memcpy0.houserent.entity.House;
import com.memcpy0.houserent.mapper.HouseMapper;
import com.memcpy0.houserent.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 房子接口实现
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    /**
     * mapper 对象
     *
     * @return
     */
    @Override
    public BaseMapper<House> getRepository() {
        return houseMapper;
    }

    /**
     * 获得查询器
     */
    @Override
    public QueryWrapper<House> getQueryWrapper(House house) {
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        if (house != null && house.getUserId() != null) {
            queryWrapper.eq("user_id", house.getUserId());
        }
        return queryWrapper;
    }

    /**
     * 获得带参数的查询器,提供一个无用的实现
     *
     * @param condition
     * @return
     */
    @Override
    public QueryWrapper<House> getQueryWrapper(Map<String, Object> condition) {
        return null;
    }


    /**
     * 根据用户ID和房产证号来查询合租房子
     */
    @Override
    public List<House> findByUserIdAndCetificateNoAndRentType(long userId, String cetificateNo, String rentType) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("cetificate_no", cetificateNo);
        queryWrapper.eq("rent_type", rentType);
        return houseMapper.selectList(queryWrapper);
    }


    /**
     * 根据出租类型获取最新的 n 条房子信息
     */
    @Override
    public List<House> findTopList(String rentType, Integer limit) {
        return houseMapper.findTopList(rentType, limit);
    }


    /**
     * 获得房子分页数据
     */
    @Override
    public Page<House> getHousePage(HouseSearchVO houseSearchVO, Page<House> page) {
        if (houseSearchVO == null) {
            houseSearchVO = new HouseSearchVO();
        }
        List<House> list = houseMapper.searchHouse(houseSearchVO, page);
        page.setRecords(list);
        return page;
    }
}
