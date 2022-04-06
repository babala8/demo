package com.example.demo.skill.annotationcustom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 *
 * @apiNote 自定义注解逻辑实现
 * @version 3.0.0
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatternListValidator implements ConstraintValidator<AnnotationCustom, String> {

    @Override
    public void initialize(AnnotationCustom constraintAnnotation) {
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,1,3,6-8])|(18[0-9])|(19[8,9])|(166))[0-9]{8}$");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
