package com.memcpy0.houserent.controller.front;

import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.dto.JsonResult;
import com.memcpy0.houserent.enums.UserStatusEnum;
import com.memcpy0.houserent.entity.User;
import com.memcpy0.houserent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录相关的控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    /**
     * 登录提交
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody // 说明返回的是JSON结构，而不是一个地址
    public JsonResult loginSubmit(User user, HttpSession session) {
        if (user.getUsername() == null || user.getUserPass() == null) {
            return JsonResult.error("请输入用户名或密码");
        }
        User user1 = userService.findByUserName(user.getUsername());
        if (user1 == null) {
            return JsonResult.error("用户不存在");
        }
        //判断密码是否正确
        if (!user.getUserPass().equals(user1.getUserPass())) {
            return JsonResult.error("密码错误");
        }
        if (UserStatusEnum.DISABLE.getValue().equals(user1.getStatus())) {
            return JsonResult.error("账户已被冻结，请联系管理员");
        }
        // 把用户信息存到session中
        session.setAttribute(Constant.SESSION_USER_KEY, user1);
        return JsonResult.success("登录成功");
    }

    /**
     * 退出登录，返回首页
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Constant.SESSION_USER_KEY);
        session.invalidate();
        return "redirect:/";
    }
}
