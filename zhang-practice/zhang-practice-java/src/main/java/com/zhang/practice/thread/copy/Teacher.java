package com.zhang.practice.thread.copy;

/**
 * @author : zzh
 * create at:  2020/5/29
 * @description:
 */
public class Teacher implements Cloneable {

    private int age;
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Teacher(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "this=" + this.hashCode() +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
