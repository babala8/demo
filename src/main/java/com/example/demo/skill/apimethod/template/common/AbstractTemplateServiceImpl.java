package com.example.demo.skill.apimethod.template.common;


import com.example.demo.entity.Person;
import com.example.demo.skill.apimethod.template.api.TemplateService;

import java.io.Serializable;

public abstract class AbstractTemplateServiceImpl<T extends Serializable> implements TemplateService {

    @Override
    public void test() {
        System.out.println(this.getClass().getName() + ",的test方法被调用");
    }

    @Override
    public Person query() {
        System.out.println(this.getClass().getName() + ",的query方法被调用");
        return null;
    }
}
