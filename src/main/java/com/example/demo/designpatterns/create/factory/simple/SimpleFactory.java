package com.example.demo.designpatterns.create.factory.simple;

public class SimpleFactory {
    // 当新增车的时候，SimpleFactory中的getInstance方法需要修改,根据新类型返回新对象
    public static Car getInstance(String type){
        if("DaZhong".equals(type)){
            return new DaZhong();
        }else if ("xxx".equals(type)){
//            return new xxx;
        }
        return null;
    }

    public static void main(String[] args) {
        Car daZhong = SimpleFactory.getInstance("DaZhong");
        System.out.println(daZhong);
    }
}

class DaZhong implements Car{
}

