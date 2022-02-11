package com.example.demo.designpatterns.create.prototype;

import lombok.Data;
import lombok.NonNull;

@Data
public class PrototypeByCloneable implements Cloneable{
    public String type;
    public boolean ice;

    /**
     * 值得注意的是，Java 自带的 clone 方法是浅拷贝的。也就是说调用此对象的 clone 方法，
     * 只有基本类型的参数会被拷贝一份，非基本类型的对象不会被拷贝一份，而是继续使用传递引用的
     * 方式。如果需要实现深拷贝，必须要自己手动修改 clone 方法才行。
     */
    @NonNull
    @Override
    // Java 中有一个语法糖，让我们并不需要手写 clone 方法。这个语法糖就是Cloneable 接口，我们只要让需要拷贝的类实现此接口即可。
    protected PrototypeByCloneable clone() throws CloneNotSupportedException {
        return (PrototypeByCloneable) super.clone();
    }

    private void order() throws CloneNotSupportedException {
        PrototypeByCloneable milkTeaOfJay = new PrototypeByCloneable();
        milkTeaOfJay.type = "原味";
        milkTeaOfJay.ice = false;

        PrototypeByCloneable yourMilkTea = milkTeaOfJay.clone();
        System.out.println(yourMilkTea.toString());
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        for (int i = 1; i < 20; i++) {
            PrototypeByCloneable prototype = new PrototypeByCloneable();
            prototype.order();
        }
    }
}