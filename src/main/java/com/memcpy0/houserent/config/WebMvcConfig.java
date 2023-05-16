package com.memcpy0.houserent.config;

import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.interceptor.AdminInterceptor;
import com.memcpy0.houserent.interceptor.CommonInterceptor;
import com.memcpy0.houserent.interceptor.CustomerInterceptor;
import com.memcpy0.houserent.interceptor.OwnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 前端配置类
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置静态资源访问路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // 获取文件的真实路径 work_project代表项目工程名 需要更改
        String os = System.getProperty("os.name");
        registry.addResourceHandler("/assets/**") // 静态资源路径以/assets开头
                .addResourceLocations("classpath:/static/assets/");

        if (os.toLowerCase().startsWith("win")) { // 在本地机器
            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:///" + System.getProperty("user.dir") + "/src/main/resources/static/assets/img/uploads/");
        } else { // linux和mac系统 可以根据逻辑再做处理
            registry.addResourceHandler("/uploads/**").
                    addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator")
                            + "uploads" + System.getProperty("file.separator"));
        }
    }

    /**
     * 配置JSP的访问前后缀
     * @return
     */
    @Bean
    public InternalResourceViewResolver setupViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        // jsp的访问前缀
        resolver.setPrefix("/jsp/");
        // jsp的访问后缀
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new CommonInterceptor())
                .addPathPatterns("/admin/profile") //  个人信息
                .addPathPatterns("/admin/profile/submit") //  个人信息修改
                .addPathPatterns("/admin/mark") //  收藏列表
                .addPathPatterns("/admin/mark/cancel") // 取消收藏
                .addPathPatterns("/admin/password") //  密码修改
                .addPathPatterns("/admin/password/submit") //  密码修改信息提交
                .addPathPatterns("/admin/home")  //  我的家
                .addPathPatterns("/admin/house") //  房子管理
                .addPathPatterns("/admin/publish") // 发布新房子
                .addPathPatterns("/admin/publish/submit")
                .addPathPatterns("/admin/down")
                .addPathPatterns("/admin/up") // 上架和下架房子
                .addPathPatterns("/admin/delete") // 删除房子
                .addPathPatterns("/admin/checkPass")
                .addPathPatterns("/admin/checkReject") // 审核通过或拒绝新发布的租房信息
                .addPathPatterns("/admin/order") //  订单管理 //  这些功能必须登录
                .addPathPatterns("/order/create") // 预定租房请求
                .addPathPatterns("/order/pay") // 处理支付请求
                .addPathPatterns("/mark/submit") // 收藏房子信息的请求(必须登录才能收藏)
                .addPathPatterns("/admin/user") //  用户管理
                .addPathPatterns("/admin/user/disable") // 启用/禁用用户
                .addPathPatterns("/admin/user/enable");

        registry.addInterceptor(new CustomerInterceptor())
                .addPathPatterns("/admin/home") //  我的家 //  这些功能必须是租户
                .addPathPatterns("/order/create") // 预定租房请求
                .addPathPatterns("/order/pay"); // 处理支付请求

        registry.addInterceptor(new OwnerInterceptor())
                .addPathPatterns("/admin/house") // 查询房子列表 // 这些功能必须是房东或管理员
                .addPathPatterns("/admin/publish") // 发布新房子
                .addPathPatterns("/admin/publish/submit")
                .addPathPatterns("/admin/down")
                .addPathPatterns("/admin/up") // 上架和下架房子
                .addPathPatterns("/admin/delete"); // 删除房子

        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/user") //  这些功能必须是管理员
                .addPathPatterns("/admin/user/disable") // 启用/禁用用户
                .addPathPatterns("/admin/user/enable")
                .addPathPatterns("/admin/checkPass")
                .addPathPatterns("/admin/checkReject"); // 审核通过或拒绝新发布的租房信息
    }
}
