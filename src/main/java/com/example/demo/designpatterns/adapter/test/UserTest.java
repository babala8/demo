package com.example.demo.designpatterns.adapter.test;

import com.example.demo.designpatterns.adapter.Adapter;
import com.example.demo.designpatterns.adapter.HomeBattery;
import com.example.demo.designpatterns.adapter.USBLine;

public class UserTest {

    public void chargeForPhone() {
        HomeBattery homeBattery = new HomeBattery();
        int supply = homeBattery.supply();
        System.out.println("家庭电源提供的电压是 " + supply + "V");
        Adapter adapter = new Adapter();
        int convert = adapter.convert(supply);
        System.out.println("使用适配器将家庭电压转换成了 " + convert + "V");
        USBLine usbLine = new USBLine();
        usbLine.charge(convert);
    }
    public static void main(String[] args) {
        new UserTest().chargeForPhone();
    }
}
