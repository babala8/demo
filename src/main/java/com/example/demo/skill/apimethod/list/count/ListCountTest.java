package com.example.demo.skill.apimethod.list.count;

import com.example.demo.entity.Person;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ListCountTest {

    public static void main(String[] args) {
        List<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person(5));
        personArrayList.add(new Person(1));

        if(!CollectionUtils.isEmpty(personArrayList)){
            long count = personArrayList.stream().filter(person -> Objects.equals(1, person.getSex())).count();// 统计年龄为1的人数
            System.out.println("count="+count);
        }
    }
}
