package com.example.demo.skill.thread.createthread.executor.submit;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 两个方法都是将线程提交到线程池
 * （1）传入的参数不同
 * （2）execute没有返回值。submit返回Future对象
 * (3)excute方法会抛出异常。
 * sumbit方法不会抛出异常。除非你调用Future.get()。
 * （4）submit在interface ExecutorService内，execute在 interface Executor 中
 */
public class ThreadExecutorSubmitRunnable implements Runnable{

    @Override
    public void run() {
//        int i = 1/0;
        System.out.println(Thread.currentThread().getName() + " run()方法执行中...");
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ThreadExecutorSubmitRunnable myRunnable = new ThreadExecutorSubmitRunnable();
        /** 提交多个任务,50 */
        for (int i = 0; i < 50; i++) {
            // 创建任务对象,并提交给线程池
            Future<?> future = executorService.submit(myRunnable);
            try {
                /**
                 * 1. 不管提交的是Runnable还是Callable类型的任务，如果不对返回值Future调用get()方法，都不会抛出异常
                 * 2. submit不管是Runnable还是Callable类型的任务都可以接受，但是Runnable返回值均为void，所以使用Future的get()获得的还是null
                 * */
                future.get();
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        System.out.println("线程任务开始执行");
        executorService.shutdown();
    }


}
