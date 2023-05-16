package com.memcpy0.houserent.controller.front;

import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.enums.HouseRentTypeEnum;
import com.memcpy0.houserent.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端首页控制器
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    private HouseService houseService;
    /**
     * 前端首页
     */
    @RequestMapping("/")
    public String index(Model model){
        // 最新整租
        model.addAttribute("recentWholeHouseList",houseService.findTopList(HouseRentTypeEnum.WHOLE.getValue(), Constant.INDEX_HOUSE_NUM));
        // 最新整租
        model.addAttribute("recentShareHouseList",houseService.findTopList(HouseRentTypeEnum.SHARE.getValue(), Constant.INDEX_HOUSE_NUM));
        return "front/index";
    }
}
