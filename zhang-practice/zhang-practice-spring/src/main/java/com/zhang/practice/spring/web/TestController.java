package com.zhang.practice.spring.web;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
public class TestController {

    @GetMapping("/user/{id}")
    public Result<Mapper> get(@PathVariable Integer id) {
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
