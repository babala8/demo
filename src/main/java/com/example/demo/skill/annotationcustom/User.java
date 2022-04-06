package com.example.demo.skill.annotationcustom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userName;
    @AnnotationCustom
    private String phoneNumber;

    public static void main(String[] args) {

        try {
            User user = new User("哈哈哈","aaa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
