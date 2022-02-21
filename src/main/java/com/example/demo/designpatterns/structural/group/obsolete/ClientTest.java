package com.example.demo.designpatterns.structural.group.obsolete;

import com.example.demo.designpatterns.structural.group.obsolete.Employee;
import com.example.demo.designpatterns.structural.group.obsolete.Manager;

public class ClientTest {
    public static void main(String[] args) {
        Manager boss = new Manager("老板", "唱怒放的生命");
        Employee HR = new Employee("人力资源", "聊微信");
        Manager PM = new Manager("产品经理", "不知道干啥");
        Manager CFO = new Manager("财务主管", "看剧");
        Manager CTO = new Manager("技术主管", "划水");
        Employee UI = new Employee("设计师", "画画");
        Employee operator = new Employee("运营人员", "兼职客服");
        Employee webProgrammer = new Employee("程序员", "学习设计模式");
        Employee backgroundProgrammer = new Employee("后台程序员", "CRUD");
        Employee accountant = new Employee("会计", "背九九乘法表");
        Employee clerk = new Employee("文员", "给老板递麦克风");
        boss.addEmployee(HR);
        boss.addManager(PM);
        boss.addManager(CFO);
        PM.addEmployee(UI);
        PM.addManager(CTO);
        PM.addEmployee(operator);
        CTO.addEmployee(webProgrammer);
        CTO.addEmployee(backgroundProgrammer);
        CFO.addEmployee(accountant);
        CFO.addEmployee(clerk);
        boss.check();
    }
}
