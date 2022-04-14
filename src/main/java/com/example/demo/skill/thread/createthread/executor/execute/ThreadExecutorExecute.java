package com.example.demo.skill.thread.createthread.executor.execute;

import com.sun.xml.internal.ws.api.model.CheckedException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * 使用 Executors 工具类创建线程池
 */
public class ThreadExecutorExecute implements Runnable{

    @Override
    public void run() {
//        int i =1/0;
        System.out.println(Thread.currentThread().getName() + " run()方法执行中...");
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        ThreadExecutorExecute myRunnable = new ThreadExecutorExecute();
        /** 提交多个任务,50 */
        for (int i = 0; i < 50; i++) {
            /**
             * 创建任务对象,并提交给线程池
             * 1. execute只能接受Runnable类型的任务
             * 2. execute没有返回值
             * 3.execute中可以抛出异常，所以使用try、catch来捕获
             */
            executorService.execute(myRunnable);

        }

        System.out.println("线程任务开始执行");
        executorService.shutdown();
    }

}
