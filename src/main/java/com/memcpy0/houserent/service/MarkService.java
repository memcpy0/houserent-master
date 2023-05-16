package com.memcpy0.houserent.service;

import com.memcpy0.houserent.base.BaseService;
import com.memcpy0.houserent.entity.Mark;

import java.util.List;

/**
 * 收藏 service 接口
 */
public interface MarkService extends BaseService<Mark,Long> {

    /**
     * 根据用户id和房子的id查询该用户是否已经收藏过该房子
     */
    List<Mark> findUserIdAndHousId(Long userId,Long houseId);
}
