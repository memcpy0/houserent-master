package com.memcpy0.houserent.controller.backend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.enums.OrderStatusEnum;
import com.memcpy0.houserent.utils.PageUtil;
import com.memcpy0.houserent.entity.Order;
import com.memcpy0.houserent.service.HouseService;
import com.memcpy0.houserent.service.OrderService;
import com.memcpy0.houserent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 我的家
 */
@Controller
public class HomeController extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;

    /**
     * 租客的房子信息列表，从订单中搜索对应信息
     */
    @RequestMapping("/admin/home")
    public String home(@RequestParam(value = "page",defaultValue = "1") Long pageNumber,
                       @RequestParam(value = "size",defaultValue = "6") Long pageSize,
                       Model model){
        Page page = PageUtil.initMpPage(pageNumber,pageSize);
        Order condition = new Order();
        condition.setCustomerUserId(getLoginUserId());
        condition.setStatus(OrderStatusEnum.NORMAL.getValue());
        Page<Order> orderPage = orderService.findAll(page, condition);
        for (Order order : orderPage.getRecords()) {
            order.setHouse(houseService.get(order.getHouseId()));
            order.setOwnerUser(userService.get(order.getOwnerUserId()));
        }
        model.addAttribute("pageInfo",orderPage);
        model.addAttribute("pagePrefix", "/admin/home?");
        model.addAttribute("tab", "home");
        return "admin/my-home";
    }
}
