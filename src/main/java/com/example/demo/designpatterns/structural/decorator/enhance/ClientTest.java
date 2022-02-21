package com.example.demo.designpatterns.structural.decorator.enhance;

public class ClientTest {
    public static void main(String[] args) {
        IBeauty me = new Me();
        System.out.println("我原本的颜值: "+me.getBeautyValue());

        IBeauty meWithRing = new RingDecorator(me);
        System.out.println("戴上了戒指后，我的颜值："+meWithRing.getBeautyValue());

        IBeauty meWithNecklaceAndRing = new NecklaceDecorator(new RingDecorator(me));
        System.out.println("戴上戒指、项链后，我的颜值：" +meWithNecklaceAndRing.getBeautyValue());
    }
}
