package com.example.demo.designpatterns.structural.bridgeconnection;


public class Rectangle implements IShape{
    private IColor color;

    void setColor(IColor color){
        this.color = color;
    }
    @Override
    public void draw() {
        System.out.println("绘制" + color.getColor() + "矩形");
    }
}


class Round implements IShape{
    private IColor color;

    void setColor(IColor color){
        this.color = color;
    }
    @Override
    public void draw() {
        System.out.println("绘制" + color.getColor() + "圆形");
    }
}

class Triangle implements IShape{
    private IColor color;

    void setColor(IColor color){
        this.color = color;
    }
    @Override
    public void draw() {
        System.out.println("绘制" + color.getColor() + "三角形");
    }
}