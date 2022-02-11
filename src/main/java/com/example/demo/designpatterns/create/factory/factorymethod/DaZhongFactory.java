package com.example.demo.designpatterns.create.factory.factorymethod;


public class DaZhongFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new DaZhong();
    }
}

