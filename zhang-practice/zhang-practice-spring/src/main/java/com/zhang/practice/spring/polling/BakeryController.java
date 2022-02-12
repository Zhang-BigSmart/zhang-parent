package com.zhang.practice.spring.polling;

import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

/**
 * @author 哲也
 * @className BakeryController
 * @desc
 * @since 2021/12/13
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class BakeryController {

    public static final Integer bakeTime = 10000;

    //guava中的Multimap，多值map,对map的增强，一个key可以保持多个value
    private Multimap<String, DeferredResult<String>> watchRequests = Multimaps.synchronizedSetMultimap(HashMultimap.create());


    //public static final ConcurrentHashMap<String, DeferredResult> map = new ConcurrentHashMap<>();

    @GetMapping("/bake/{namespace}")
    public DeferredResult<String> publisher(@PathVariable String namespace) {
        log.info("Request received");
        DeferredResult<String> deferredResult = new DeferredResult<>(1000L);
        deferredResult.onTimeout(() -> {
            watchRequests.remove(namespace, deferredResult);
            deferredResult.setResult("timeout");
        });
        //当deferredResult完成时（不论是超时还是异常还是正常完成），移除watchRequests中相应的watch key
        deferredResult.onCompletion(() -> {
            System.out.println("remove key:" + namespace);
            watchRequests.remove(namespace, deferredResult);
        });
        watchRequests.put(namespace, deferredResult);
        log.info("Servlet thread released");
        return deferredResult;
    }

    @GetMapping("/bake/{namespace}/update")
    public String test(@PathVariable String namespace) {
        if (watchRequests.containsKey(namespace)) {
            Collection<DeferredResult<String>> deferredResults = watchRequests.get(namespace);
            Long time = System.currentTimeMillis();
            //通知所有watch这个namespace变更的长轮训配置变更结果
            for (DeferredResult<String> deferredResult : deferredResults) {
                deferredResult.setResult(namespace + " changed:" + time);
            }
        }
        return "success";
    }
}
