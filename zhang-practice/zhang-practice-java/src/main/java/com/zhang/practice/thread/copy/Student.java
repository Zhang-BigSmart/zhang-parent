package com.zhang.practice.thread.copy;

/**
 * @author : zzh
 * create at:  2020/5/29
 * @description:
 */
public class Student implements Cloneable{

    private int age;
    private String name;
    private Integer range;
    private Teacher teacher;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.teacher = (Teacher) teacher.clone();
        return student;
    }

    public Student(int age, String name, Integer range, Teacher teacher) {
        this.age = age;
        this.name = name;
        this.teacher = teacher;
        this.range = range;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Student{" +
                "this=" + this.hashCode() +
                ", age=" + age +
                ", range=" + range +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
