package com.zhang.practice.thread.delayed;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Exam
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/17 14:21
 */
public class Exam {

    /**
     *
     *2014-1-10 下午9:43:48 by 孙振超
     *
     *@param args
     *void
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        int studentNumber = 20;
        CountDownLatch countDownLatch = new CountDownLatch(studentNumber + 1);
        DelayQueue<Student> students = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i < studentNumber; i++) {
            students.put(new Student("student"+(i+1), 30 + random.nextInt(120), countDownLatch));
        }
        // 因为delayQueue的take()会阻塞，如果在主线程使用的话会导致当前线程阻塞，所以创建一个线程用于读取delayQueue的阻塞任务
        Thread teacherThread = new Thread(new Teacher(students));
        students.put(new EndExam(students, 120, countDownLatch, teacherThread));
        teacherThread.start();
        countDownLatch.await();
        System.out.println(" 考试时间到，全部交卷！");
    }

}

class Student implements Runnable,Delayed{

    private String name;
    private long workTime;
    private long submitTime;
    private boolean isForce = false;
    private CountDownLatch countDownLatch;

    public Student(String name, long workTime, CountDownLatch countDownLatch){
        this.name = name;
        this.workTime = workTime;
        this.submitTime = TimeUnit.NANOSECONDS.convert(workTime, TimeUnit.NANOSECONDS)+System.nanoTime();
        this.countDownLatch = countDownLatch;
    }

    /**
     * 判断队列中元素的顺序谁前谁后  当前元素比队列元素后执行时，返回一个正数，比它先执行时返回一个负数，否则返回0
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        // TODO Auto-generated method stub
        if(o == null || ! (o instanceof Student)) return 1;
        if(o == this) return 0;
        Student s = (Student)o;
        if (this.workTime > s.workTime) {
            return 1;
        }else if (this.workTime == s.workTime) {
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * 计算当前时间到执行时间之间还有多长时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        // TODO Auto-generated method stub
        return unit.convert(submitTime - System.nanoTime(),  TimeUnit.NANOSECONDS);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        if (isForce) {
            System.out.println(name + " 交卷, 希望用时" + workTime + "分钟"+" ,实际用时 120分钟" );
        }else {
            System.out.println(name + " 交卷, 希望用时" + workTime + "分钟"+" ,实际用时 "+workTime +" 分钟");
        }
        countDownLatch.countDown();
    }

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean isForce) {
        this.isForce = isForce;
    }

}

class EndExam extends Student{

    private DelayQueue<Student> students;
    private CountDownLatch countDownLatch;
    private Thread teacherThread;

    public EndExam(DelayQueue<Student> students, long workTime, CountDownLatch countDownLatch,Thread teacherThread) {
        super("强制收卷", workTime,countDownLatch);
        this.students = students;
        this.countDownLatch = countDownLatch;
        this.teacherThread = teacherThread;
    }



    @Override
    public void run() {
        // 阻塞教师线程
        teacherThread.interrupt();
        System.out.println("teacher interrupted start");
        Student tmpStudent;
        // 讲队列中的剩下的学生直接运行
        for (Iterator<Student> iterator2 = students.iterator(); iterator2.hasNext();) {
            tmpStudent = iterator2.next();
            tmpStudent.setForce(true);
            tmpStudent.run();
        }
        countDownLatch.countDown();
    }

}

class Teacher implements Runnable{

    private DelayQueue<Student> students;
    public Teacher(DelayQueue<Student> students){
        this.students = students;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        /*try {
            System.out.println(" Test start");
            while(!Thread.interrupted()){
                students.take().run();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }*/
        try {
            while(true) {
                students.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("Teacher was interrupted");
        }
        System.out.println("teacher end");
    }

}
