package com.example.demo.designpatterns.structural.decorator.newfunc;

public class ClientTest {
    public static void main(String[] args) {
        IHouse house = new House();
        house.live();

        IStickyHookHouse stickyHookHouse = new StickyHookDecorator(house);
        stickyHookHouse.live();
        stickyHookHouse.hangThings();
    }
}
