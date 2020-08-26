package com.zhang.practice.spring.async;

import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Set;

/**
 * @author : zzh
 * create at:  2020/8/6
 * @description:
 */
@RestController
@RequestMapping("/deferred")
public class DeferredResultController {

    final Map deferredResultMap = new ConcurrentReferenceHashMap<>();

    @GetMapping
    public DeferredResult<Object> pollNotification() {
        DeferredResult<Object> result = new DeferredResult();
        deferredResultMap.put(result.hashCode(), result);
        System.out.println("quest:" + result.hashCode());

        result.onCompletion(() -> {
            Object object = deferredResultMap.remove(result.hashCode());
            result.setResult(object);
            System.err.println("还剩" + deferredResultMap.size() + "个deferredResult未响应");
        });

        /*ForkJoinPool.commonPool().submit(() -> {
            System.out.println("Processing in separate thread");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
            result.setResult(ResponseEntity.ok("ok"));
        });*/
        return result;
    }


    @GetMapping("/return")
    public void returnLongPollingValue() {
        Set<Map.Entry> entrySet = deferredResultMap.entrySet();
        System.out.println("set return");
        for (Map.Entry entry : entrySet) {
            System.out.println("key:" + entry.getKey());
            entry.setValue(entry.getKey());
        }
    }
}
