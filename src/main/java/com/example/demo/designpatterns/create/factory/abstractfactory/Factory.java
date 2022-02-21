package com.example.demo.designpatterns.create.factory.abstractfactory;


// 抽象工厂模式，生产不同的产品簇，工厂接口
public interface Factory {
    Phone getPhone();
    Computer getComputer();
}

class XiaoMiFactory implements Factory{
    @Override
    public Phone getPhone() {
        return new XiaoMiPhone();
    }

    @Override
    public Computer getComputer() {
        return new XiaoMiComputer();
    }
}

class HuaWeiFactory implements Factory{
    @Override
    public Phone getPhone() {
        return new HuaWeiPhone();
    }

    @Override
    public Computer getComputer() {
        return new HuaWeiComputer();
    }
}
