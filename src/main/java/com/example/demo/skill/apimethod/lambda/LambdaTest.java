package com.example.demo.skill.apimethod.lambda;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Lambda表达式就相当于一个匿名方法
 *
 * 当使用Lambda表达式替代内部类创建对象时，Lambda表示的代码块将会代替实现抽象方法的方法体。
 *
 * 形参列表：形参列表允许省略形参类型。如果形参列表只有一个参数，甚至连形参列表的圆括号都可以省略。
 *
 * 箭头 ->必须通过英文中线和大于符号组成
 *
 * 代码块，如果代码块只有一条语句，允许省略代码块的花括号。Lambada代码块只有一条return语句，甚至可以省略return关键字。Lambda需要返回值，儿它的代码块省略了return关键字，会自动返回这条语句的值。
 */
public class LambdaTest {

    public static void main(String[] args) {

        //创建线程
        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("---");
            }
        });
        td.start();

        /**
         * Lambda表达式
         * 1. ()代表形参，入参
         * 2. 后面{}中写方法体，当代码块只有一条语句，允许省略代码块的花括号
         */
        Thread td1 = new Thread(() -> System.out.println("---"));
        td1.start();

        //排序
        List<String> list = Arrays.asList(new String[]{"a", "c", "b"});

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        System.out.println(list);

        //表达式
        Collections.sort(list, (str2, str1) -> str1.compareTo(str2));
        System.out.println(list);

        /** 只有一个入参时，可省略括号 只有1个入参，只有1条语句 */
        list.forEach( p -> System.out.println(p));


        LambdaTest lc = new LambdaTest();
        /** 代码块中只有一条语句，即使该表达式需要返回值，也可以省略return关键字 */
        lc.Method3((a, b) -> a + b);

    }

    public void Method3(InterfaceClass3 inClass3){
        //inClass3.InterfaceClass3Method(5, 3);
        System.out.println(inClass3.InterfaceClass3Method(5, 3));
    }
}

interface InterfaceClass3{
    int InterfaceClass3Method(int a, int b);
}
