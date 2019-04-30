package com.zhang.practice.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BoundQueue
 * @Description:Condition实现阻塞有界队列
 * @Author: zhangzh
 * @Date 2019/3/11 10:08
 */
public class BoundQueue<T> {

    public List<T> list;

    public int maxSize;

    private Lock lock = new ReentrantLock();

    private final Condition addCondition = lock.newCondition();

    private final Condition removeCondition = lock.newCondition();

    public BoundQueue(int maxSize) {
        this.maxSize = maxSize;
        this.list = new ArrayList<>();
    }

    /**
     * 插入元素
     * @param t
     */
    public void add(T t) {
        lock.lock();
        try {
            while (list.size() == maxSize) {
                System.out.println("add full...");
                addCondition.await();
            }
            list.add(t);
            System.out.println("add element, list is:" + Arrays.toString(list.toArray()));
            removeCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T remove() {
        lock.lock();
        try {
            while (list.size() == 0) {
                System.out.println("remove is null...");
                removeCondition.await();
            }
            T q = list.remove(0);
            System.out.println("remove element, list is:" + Arrays.toString(list.toArray()));
            addCondition.signal();
            return q;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }

}
