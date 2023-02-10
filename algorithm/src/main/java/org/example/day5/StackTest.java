package org.example.day5;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> colors=new Stack<>();
        colors.push("red");
        colors.push("blue");
        colors.push("yellow");
        colors.push("pink");
        colors.push("brown");

        System.out.println(colors.size());
        System.out.println(colors.isEmpty());

        System.out.println(colors.pop());
        System.out.println(colors.pop());
        System.out.println(colors.pop());

    }
}
