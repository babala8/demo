package com.example.demo.skill.apimethod.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author ldy
 * @version 1.0
 */
public class ArrayFilterTest {

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>(2);
        arrayList.add(2);
        arrayList.add(5);
        boolean b = Arrays.stream(new int[arrayList.size()]).anyMatch(2);

    }
}
