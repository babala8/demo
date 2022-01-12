package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.entity.Person1;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TestStr {

    public static void main(String[] args) {
        List<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person(1));
        personArrayList.add(new Person(5));
        List<Person1> person1ArrayList = new ArrayList<>();
        Person1 person1 = new Person1(8);
        if(!CollectionUtils.isEmpty(personArrayList)){
            BeanUtils.copyProperties(personArrayList,person1ArrayList);
            System.out.println(person1ArrayList);
            System.out.println(personArrayList);
//            personArrayList.forEach(person -> {
//                System.out.println(person);
//                BeanUtils.copyProperties(person,person1);
//            });
        }
        Person person = null;
        String a = "123" + person;
        System.out.println(a);
        Person person2 = new Person();
        System.out.println(person2.getSex());
    }
}
