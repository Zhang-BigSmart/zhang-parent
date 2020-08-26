package com.zhang.practice.thread.copy;

/**
 * @author : zzh
 * create at:  2020/5/29
 * @description:
 */
public class ShallowCopy {

    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher(30, "Miss.Yang");

        Student s1 = new Student(18, "edison", 1, teacher);

        Student s2 = (Student) s1.clone();
        s1.setAge(20);
        s1.setRange(100);
        s1.setName("Alic");

        s1.getTeacher().setName("Mr.Zhang");

        System.out.println(s1);
        System.out.println(s2);

    }
}
