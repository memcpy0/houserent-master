package com.memcpy0.houserent.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memcpy0.houserent.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
