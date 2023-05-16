package com.memcpy0.houserent.vo;

import lombok.Data;

/**
 * 房子搜索封装参数
 */
@Data
public class HouseSearchVO {
    /* 页码 */
    private Integer page = 1;
    /* 页面大小 */
    private Integer size = 6;
    /* 房子出租状态 0未租出,1已租出,-1已下架,-2待审核,-3审核不通过 */
    private Integer status = 0;
    /* 租房类型 */
    private String rentType="";
    /**
     * 获得分页前缀参数
     * @return
     */
    public String getPagePrefix(){
        StringBuffer sb = new StringBuffer();
        sb.append("?rentType=").append(this.rentType);
        return sb.toString();
    }
}
