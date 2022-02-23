package com.example.demo.designpatterns.structural.dynamicproxy.cglib;

import java.lang.reflect.Method;
import java.util.Arrays;

// 测试
public class CglibTest {
    public static void main(String[] args) {
        // 生成 Cglib 代理类
        Engineer engineerProxy = (Engineer) CglibProxy.getProxy(new Engineer());
        // 调用相关方法
        engineerProxy.eat();
        engineerProxy.work();// final 方法不会被生成的子类重写
//        engineerProxy.play();// private 方法不会被生成的子类重写
    }
}