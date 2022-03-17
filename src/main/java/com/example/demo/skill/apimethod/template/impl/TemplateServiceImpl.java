package com.example.demo.skill.apimethod.template.impl;

import com.example.demo.entity.Person;
import com.example.demo.skill.apimethod.template.common.AbstractTemplateServiceImpl;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;


@Service("templateService")
public class TemplateServiceImpl extends AbstractTemplateServiceImpl {

    @Override
    public void test() {
        super.test();
        System.out.println(this.getClass().getName() + ",的test方法被调用");
    }

    @Override
    public Person query() {
        super.query();
        System.out.println(this.getClass().getName() + ",的query方法被调用");
        return null;
    }

}
