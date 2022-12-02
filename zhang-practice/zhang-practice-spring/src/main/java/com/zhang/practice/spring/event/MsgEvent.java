package com.zhang.practice.spring.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : zzh
 * create at:  2022/7/28
 * @description:
 */
@Data
@AllArgsConstructor
public class MsgEvent {

    private Integer id;

    private String name;
}
