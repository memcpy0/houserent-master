package com.memcpy0.houserent.interceptor;

import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.entity.User;
import com.memcpy0.houserent.enums.UserRoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用拦截器，必须登录后有用户信息才能访问
 */
@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER_KEY); // 如果用户未登录﹐拦截
        if (user == null) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}