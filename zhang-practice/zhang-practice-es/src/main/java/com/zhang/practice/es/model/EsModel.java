package com.zhang.practice.es.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName EsModel
 * @Description:
 * @Author: zhangzh
 * @Date 2019/5/7 10:11
 */
@Data
@ToString
@NoArgsConstructor
public class EsModel {
    private String id;
    private String name;
    private int age;
    private Date date;
}

