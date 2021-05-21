package com.zhang.practice.spring.web;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/user/{id}")
    public Result<Mapper> get(@PathVariable Integer id, Model model) {

        Map<String, Object> map = model.asMap();
        System.out.println(map);

        Result<Mapper> result = new Result<>();

        Mapper mapper = new Mapper();
        mapper.setId(id);
        mapper.setName("name_" + id);
        mapper.setFirst_name("first_name_" + id);
        mapper.setUser_phone_no(id+1);
        System.out.println("in!!!");

        result.setResult(mapper);
        result.setCode(400);
        result.setMsg("success");
        result.setSuccess(true);
        result.setT(System.currentTimeMillis());

        return result;
    }


    @GetMapping("/user/v2/{id}")
    public Result<Mapper> get2(@PathVariable Integer id, @ModelAttribute("md2") HashMap map) {

        System.out.println(map);

        Result<Mapper> result = new Result<>();

        Mapper mapper = new Mapper();
        mapper.setId(id);
        mapper.setName("name_" + id);
        mapper.setFirst_name("first_name_" + id);
        mapper.setUser_phone_no(id+1);
        System.out.println("in!!!");

        result.setResult(mapper);
        result.setCode(400);
        result.setMsg("success");

        return result;
    }

    @Data
    class Mapper implements Serializable {

        private Integer id;
        private String name;
        private String first_name;
        private Integer user_phone_no;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class Result<T> implements Serializable {
        private static final long serialVersionUID = 1076888268359702742L;

        Integer code;
        String msg;
        Boolean success;
        Long t;
        T result;
    }

}
