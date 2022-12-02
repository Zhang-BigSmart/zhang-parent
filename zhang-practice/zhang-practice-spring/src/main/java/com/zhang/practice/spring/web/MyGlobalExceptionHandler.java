package com.zhang.practice.spring.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> TODO
 *
 * @author 哲也（张梓濠 zheye.zhang@tuya.com）
 * @since 2021/4/9
 */
@ControllerAdvice
public class MyGlobalExceptionHandler {

    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }

    @ModelAttribute(name = "md2")
    public Map<String,Object> mydata2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 100);
        map.put("gender", "girl");
        return map;
    }
}
