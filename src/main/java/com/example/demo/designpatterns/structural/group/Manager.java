package com.example.demo.designpatterns.structural.group;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Component {
    // 管理的组件
   private List<Component> components = new ArrayList<>();

    public Manager(String position, String job) {
        super(position, job);
    }

    @Override
    void addComponent(Component component) {
        components.add(component);
    }

    @Override
    void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    void check() {
        work();
        for (Component component : components) {
            component.check();
        }
    }
}
