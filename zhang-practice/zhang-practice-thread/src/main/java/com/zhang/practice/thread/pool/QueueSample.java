package com.zhang.practice.thread.pool;

import java.beans.ConstructorProperties;
import java.util.Date;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public class QueueSample {

    private final Date date;
    private final int size;
    private final String head;

    @ConstructorProperties({"date", "size", "head"})
    public QueueSample(Date date, int size,
                       String head) {
        this.date = date;
        this.size = size;
        this.head = head;
    }

    public Date getDate() {
        return date;
    }

    public int getSize() {
        return size;
    }

    public String getHead() {
        return head;
    }
}
