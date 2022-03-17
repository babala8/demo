package com.example.demo.skill.apimethod.template.controller;

import com.example.demo.entity.Person;
import com.example.demo.skill.apimethod.template.api.TemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 模板模式实际使用方法
 */
@Controller
public class TemplateController {

    @Resource(name="templateService2")
    private TemplateService templateService2;
    @Resource(name="templateService")
    private TemplateService templateService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        templateService.test();
        templateService2.test();
        Person query = templateService.query();
        System.out.println(query);
        Person query2 = templateService2.query();
        System.out.println(query2);
        return "success";
    }


}
