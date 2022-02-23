package com.example.demo.designpatterns.action.template;

public class TemplatePatternTest {
    public static void main(String[] args) {

        TemplateGame game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
