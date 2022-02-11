package com.example.demo.designpatterns.create.single;

public class SingleBean {

    private static volatile SingleBean singleBean;
    private static  Object object = new Object();

    private SingleBean(){
    }
    public static SingleBean getInstance(){
        if(singleBean == null){
            // 因为singleBean没有new对象，所以此处不能使用singleBean的对象锁
            // 创建一个object对象，即可使用object的对象锁
            synchronized (object){// 或者 synchronized (SingleBean.class)
                if(singleBean == null){
                    singleBean = new SingleBean();
                }
            }
        }
        return singleBean;
    }
    public static void main(String[] args) {

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    SingleBean instance = SingleBean.getInstance();
                    System.out.println(Thread.currentThread().getName()+":"+instance);
                }
            }).start();
        }
    }
}
