package com.memcpy0.houserent.base;


import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.enums.UserRoleEnum;
import com.memcpy0.houserent.entity.User;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 所有controller控制器的基类
 */
@Controller
public class BaseController {

    @Resource
    protected HttpServletRequest request;

    /**
     * 获得当前登录用户
     */
    public User getLoginUser(){
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER_KEY);
        return user;
    }

    /**
     * 获得当前用户ID
     */
    public Long getLoginUserId(){
        User user = (User)request.getSession().getAttribute(Constant.SESSION_USER_KEY);
        if(user == null){
            return null;
        }
        return user.getId();
    }

    /**
     * 当前用户是管理员
     */
    public Boolean loginUserIsAdmin(){
        User user = getLoginUser();
        if(user == null){
            return false;
        }
        return UserRoleEnum.ADMIN.getValue().equalsIgnoreCase(user.getRole());
    }

    /**
     * 当前用户是租户
     */
    public Boolean loginUserIsCustomer(){
        User user = getLoginUser();
        if(user == null){
            return false;
        }
        return UserRoleEnum.CUSTOMER.getValue().equalsIgnoreCase(user.getRole());
    }

    /**
     * 当前用户是房东
     */
    public Boolean loginUserIsOwner(){
        User user = getLoginUser();
        if(user == null){
            return false;
        }
        return UserRoleEnum.OWNER.getValue().equalsIgnoreCase(user.getRole());
    }

    /**
     * 渲染404页面
     */
    public String renderNotFond(){
        return "forward:/404";
    }

    /**
     * 没有权限
     */
    public String renderNotAllowAccsee(){
        return "forward:/403";
    }

    /**
     * 服务器异常
     */
    public String renderServerException(){
        return "forward:/500";
    }

    /**
     * 其他错误
     */
    public String renderError(){
        return "forward:/error";
    }
}
