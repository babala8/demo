package com.example.demo.staticinnerclass;

public class TestStatic {
    private static Integer i = 127;
    private static Integer i1 = 127;
    private static Integer i2 = new Integer(127);
    private static Integer i3 = 128;
    private static Integer i4 = 128;
    private static Integer i5 = new Integer(128);
    private static Integer i6 = new Integer(128);

    public static void main(String[] args) {
        // 常量池 -128~127
        System.out.println(TestStatic.i == TestStatic.i1);// true
        // new 产生新对象
        System.out.println(TestStatic.i == TestStatic.i2);// false
        System.out.println(TestStatic.i3 == TestStatic.i4);// false
        System.out.println(TestStatic.i3 == TestStatic.i5);// false
        System.out.println(TestStatic.i5 == TestStatic.i6);// false
        MilkTea.Builder mb = new MilkTea.Builder("原味");
        MilkTea.Builder mb1 = new MilkTea.Builder("原味");
        // 对于静态内部类一样，new 会产生新对象
        System.out.println(mb==mb1);// false
    }

}
