package com.example.demo.skill.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 使用 Executors 工具类创建线程池
 */
public class ThreadExecutorTest implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run()方法执行中...");
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        MyRunnable myRunnable = new MyRunnable();
        for (int i = 0; i < 50; i++) {
            executorService.execute(myRunnable);
        }

        System.out.println("线程任务开始执行");
        executorService.shutdown();
    }

}
