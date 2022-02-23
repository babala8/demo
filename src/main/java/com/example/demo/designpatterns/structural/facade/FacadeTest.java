package com.example.demo.designpatterns.structural.facade;

public class FacadeTest {
    public static void main(String[] args) {
        Facade facade = new Facade();
        System.out.println("上班:");
        facade.open();
        System.out.println("下班:");
        facade.close();
    }
}
