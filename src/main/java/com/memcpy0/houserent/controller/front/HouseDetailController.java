package com.memcpy0.houserent.controller.front;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.enums.HouseRentTypeEnum;
import com.memcpy0.houserent.utils.PageUtil;
import com.memcpy0.houserent.vo.HouseSearchVO;
import com.memcpy0.houserent.entity.House;
import com.memcpy0.houserent.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 显示房子详情
 */
@Controller
public class HouseDetailController extends BaseController {
    @Autowired
    private HouseService houseService;

    /**
     * 房子详情
     */
    @RequestMapping("house/detail/{id}")
    public String HouseDetail(@PathVariable("id")long id, Model model){
        // 根据id查询房子
        House house = houseService.get(id);
        if(house == null) {
            return renderNotFond();
        }
        // 处理轮播图url
        List<String> slideList = JSON.parseArray(house.getSlideUrl(), String.class);
        house.setSlideImgList(slideList);
        // 如果合租，查询合租房子
        List<House> shareHouseList = houseService.findByUserIdAndCetificateNoAndRentType
                (house.getUserId(), house.getCetificateNo(), HouseRentTypeEnum.SHARE.getValue());
        house.setSlideImgList(slideList);
        model.addAttribute("house",house);
        return "front/house-detail";
    }

    /**
     * 房子列表
     */
    @RequestMapping("/house")
    public String houseList(HouseSearchVO houseSearchVO, Model model){
        Page page = PageUtil.initMpPage(houseSearchVO.getPage(), houseSearchVO.getSize());
        Page<House> housePage = houseService.getHousePage(houseSearchVO, page);
        model.addAttribute("pageInfo",housePage);
        model.addAttribute("houseSearchVo",houseSearchVO);
        model.addAttribute("pagePrefix",houseSearchVO.getPagePrefix());
        return "front/house-list";
    }
}
