package com.example.demo.skill.copybeanutil.cglib;

import com.example.demo.skill.copybeanutil.A;
import com.example.demo.skill.copybeanutil.B;
import net.sf.cglib.beans.BeanCopier;
//import org.easymock.cglib.beans.BeanCopier;

import java.util.Arrays;

/**
 * 选择Cglib的BeanCopier进行Bean拷贝的理由是，其性能要比Spring的BeanUtils，
 * Apache的BeanUtils和PropertyUtils要好很多，尤其是数据量比较大的情况下
 *
 * 使用CGlib 在不定义Converter 的情况下也会遇到类型转换异常问题：
 */
public class CglibBeanUtils {
    public static void main(String[] args) {
        A first = new A();
        first.setName("demo");
        first.setIds(Arrays.asList(1, 2, 3));

        B second = new B();
        final BeanCopier beanCopier = BeanCopier.create(A.class, B.class, false);
        beanCopier.copy(first, second, null);

        for (String each : second.getIds()) {// 类型转换异常
            System.out.println(each);
        }
    }
}