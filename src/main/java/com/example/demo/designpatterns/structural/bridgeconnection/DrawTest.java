package com.example.demo.designpatterns.structural.bridgeconnection;

public class DrawTest {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setColor(new Red());
        rectangle.draw();
        Round round = new Round();
        round.setColor(new Blue());
        round.draw();
        Triangle triangle = new Triangle();
        triangle.setColor(new Yellow());
        triangle.draw();
    }
}
