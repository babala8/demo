package com.example.demo.skill.apimethod.exception.concurrentModificationException;

import com.example.demo.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModificationExceptionTest {

    /**
     *
     */
    public void ConcurrentModification(){

//        List<Person> personList = new ArrayList<>();
        List<Person> personList = new CopyOnWriteArrayList<>();
        personList.add(new Person(2));
        personList.add(new Person(5));
        personList.add(new Person(30));
        List<Person> persons = new ArrayList<>();

        /**
         * https://www.cnblogs.com/bsjl/p/7676209.html
         * 关键点就在于：调用list.remove()或list.add()方法导致modCount和expectedModCount的值不一致。
         * 注意：
         *  1.像使用for-each 或 iterator()进行迭代实际上也会出现这种问题
         *  2.多线程情况也会出现这种问题
         * 解决：
         * 可使用并发容器解决 CopyOnWriteArrayList
         */
        personList.forEach(p->{
            if(Objects.equals(2,p.getSex())){
                persons.add(p);
                personList.remove(p);
            }
        });

        System.out.println(personList.toString());
        System.out.println(persons.toString());

    }

    public static void main(String[] args) {
        ConcurrentModificationExceptionTest concurrentModificationExceptionTest = new ConcurrentModificationExceptionTest();
        concurrentModificationExceptionTest.ConcurrentModification();
    }

}
