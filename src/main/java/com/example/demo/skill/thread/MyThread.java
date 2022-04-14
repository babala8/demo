package com.example.demo.skill.thread;


/**
 * 1. 定义一个Thread类的子类，重写run方法，将相关逻辑实现，run()方法就是线程要执行的业务逻辑方法
 * 2. 创建自定义的线程子类对象
 * 3. 调用子类实例的star()方法来启动线程
 */
public class MyThread extends Thread{


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run()方法正在执行...");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            MyThread myThread = new MyThread();
            myThread.start();
        }

        System.out.println(Thread.currentThread().getName() + " main()方法执行结束");
    }
}
