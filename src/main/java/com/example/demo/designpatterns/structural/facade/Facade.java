package com.example.demo.designpatterns.structural.facade;

/**
 * 外观模式
 */
public class Facade {
    public void open() {
        Browser.open();
        IDE.open();
        Wechat.open();
    }
    public void close() {
        Browser.close();
        IDE.close();
        Wechat.close();
    }
}
