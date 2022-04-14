package com.example.demo.skill.apimethod.copybeanutil.common;

import com.example.demo.skill.apimethod.copybeanutil.A;
import com.example.demo.skill.apimethod.copybeanutil.B;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 首先公司内部就遇到过 commons 包的 BeanUtils 进行属性拷贝性能较差的真实案例，
 * 然后该同事换成了 Spring 的 BeanUtils 性能好了很多
 */
public class CommonBeanUtils {
    public static void main(String[] args) {
        A first = new A();
        first.setName("demo");
        first.setIds(Arrays.asList(1, 2, 3));

        B second = new B();
        try {
            BeanUtils.copyProperties(first, second);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        for (String each : second.getIds()) {// 类型转换异常
            System.out.println(each);
        }
    }
}