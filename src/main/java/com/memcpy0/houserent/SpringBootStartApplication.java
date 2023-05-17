//package com.memcpy0.houserent;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
///**
// * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
// */
//@Slf4j
//public class SpringBootStartApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        log.info("房屋租赁服务系统启动");
//        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(Application.class);
//    }
//}
