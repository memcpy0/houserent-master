package com.memcpy0.houserent.controller.backend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.dto.JsonResult;
import com.memcpy0.houserent.utils.PageUtil;
import com.memcpy0.houserent.entity.Mark;
import com.memcpy0.houserent.service.HouseService;
import com.memcpy0.houserent.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

/**
 * 收藏查询和取消收藏
 *
 */
@Controller
public class AdminMarkController extends BaseController {
    @Autowired
    private MarkService markService;

    @Autowired
    private HouseService houseService;
    /**
     * 我的收藏列表
     */
    @RequestMapping("/admin/mark")
    public String markList(@RequestParam(value = "page",defaultValue = "1") Long pageNumber,
                           @RequestParam(value = "size",defaultValue = "6") Long pageSize,
                           Model model) {
        if(getLoginUser() == null){
            return "front/index";
        }
        Page page = PageUtil.initMpPage(pageNumber, pageSize);
        Mark condition = new Mark();
        condition.setUserId(getLoginUserId());
        Page<Mark> markPage = markService.findAll(page,condition);
        for(Mark mark:markPage.getRecords()){
            mark.setHouse(houseService.get(mark.getHouseId()));
        }
        model.addAttribute("pageInfo",markPage);
        model.addAttribute("tab","mark-list");
        model.addAttribute("pagePrefix","/admin/mark?");
        return "admin/mark-list";
    }

    /**
     * 取消收藏
     */
    @RequestMapping(value = "/admin/mark/cancel",method = RequestMethod.POST)
    public JsonResult cancelMark(@RequestParam("id")Long id){
        Mark mark = markService.get(id);
        if(mark == null|| Objects.equals(mark.getUserId(),getLoginUser())){
            return JsonResult.error("取消收藏失败");
        }
        markService.delete(id);
        return JsonResult.success("取消收藏成功");
    }
}
