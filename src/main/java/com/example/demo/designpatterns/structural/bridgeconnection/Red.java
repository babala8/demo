package com.example.demo.designpatterns.structural.bridgeconnection;

public class Red implements IColor {
    @Override
    public String getColor() {
        return "红";
    }
}

class Blue implements IColor {
    @Override
    public String getColor() {
        return "蓝";
    }
}

class Yellow implements IColor {
    @Override
    public String getColor() {
        return "黄";
    }
}

class Green implements IColor {
    @Override
    public String getColor() {
        return "绿";
    }
}

