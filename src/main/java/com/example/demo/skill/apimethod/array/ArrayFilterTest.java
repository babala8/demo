package com.example.demo.skill.apimethod.array;

import com.example.demo.entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ldy
 * @version 1.0
 */
public class ArrayFilterTest {

    public static void main(String[] args) {
        List<Person> arrayList = new ArrayList<>(2);
        arrayList.add(new Person(2));
        arrayList.add(new Person(5));
        /** 返回值Boolean，可用于判断 */
        if(Arrays.stream(arrayList.toArray(new Person[arrayList.size()])).anyMatch(p -> Objects.equals(2,p.getSex()))){
            System.out.println("ggggg");
        }

        /** 统计年龄为2 的数量 */
        long count = Arrays.stream(arrayList.toArray(new Person[arrayList.size()])).filter(p ->  Objects.equals(2,p.getSex())).count();
        System.out.println(count);

        /** 获取集合中第一个元素，如果集合为空，则为null */
        Person person = Arrays.stream(arrayList.toArray(new Person[arrayList.size()])).filter(p -> Objects.equals(2, p.getSex())).findFirst().orElse(null);
        System.out.println(person);

        /** 筛选年龄为2的人的list */
        List<Person> personList = Arrays.stream(arrayList.toArray(new Person[arrayList.size()])).filter(p -> Objects.equals(2, p.getSex())).collect(Collectors.toList());
        System.out.println(personList.toString());

        Stream<Person> limit = Arrays.stream(arrayList.toArray(new Person[arrayList.size()])).filter(p -> Objects.equals(2, p.getSex())).limit(0);
        // limit.getClass() 输出 class java.util.stream.SliceOps$1
        System.out.println(limit.getClass());
        // limit.getClass().getName() 输出 java.util.stream.SliceOps$1
        System.out.println(limit.getClass().getName());


    }
}
