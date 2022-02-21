package com.example.demo.designpatterns.structural.decorator.newfunc;

public class StickyHookDecorator implements IStickyHookHouse {
    private final IHouse house;

    public StickyHookDecorator(IHouse house){
        this.house=house;
    }
    @Override
    public void hangThings() {
        System.out.println("有了粘钩后，新增了挂东西功能");
    }

    @Override
    public void live() {
        house.live();
    }
}
