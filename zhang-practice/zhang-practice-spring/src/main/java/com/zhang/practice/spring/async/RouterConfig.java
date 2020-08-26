//package com.zhang.practice.spring.async;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//
///**
// * @ClassName RouterConfig
// * @Description:
// * @Author: zhangzh
// * @Date 2018/10/1 16:59
// */
//@Configuration
//public class RouterConfig {
//
//    @Autowired
//    private TimeHandler timeHandler;
//
//    @Bean
//    public RouterFunction<ServerResponse> timerRouter() {
//        // 这种方式相对于上一行更加简洁
//        return route(GET("/time"), req -> timeHandler.getTime(req))
//                .andRoute(GET("/date"), timeHandler::getDate);
//    }
//}