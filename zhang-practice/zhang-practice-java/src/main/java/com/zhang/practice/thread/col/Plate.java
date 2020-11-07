package com.zhang.practice.thread.col;

/**
 * @author : zzh
 * create at:  2020/10/19
 * @description:
 */
public class Plate<T> {

    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public static void main(String[] args) {
        Plate<? extends Fruit> plate = new Plate<>(new Apple());
        plate.setItem(new Apple());
    }
}

class Fruit{
    public void call(){
        System.out.println("这是一个水果");
    }
}

class Banana extends Fruit{
    @Override
    public void call(){
        System.out.println("这是一个香蕉");
    }
}

class Apple extends Fruit{
    @Override
    public void call(){
        System.out.println("这是一个苹果");
    }
}