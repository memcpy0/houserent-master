package com.memcpy0.houserent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memcpy0.houserent.vo.HouseSearchVO;
import com.memcpy0.houserent.entity.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 房子数据访问层
 */
@Mapper
public interface HouseMapper extends BaseMapper<House> {

    /**
     * 根据出租类型获取最新的 n 条房子信息
     */
    @Select("select * from t_house where status = 0 and rent_type = #{rentType} order by create_time desc limit #{limit}")
    public List<House> findTopList(@Param("rentType")String rentType, @Param("limit")Integer limit);

    /**
     * 搜索房子
     * 一个参数的时候可以不写 param，两个以上一定要写
     * 比较复杂的sql，可以直接写在XML 里
     */
    public List<House> searchHouse(@Param("houseSearchVO") HouseSearchVO houseSearchVO, @Param("page") Page page);
}
