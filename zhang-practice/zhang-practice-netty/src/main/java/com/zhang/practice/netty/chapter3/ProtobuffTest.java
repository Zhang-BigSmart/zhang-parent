package com.zhang.practice.netty.chapter3;

import com.zhang.practice.netty.chapter3.model.DataTest;

/**
 * @author : zzh
 * create at:  2019/10/31
 * @description:
 */
public class ProtobuffTest {
    public static void main(String[] args) throws Exception{
        DataTest.RequestUser student = DataTest.RequestUser.newBuilder().setUserName("张三").setAge(20).setPassword("北京").build();

        //将对象转译成字节数组,序列化
        byte[] student2ByteArray = student.toByteArray();

        //将字节数组转译成对象,反序列化
        DataTest.RequestUser student2 = DataTest.RequestUser.parseFrom(student2ByteArray);

        System.out.println(student2.getUserName());
        System.out.println(student2.getAge());
        System.out.println(student2.getPassword());
    }
}
