package com.example.demo.designpatterns.create.builder.test;

import com.example.demo.designpatterns.create.builder.MilkTea;

public class User {
    private void buyMilkTea() {
        // Inner为静态内部类，Outer.Inner in = new Outer.Inner();
//        MilkTea milkTea = new MilkTea.Builder("原味").build();
//        MilkTea.Builder mb = new MilkTea.Builder("原味");// 创建内部类对象
        MilkTea.Builder mb = new MilkTea.Builder("原味");// 创建静态内部类MilkTea.Builder对象
        mb.pearl(false);
        MilkTea milkTea = mb.build();
        show(milkTea);

        MilkTea chocolate = new MilkTea.Builder("巧克力味")
                .ice(false)
                .build();
        show(chocolate);

        MilkTea strawberry = new MilkTea.Builder("草莓味")
                .size("大杯")
                .pearl(false)
                .ice(true)
                .build();
        show(strawberry);
    }

    private void show(MilkTea milkTea) {
        String pearl;
        if (milkTea.isPearl())
            pearl = "加珍珠";
        else
            pearl = "不加珍珠";
        String ice;
        if (milkTea.isIce()) {
            ice = "加冰";
        } else {
            ice = "不加冰";
        }
        System.out.println("一份" + milkTea.getSize() + "、"
                + pearl + "、"
                + ice + "的"
                + milkTea.getType() + "奶茶");
    }

    public static void main(String[] args) {
        new User().buyMilkTea();
//        MilkTea.Builder mb = new MilkTea.Builder("原味");
//        MilkTea.Builder mb1 = new MilkTea.Builder("原味");
//        System.out.println(mb==mb1);// false
    }
}
