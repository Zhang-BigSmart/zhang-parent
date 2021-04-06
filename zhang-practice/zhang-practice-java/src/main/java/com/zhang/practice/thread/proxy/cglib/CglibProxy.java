package com.zhang.practice.thread.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Classname CglibProxy
 * @Description TODO
 * @Date 2021/4/6
 * @Author 哲也（张梓濠 zheye.zhang@tuya.com）
 */
public class CglibProxy implements MethodInterceptor {

    /**
     *
     * @param o  代理对象
     * @param method 目标类中的方法
     * @param objects 方法参数
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("take off clothes...");
        System.out.println(o.getClass().getName());
        System.out.println(method.getDeclaringClass().getName());
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("put on clothes...");
        return invoke;
    }

    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/zhangzihao/Documents/git/zhang-parent/zhang-practice/zhang-practice-java/target/classes/com/zhang/practice/thread/proxy");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(MyShowerImpl.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new CglibProxy());
        // 创建代理对象
        MyShowerImpl proxy= (MyShowerImpl)enhancer.create();
        // 通过代理对象调用目标方法
        proxy.bathing();
    }
}
