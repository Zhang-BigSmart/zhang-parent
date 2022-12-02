package com.zhang.practice.spring.async;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : zzh
 * create at:  2020/8/6
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/deferred")
public class DeferredResultController {

    // guava中的Multimap，多值map,对map的增强，一个key可以保持多个value
    private final Multimap<String, DeferredResult<String>> deferredResultMap = HashMultimap.create();
    // 超时时间 10s
    private long DEFAULT_LONG_POLLING_TIMEOUT = 10 * 1000;

    /**
     * 模拟监听namespace配置
     */
    @GetMapping("/listen")
    public DeferredResult<String> pollNotification(@RequestParam("namespace") String namespace) {
        // 创建DeferredResult对象，设置超时时间和超时返回对象
        DeferredResult<String> result = new DeferredResult<>(DEFAULT_LONG_POLLING_TIMEOUT, "timeout");

        result.onTimeout(() -> log.info("timeout"));

        result.onCompletion(() -> {
            log.info("completion");
            deferredResultMap.remove(namespace, result);
        });

        deferredResultMap.put(namespace, result);
        return result;
    }

    /**
     * 模拟发布namespace配置
     */
    @GetMapping(value = "/publish")
    public Object publishConfig(@RequestParam("namespace") String namespace, @RequestParam("context") String context) {
        if (deferredResultMap.containsKey(namespace)) {
            Collection<DeferredResult<String>> deferredResults = deferredResultMap.get(namespace);
            for (DeferredResult<String> deferredResult : deferredResults) {
                deferredResult.setResult(context);
            }
        }
        return "success";
    }
}
