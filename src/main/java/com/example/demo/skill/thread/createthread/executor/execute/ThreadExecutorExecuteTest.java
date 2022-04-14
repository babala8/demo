package com.example.demo.skill.thread.createthread.executor.execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;



public class ThreadExecutorExecuteTest{



    public static void main(String[] args) {

        ThreadFactory factory = r->{
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setUncaughtExceptionHandler( (t,e) -> {
                System.out.println(t + "" + e);
                e.printStackTrace();//example
            });
            return thread ;
        };

        ExecutorService service = Executors.newFixedThreadPool(1,factory);
        Runnable r = () -> {
            System.out.println("a");
            System.out.println(1 / 0);
        };

        service.execute(r);
        service.shutdown();
    }

}
