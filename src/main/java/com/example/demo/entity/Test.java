package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {
/*    public static void main(String[] args) {

        User user = new User();
        Person person = new Person();
        user.setPerson(person);
        user.getPerson().setSex(1);
        System.out.println(user.getPerson().toString());
    }*/


    // stream.filter一般适用于list集合,主要作用就是模拟sql查询，从集合中查询想要的数据。filter里面的参数user是指集合里面的每一项
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        //定义三个用户对象
        User user1 = new User();
        user1.setName("huxiansen");
//        user1.setAge(24);
//        User user2 = new User();
//        user2.setName("huxianseng");
//        user2.setAge("12");
//        User user3 = new User();
//        user3.setName("huxiansen");
//        user3.setAge("12");
        //添加用户到集合中
        list.add(user1);
//        list.add(user2);
//        list.add(user3);
        //在集合中查询用户名为huxiansen的集合
        List<User> userList = list.stream().filter(user -> "huxiansen".equals(user.getName())).collect(Collectors.toList());
        List<User> userAgeList = list.stream().filter(user -> "12".equals(user.getAge())).collect(Collectors.toList());
        //在集合中查询出第一个用户密码为123456的用户
        Optional<User> user = list.stream().filter(userTemp -> "12".equals(userTemp.getAge())).findFirst();
        System.out.println(userList);
        System.out.println(userAgeList);
        System.out.println(user);
    }

}
