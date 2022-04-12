package com.example.demo.skill.apimethod.exception.globalexceptionhandler;

import com.example.demo.entity.Person;
import com.example.demo.entity.Person1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ldy
 * @version 1.0
 */
@RequestMapping
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/admin/test/post/filter.json")
    public RespBean<Person> getAdminById() {

        Person person = new Person();
        Person1 person1 = new Person1(6);
        person.setSex(5);

        return RespBean.successWithData(person);
    }
}
