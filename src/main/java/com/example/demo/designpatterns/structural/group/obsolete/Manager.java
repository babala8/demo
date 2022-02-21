package com.example.demo.designpatterns.structural.group.obsolete;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    // 职位
    private String position;
    // 工作内容
    private String job;
    // 管理的管理者
    private List<Manager> managers = new ArrayList<>();
    // 管理的职员
    private List<Employee> employees = new ArrayList<>();

    public Manager(String position, String job) {
        this.position = position;
        this.job = job;
    }

    public void addManager(Manager manager) {
        managers.add(manager);
    }
    public void removeManager(Manager manager) {
        managers.remove(manager);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    // 做自己的本职工作
    public void work() {
        System.out.println("我是" + position + "，我正在" + job);
    }

    // 检查下属
    public void check() {
        work();
        for (Employee employee : employees) {
            employee.work();
        }
        for (Manager manager : managers) {
            manager.check();
        }
    }
}
