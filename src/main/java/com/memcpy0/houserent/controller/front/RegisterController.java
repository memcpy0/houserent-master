package com.memcpy0.houserent.controller.front;

import cn.hutool.json.JSON;
import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.dto.JsonResult;
import com.memcpy0.houserent.entity.User;
import com.memcpy0.houserent.service.UserService;
import com.memcpy0.houserent.utils.CreateCode;
import com.memcpy0.houserent.utils.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 注册控制器
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailClient mailClient;
    /**
     * 发送验证码
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult sendCode(String mail, HttpSession session) throws MessagingException, UnsupportedEncodingException {
        String code = CreateCode.createCode(6);
        String message = "您好,您的验证码是" + code;
        session.setAttribute("MessageCode", code); // 将验证码存入session中
        session.setMaxInactiveInterval(60 * 60);
        mailClient.sendMail(mail, message);
        return JsonResult.success("验证码已经发送");
    }

    /**
     * 注册提交
     *
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody       // 说明返回的是JSON结构，而不是一个地址
    public JsonResult registerSubmit(User user, HttpSession session) {
        String code = (String) session.getAttribute("MessageCode");
        if (StringUtils.isBlank(user.getVerifyCode())) {
            return JsonResult.error("必须填写验证码");
        }
        if (!StringUtils.equals(code, user.getVerifyCode())) {
            return JsonResult.error("验证码错误");
        }
        if (user.getUsername().equals("") || user.getUserPass().equals("")) {
            return JsonResult.error("必须填写用户名");
        }
        User user1 = userService.findByUserName(user.getUsername());
        if (user1 != null) {
            return JsonResult.error("用户已存在");
        }
        user.setIdCard("无");
        user.setUserAvatar("/assets/img/default-avatar.jpg");
        user.setUserDesc("无");
        user.setSex("无");
        user.setHobby("无");
        user.setJob("无");
        user.setCreateTime(new Date());
        user.setStatus(1); // 启用账号
        try {
            userService.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("注册失败");
        }
        // 把用户信息存到session中
//        session.setAttribute(Constant.SESSION_USER_KEY, user);
        return JsonResult.success("注册成功");
    }
}
