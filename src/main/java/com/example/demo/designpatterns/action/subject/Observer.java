package com.example.demo.designpatterns.action.subject;

/** 创建 Observer 类 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
