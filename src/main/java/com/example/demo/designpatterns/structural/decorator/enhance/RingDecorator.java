package com.example.demo.designpatterns.structural.decorator.enhance;

/** 装饰器类 */
public class RingDecorator implements IBeauty {
    private final IBeauty me;

    public RingDecorator(IBeauty me){
        this.me = me;
    }

    @Override
    public int getBeautyValue() {
        return me.getBeautyValue() + 20;
    }
}
