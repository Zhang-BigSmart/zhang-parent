package com.zhang.practice.thread.copy;

/**
 * @author : zzh
 * create at:  2020/5/29
 * @description:
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher(30, "Miss.Yang");

        Student s1 = new Student(2, "xiaoming", 1, teacher);

        Student s2 = (Student) s1.clone();

        Student s3 = s1;

        s1.setAge(22);
        s3.setRange(101);

        s2.setName("alic");

        s3.setName("edison");

        s1.getTeacher().setName("www");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

    }
}
