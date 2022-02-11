package com.example.demo.designpatterns.create.prototype;

import lombok.Data;

@Data
public class Prototype{
    public String type;
    public boolean ice;

    public Prototype clone(){
        Prototype milkTea = new Prototype();
        milkTea.type = this.type;
        milkTea.ice = this.ice;
        return milkTea;
    }

    private void order() {
        Prototype milkTeaOfJay = new Prototype();
        milkTeaOfJay.type = "原味";
        milkTeaOfJay.ice = false;

        Prototype yourMilkTea = milkTeaOfJay.clone();
        System.out.println(yourMilkTea.toString());
    }

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            Prototype prototype = new Prototype();
            prototype.order();
        }
    }
}