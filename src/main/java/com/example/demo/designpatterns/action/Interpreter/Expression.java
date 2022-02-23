package com.example.demo.designpatterns.action.Interpreter;


/** 创建一个表达式接口 */
public interface Expression {
    public boolean interpret(String context);
}
