package com.example.demo.designpatterns.structural.adapter;


/**  Adapter来适配 HomeBattery 和 UsbLine  */
public class AdapterDecorator {
    public int convert(int homeVolt) {
        // 适配过程：使用电阻、电容等器件将其降低为输出 5V
        int chargeVolt = homeVolt - 215;
        return chargeVolt;
    }
}
