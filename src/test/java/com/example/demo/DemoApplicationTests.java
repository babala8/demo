package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.entity.Person1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        Person person = new Person();
        System.out.println(person.getSex());
        person.setSex(null);
        Person1 person1 = new Person1(1);
        person1.setSex(person.getSex());
        System.out.println(person1);
    }

}
