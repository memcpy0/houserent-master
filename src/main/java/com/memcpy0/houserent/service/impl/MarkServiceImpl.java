package com.memcpy0.houserent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memcpy0.houserent.mapper.MarkMapper;
import com.memcpy0.houserent.service.MarkService;
import com.memcpy0.houserent.entity.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 收藏 service 实现类
 */
@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkMapper markMapper;

    /**
     * mapper 对象
     *
     * @return
     */
    @Override
    public BaseMapper<Mark> getRepository() {
        return markMapper;
    }

    /**
     * 获得查询器
     *
     * @param mark
     * @return
     */

    @Override
    public QueryWrapper<Mark> getQueryWrapper(Mark mark) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (mark != null) {
            if (mark.getUserId() != null) {
                queryWrapper.eq("user_id", mark.getUserId());
            }
            if (mark.getHouseId() != null) {
                queryWrapper.eq("house_id", mark.getHouseId());
            }
        }
        return queryWrapper;
    }

    /**
     * 获得带参数的查询器
     * @param condition
     * @return
     */
    @Override
    public QueryWrapper<Mark> getQueryWrapper(Map<String, Object> condition) {
        return null;
    }

    /**
     * 根据用户id和房子的id查询该用户是否已经收藏过该房子
     */
    @Override
    public List<Mark> findUserIdAndHousId(Long userId, Long houseId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("house_id", houseId);
        return markMapper.selectList(queryWrapper);
    }
}
