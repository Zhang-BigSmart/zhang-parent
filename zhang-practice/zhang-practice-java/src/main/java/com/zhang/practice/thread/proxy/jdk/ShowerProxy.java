package com.zhang.practice.thread.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : zzh
 * create at:  2021/4/5
 * @description:
 */
public class ShowerProxy implements InvocationHandler {

    private Object target;

    public ShowerProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("take off clothes...");
        Object result = method.invoke(target, args);
        System.out.println("put on clothes...");
        return result;
    }

    public static void main(String[] args) {
        Shower shower = new ShowerProxy(new MyShower()).getProxy();
        shower.bathing();
    }
}
