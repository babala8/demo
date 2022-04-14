package com.example.demo.skill.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * 1. 创建实现Callable接口的类myCallable
 * 2. 以myCallable为参数创建FutureTask对象
 * 3. 将FutureTask作为参数创建Thread对象
 * 4. 调用线程对象的start()方法
 */
public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " call()方法执行中...");
        return 1;
    }

    public static void main(String[] args) {


        for (int i = 0; i < 20; i++) {
            FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallable());
            Thread thread = new Thread(futureTask);
            thread.start();

            try {
//                Thread.sleep(1000);
                System.out.println("返回结果 " + futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + " main()方法执行完成");
    }

}
