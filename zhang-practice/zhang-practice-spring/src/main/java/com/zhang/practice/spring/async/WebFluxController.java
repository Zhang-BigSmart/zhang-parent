package com.zhang.practice.spring.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @ClassName WebFluxController
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/1 16:42
 */
@RestController
public class WebFluxController {

    @GetMapping("/hello")
    public Mono<String> hello() {   // 【改】返回类型为Mono<String>
        // 【改】使用Mono.just生成响应式数据
        return Mono.just("Welcome to reactive world ~");
    }
}
