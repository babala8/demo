package com.example.demo.skill.thread.createthread.executor.submit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * 两个方法都是将线程提交到线程池
 * （1）传入的参数不同
 * （2）execute没有返回值。submit返回Future对象
 * (3)excute方法会抛出异常。
 * sumbit方法不会抛出异常。除非你调用Future.get()。
 * （4）submit在interface ExecutorService内，execute在 interface Executor 中
 */
public class ThreadExecutorSubmitCallable implements Callable<Integer> {

    /** 返回值为泛型， Callable<Integer> */
    @Override
    public Integer call() throws Exception {
//        int i = 1/0;
        System.out.println(Thread.currentThread().getName() + " run()方法执行中...");
        return 1;
    }


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ThreadExecutorSubmitCallable myRunnable = new ThreadExecutorSubmitCallable();

        List<Future<Integer>> futureList = new ArrayList<>();

        /** 提交多个任务,40个 */
        for (int i = 0; i < 40; i++) {
            // 创建任务对象,并提交给线程池
            Future<Integer> future = executorService.submit(myRunnable);
            futureList.add(future);
        }

        System.out.println("线程任务开始执行");
        // 关闭线程池
        executorService.shutdown();

        try {
            // 获取所有并发任务结果
            for (Future<Integer> future : futureList) {
                /**
                 * 1. 不管提交的是Runnable还是Callable类型的任务，如果不对返回值Future调用get()方法，都不会抛出异常
                 * 2. submit不管是Runnable还是Callable类型的任务都可以接受，但是Runnable返回值均为void，所以使用Future的get()获得的还是null
                 * */
                Integer integer = future.get();
                // 从 Future 对象上获取任务的返回值，并输出到控制台
                System.out.println("res：" + future.get().toString());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("执行出异常了");
            e.printStackTrace();
        }

    }


}
