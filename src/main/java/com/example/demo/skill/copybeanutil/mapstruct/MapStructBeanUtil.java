package com.example.demo.skill.copybeanutil.mapstruct;

import com.example.demo.skill.copybeanutil.A;
import com.example.demo.skill.copybeanutil.B;

import java.util.Arrays;

/**
 * MapStruct自动帮我们将Integer转换为了String进行了转换，我们可能没有意识到类型并不一致。
 *
 * 如果我们在 A 类中添加一个 String number 属性，在 B 类中添加一个 Long number 属性，
 * 使用 mapstruect 当 number 设置为非数字类型时就会报 .NumberFormatException 。
 */
public class MapStructBeanUtil {
    public static void main(String[] args) {
        A first = new A();
        first.setName("demo");
        first.setIds(Arrays.asList(1, 2, 3));

        B second = Converter.INSTANCE.aToB(first);
        for (String each : second.getIds()) {// 正常
            System.out.println(each);
        }
    }
}