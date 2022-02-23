package com.example.demo.skill.apimethod.doublepoint;

public class TestDouble {
    public static void main(String[] args) {
        double num = 168588866654.0d;
        System.out.println(num);

        java.text.DecimalFormat df = new java.text.DecimalFormat("##########.00");
        String format = df.format(num);
        System.out.println(format);
        System.out.println(Double.parseDouble(format));
    }
}
