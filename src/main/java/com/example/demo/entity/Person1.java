package com.example.demo.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person1 {
    private int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                '}';
    }
}
