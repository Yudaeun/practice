package org.example.day5;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue<String> q=new ArrayDeque<>();

        q.offer("kim");
        q.offer("lee");
        q.offer("park");
        q.offer("choi");

        System.out.println(q.size());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.peek());
        System.out.println(q.size());
        System.out.println(q.isEmpty());

    }
}
