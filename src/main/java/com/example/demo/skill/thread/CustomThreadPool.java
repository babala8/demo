package com.example.demo.skill.thread;

import java.util.concurrent.*;


public class CustomThreadPool {

    private static final int CORE_POOL_SIZE = 10;
    private static final int MAXINUM_POOL_SIZE = 20;
    private static final long KEEP_ALIVE_TIME = 60;
    private static final int MAXINUM_QUEUE_SIZE = 100;

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXINUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(MAXINUM_QUEUE_SIZE),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()// 拒绝策略
                new CustomRejectedExecutionHandler()
                );
    }


    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            BlockingQueue<Runnable> queue = executor.getQueue();
            try {
                queue.put(r);
            } catch (InterruptedException e) {
                System.out.println("拒绝策略重新提交任务失败");
                e.printStackTrace();
            }

        }
    }

}
