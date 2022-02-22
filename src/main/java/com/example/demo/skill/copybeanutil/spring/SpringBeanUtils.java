package com.example.demo.skill.copybeanutil.spring;

import com.example.demo.skill.copybeanutil.A;
import com.example.demo.skill.copybeanutil.B;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;

/**
 *
 * 大家运行以下示例时，会发生类型转换异常。
 *
 * 打断点可以看到，属性拷贝之后 B 类型的 second 对象中 ids 仍然为 Integer 类型：
 * 如果不转换为字符串，直接进行打印，并不会报错。
 */
public class SpringBeanUtils {
    public static void main(String[] args) {
        A first = new A();
        first.setName("demo");
        first.setIds(Arrays.asList(1, 2, 3));

        B second = new B();
        BeanUtils.copyProperties(first, second);
        for (String each : second.getIds()) {// 类型转换异常
            System.out.println(each);
        }
    }
}