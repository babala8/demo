package com.example.demo.skill.apimethod.list.filter;

import com.example.demo.entity.Person;
import com.example.demo.entity.Person1;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ListFilterTest {

    public static void main(String[] args) {
        List<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person(5));
        personArrayList.add(new Person(1));
        List<Person> personArrayList1 = new ArrayList<>();

        // 获取集合中第一个元素，如果集合为空，则为null
        Person person1 = personArrayList1.stream().findFirst().orElse(null);
        System.out.println(person1);

        /** 获取集合中第一个元素，如果集合为空，则为null */
        Person person2 = personArrayList.stream().findFirst().orElse(null);
        System.out.println(person2);

        if(!CollectionUtils.isEmpty(personArrayList)){
            /** 筛选年龄为1的人的list */
            personArrayList = personArrayList.stream().filter(person -> Objects.equals(1, person.getSex())).collect(Collectors.toList());
            /** 指定条件筛选判断 */
            boolean b = personArrayList.stream().anyMatch(p -> Objects.equals(1, p.getSex()));
            System.out.println(b);
        }

        /** 指定条件筛选判断 */
        Optional<Person> any = personArrayList.stream().filter(person -> Objects.equals(1, person.getSex())).findAny();
        if (any.isPresent()) {
            System.out.println("存在年龄为1的人");
        }

        /** Optional转换成list */
        List<Person> personList = any.map(Collections::singletonList).orElse(Collections.emptyList());

        System.out.println(personArrayList);
    }
}
