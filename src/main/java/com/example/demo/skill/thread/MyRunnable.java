package com.example.demo.skill.thread;


/**
 * 1. 定义Runnable接口实现类MyRunnable，并重写run()方法
 * 2. 创建MyRunnable实例myRunnable，以myRunnable作为target创建Thread对象，该Thread对象才是真正的线程对象
 * 3. 调用线程对象的start()方法
 */
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run()方法正在执行...");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            MyRunnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable);
            thread.start();
        }

        System.out.println(Thread.currentThread().getName() + " main()方法执行结束");

    }
}
