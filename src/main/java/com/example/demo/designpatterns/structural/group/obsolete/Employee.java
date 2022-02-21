package com.example.demo.designpatterns.structural.group.obsolete;

public class Employee {
    // 职位
    private String position;
    // 工作内容
    private String job;

    public Employee(String position, String job) {
        this.position = position;
        this.job = job;
    }
    // 做自己的本职工作
    public void work() {
        System.out.println("我是" + position + "，我正在" + job);
    }

}
