package ru.inbox.foreman.test;

import ru.inbox.foreman.myList.SingleLinkedList;

import java.util.LinkedList;

public class Test2 {
    public static void main(String[] arg) {
        LinkedList<String> singleLinkedList = new LinkedList<>();
        singleLinkedList.add("1");
        singleLinkedList.add(null);
        singleLinkedList.add(null);
        System.out.println(singleLinkedList.remove(1));
        System.out.println(singleLinkedList.remove(null));
    }
}
