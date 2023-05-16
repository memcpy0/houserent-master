package com.memcpy0.houserent.controller.backend;

import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.dto.JsonResult;
import com.memcpy0.houserent.entity.User;
import com.memcpy0.houserent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 个人信息控制器
 */
@Controller
@RequestMapping("/admin/profile")
public class ProfileController extends BaseController {
    @Autowired
    private UserService userService;
    /**
     * 个人信息页面
     */
    @RequestMapping("")
    public String profile(Model model){
        // 判断用户是否登录,拦截器
//        if(FilterUtil.isOrNotLogin(request.getSession())){
//            return "front/index";
//        }
        User user = getLoginUser();
        model.addAttribute("user",user);
        model.addAttribute("tab","my-profile");
        return "admin/my-profile";
    }

    /**
     * 个人信息保存提交
     */
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult submitProfile(User user, HttpSession session){
        // 判断用户是否登录,拦截器
        if (getLoginUser() == null) {
            return JsonResult.error("请先登录");
        }
        user.setId(getLoginUserId());
        userService.update(user);
        session.setAttribute(Constant.SESSION_USER_KEY, userService.get(getLoginUserId()));
        return JsonResult.success("保存成功");
    }
}
