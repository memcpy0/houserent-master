package com.memcpy0.houserent.controller.backend;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.dto.JsonResult;
import com.memcpy0.houserent.entity.User;
import com.memcpy0.houserent.enums.HouseStatusEnum;
import com.memcpy0.houserent.enums.OrderStatusEnum;
import com.memcpy0.houserent.utils.DateUtil;
import com.memcpy0.houserent.utils.PageUtil;
import com.memcpy0.houserent.entity.House;
import com.memcpy0.houserent.entity.Order;
import com.memcpy0.houserent.service.HouseService;
import com.memcpy0.houserent.service.OrderService;
import com.memcpy0.houserent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Objects;

/**
 * 后端订单控制器
 */
@Controller
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

    /**
     * 订单列表
     */
    @RequestMapping("")
    public String allOrder(@RequestParam(value = "page", defaultValue = "1") Long pageNumber,
                           @RequestParam(value = "size", defaultValue = "6") Long pageSize,
                           Model model) {
        Page page = PageUtil.initMpPage(pageNumber, pageSize);
        Order condition = new Order();
        User user = getLoginUser();
        if (user == null) {
            return "front/index";
        }

        // 如果登录用户是租客，查询租客的订单
        if (loginUserIsCustomer()) {
            condition.setCustomerUserId(getLoginUserId());
        }
        // 如果登录用户是房东，查询该房东租客的订单
        if (loginUserIsOwner()) {
            condition.setOwnerUserId(getLoginUserId());
        }

        // 如果登录用户是管理员，查询所有的订单
        Page<Order> orderPage = orderService.findAll(page, condition);
        for (Order order : orderPage.getRecords()) {
            order.setHouse(houseService.get(order.getHouseId()));
            order.setOwnerUser(userService.get(order.getOwnerUserId()));
            order.setCustomerUser(userService.get(order.getCustomerUserId()));
        }

        model.addAttribute("pageInfo", orderPage);
        model.addAttribute("pagePrefix", "/admin/order?");
        model.addAttribute("tab", "order-list");
        return "admin/order-list";
    }


    /**
     * 取消订单
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public JsonResult cancelOrder(@RequestParam("orderId") Long orderId) {
        // 校验订单是否存在
        Order order = orderService.get(orderId);
        if (order == null) {
            return JsonResult.error("订单不存在");
        }
        // 登录用户既不是管理员，也不是该订单的租客 房东，就不能取消订单
        if (!loginUserIsAdmin() && !Objects.equals(getLoginUserId(), order.getCustomerUserId())
                && !Objects.equals(getLoginUserId(), order.getOwnerUserId())) {
            return JsonResult.error("没有权限");
        }
        // 取消
        order.setStatus(OrderStatusEnum.CUSTOMER_CANCEL.getValue());
        orderService.update(order);
        House house = houseService.get(order.getHouseId());
        house.setStatus(HouseStatusEnum.NOT_RENT.getValue());
        houseService.update(house);
        return JsonResult.success("取消订单成功");
    }

    /**
     * 申请退租
     */
    @RequestMapping(value = "/end", method = RequestMethod.POST)
    public JsonResult endOrder(@RequestParam("orderId") Long orderId) {
        // 校验订单是否存在
        Order order = orderService.get(orderId);
        if (order == null) {
            return JsonResult.error("订单不存在");
        }
        // 登录用户既不是管理员，也不是该订单的租客 房东，就不能取消订单
        if (!loginUserIsAdmin() && !Objects.equals(getLoginUserId(), order.getCustomerUserId())
                && !Objects.equals(getLoginUserId(), order.getOwnerUserId())) {
            return JsonResult.error("没有权限");
        }
        // 取消
        order.setStatus(OrderStatusEnum.END_APPLY.getValue());
        orderService.update(order);
        return JsonResult.success("退租申请已提交，请联系房东审核");
    }

    /**
     * 退租申请通过
     */
    @RequestMapping(value = "/endPass", method = RequestMethod.POST)
    public JsonResult endOrderPass(@RequestParam("orderId") Long orderId) {
        // 校验订单是否存在
        Order order = orderService.get(orderId);
        if (order == null) {
            return JsonResult.error("订单不存在");
        }
        // 登录用户既不是管理员，也不是该订单的房东，就不能取消订单
        if (!loginUserIsAdmin() && !Objects.equals(getLoginUserId(), order.getOwnerUserId())) {
            return JsonResult.error("没有权限");
        }
        // 取消
        order.setStatus(OrderStatusEnum.FINISH.getValue());
        int dayNum = DateUtil.daysBetween(order.getStartDate(), new Date());
        order.setDayNum(dayNum);
        order.setTotalAmount(order.getMonthRent() / 30 * dayNum);
        order.setEndDate(new Date());
        orderService.update(order);

        // 房子状态重置为未出租
        House house = houseService.get(order.getHouseId());
        house.setStatus(HouseStatusEnum.NOT_RENT.getValue());
        house.setLastOrderEndTime(new Date());
        houseService.update(house);
        return JsonResult.success("退租申请已通过");
    }

    /**
     * 退租申请拒绝
     */
    @RequestMapping(value = "/endReject", method = RequestMethod.POST)
    public JsonResult endOrderReject(@RequestParam("orderId") Long orderId) {
        // 校验订单是否存在
        Order order = orderService.get(orderId);
        if (order == null) {
            return JsonResult.error("订单不存在");
        }
        // 登录用户既不是管理员，也不是该订单的房东，就不能取消订单
        if (!loginUserIsAdmin() && !Objects.equals(getLoginUserId(), order.getOwnerUserId())) {
            return JsonResult.error("没有权限");
        }
        // 取消
        order.setStatus(OrderStatusEnum.END_APPLY_REJCT.getValue());
        orderService.update(order);

        return JsonResult.success("操作成功");
    }
}

