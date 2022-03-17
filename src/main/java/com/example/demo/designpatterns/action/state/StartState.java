package com.example.demo.designpatterns.action.state;



/** 创建实现接口的实体类 */
public class StartState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}
