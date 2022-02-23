package com.example.demo.designpatterns.structural.dynamicproxy.jdk;

// 目标类
public class RealSubject implements Subject{
    @Override
    public int sellBooks() {
        System.out.println("卖书");
        return 1 ;
    }

    @Override
    public String speak() {
        System.out.println("说话");
        return "张三";
    }
}

// 接口
interface Subject {
    public int sellBooks();

    public String speak();
}
