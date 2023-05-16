package com.memcpy0.houserent.interceptor;

import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.entity.User;
import com.memcpy0.houserent.enums.UserRoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 租客接口拦截器
 */
@Component
public class CustomerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_KEY); //  如果用户未登录﹐拦截
        //  如果用户不是租客﹐拦截
        return UserRoleEnum.CUSTOMER.getValue().equalsIgnoreCase(user.getRole());
    }
}
