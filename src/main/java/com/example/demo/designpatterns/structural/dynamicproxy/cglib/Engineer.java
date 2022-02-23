package com.example.demo.designpatterns.structural.dynamicproxy.cglib;

// 需要代理的类
public class Engineer {
    // 可以被代理
    public void eat() {
        System.out.println("工程师正在吃饭");
    }

    // final 方法不会被生成的子类重写
    public final void work() {
        System.out.println("工程师正在工作");
    }

    // private 方法不会被生成的子类重写
    private void play() {
        System.out.println("this engineer is playing game");
    }
}
