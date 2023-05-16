package com.memcpy0.houserent.controller.front;

import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.dto.JsonResult;
import com.memcpy0.houserent.enums.HouseStatusEnum;
import com.memcpy0.houserent.utils.DateUtil;
import com.memcpy0.houserent.entity.House;
import com.memcpy0.houserent.entity.Order;
import com.memcpy0.houserent.entity.User;
import com.memcpy0.houserent.service.HouseService;
import com.memcpy0.houserent.service.OrderService;
import com.memcpy0.houserent.service.UserService;
import com.memcpy0.houserent.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 前端订单控制器
 */
@Controller
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

    /**
     * 创建订单
     */
    @RequestMapping("/order/create")
    @ResponseBody
    public JsonResult createOrder(
            @RequestParam("houseId") Long houseId,
            @RequestParam("endDate") String endDateStr) {
        if (getLoginUser() == null) {// 用户未登录
            return JsonResult.error("用户未登录");
        }
        House house = houseService.get(houseId);
        if (house == null) { // 房子不存在
            return JsonResult.error("房子不存在");
        }
        if (Objects.equals(house.getStatus(), HouseStatusEnum.HAS_RENT.getValue())) {
            return JsonResult.error("房子已租出或未释放");
        }
        // 是否下过订单
        Order checkOrser = orderService.getCurrentEffectiveOrder(houseId);
        if (checkOrser != null) {
            return JsonResult.error("房子已租出或未释放");
        }
        User ownerUser = userService.get(house.getUserId());
        if (ownerUser == null) {
            return JsonResult.error("房东用户不存在");
        }

        // 处理退租日期
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date endDate;
        try {
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return JsonResult.error("退租日期格式不合法");
        }

        Date startDate = new Date();
        //  计算总共多少天
        Integer dayNum = DateUtil.daysBetween(startDate, endDate);
        if (dayNum < Constant.MIN_RENT_DAYS) {
            return JsonResult.error("最少租住" + Constant.MIN_RENT_DAYS + "天");
        }
        // 创建订单
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setCustomerUserId(getLoginUserId());
        order.setOwnerUserId(house.getUserId());
        order.setHouseId(houseId);
        order.setStatus(OrderStatusEnum.NOT_PAY.getValue()); // 未支付
        order.setMonthRent(house.getMonthRent());
        order.setDayNum(dayNum);
        order.setTotalAmount(house.getMonthRent() / 30 * dayNum);
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        orderService.insert(order);
        return JsonResult.success("订单创建成功,支付订单", order.getId());
    }

    /**
     * 支付页面
     */
    @RequestMapping("/order/pay")
    public String pay(
            @RequestParam(value = "orderId") Long orderId, Model model) {
        if (getLoginUser() == null) {// 用户未登录
            return "redirect:/";
        }
        // 订单不存在
        Order order = orderService.get(orderId);
        if (order == null) {
            return renderNotFond();
        }
        // 登录用户既不是该订单的租客，也不是房东或者管理员，就不能看
        if (!Objects.equals(getLoginUserId(), order.getCustomerUserId()) &&
                !Objects.equals(getLoginUserId(), order.getOwnerUserId()) &&
                !loginUserIsAdmin()){
            return renderNotAllowAccsee();
        }
        House house = houseService.get(order.getHouseId());
        if (house == null) { // 房子不存在
            return renderNotFond();
        }
        // 该订单的房子信息
        order.setHouse(houseService.get(order.getHouseId()));
        model.addAttribute("order",order);
        return "front/pay";
    }

    /**
     * 模拟支付
     */
    @RequestMapping("/order/pay/submit")
    public JsonResult paySubmit(
            @RequestParam(value = "orderId") Long orderId) {
        // 用户未登录
        if (getLoginUser() == null) {
            return JsonResult.error("用户未登录");
        }
        // 订单不存在
        Order order = orderService.get(orderId);
        if (order == null) {
            return JsonResult.error("订单不存在");
        }

        House house = houseService.get(order.getHouseId());
        // 房子不存在
        if (house == null) {
            return JsonResult.error("房子不存在");
        }
        // 登录用户既不是该订单的租客，也不是房东或者管理员，就不能看
        if (!Objects.equals(getLoginUserId(), order.getCustomerUserId()) &&
                !Objects.equals(getLoginUserId(), order.getOwnerUserId()) &&
                !loginUserIsAdmin()){
            return JsonResult.error("没有权限");
        }
        if (Objects.equals(house.getStatus(), HouseStatusEnum.HAS_RENT.getValue())) {
            return JsonResult.error("房子已租出或未释放");
        }
        // 是否出有效的订单范围
        Order checkOrser = orderService.getCurrentEffectiveOrder(order.getHouseId());
        if (checkOrser != null) {
            return JsonResult.error("房子已租出或未释放");
        }
        User ownerUser = userService.get(house.getUserId());
        if (ownerUser == null) {
            return JsonResult.error("房东用户不存在");
        }
        order.setStatus(OrderStatusEnum.NORMAL.getValue());
        orderService.update(order);

        // 更新房子状态 和 开始结束的时间
        house.setStatus(HouseStatusEnum.HAS_RENT.getValue());
        house.setLastOrderStartTime(order.getStartDate());
        house.setLastOrderEndTime(order.getEndDate());

        houseService.update(house);
        return JsonResult.success("支付成功");
    }

}
