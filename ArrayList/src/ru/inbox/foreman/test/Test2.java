package ru.inbox.foreman.test;

import ru.inbox.foreman.MyArraList.MyArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] arg) {

        ArrayList<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNext()) {
                strings.add(scanner.next().split("[\\s ,.()]")[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(strings.toString());

        MyArrayList<String> list1 = new MyArrayList<>(5);
        MyArrayList<String> list2 = new MyArrayList<>();
        list2.add("The");
        list2.add("the");
        list2.add("and");
        list2.add(null);
        list2.add("if");
        list2.add("by");
        list2.add("that");
        list2.add("any");

        list1.addAll(strings);
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println("длина: " + list1.size());

        list1.removeAll(list2);
        System.out.println("После удаления слов из коллекции 2");
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println("длина: " + list1.size());

        list1.addAll(0, list2);
        System.out.println("После добавления слов из коллекции 2");
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println("длина: " + list1.size());

        ListIterator<String> listIterator = list1.listIterator(4);
        System.out.println("печать коллекции лист-итератором");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        list1.ensureCapacity(50);
        list1.trimToSize();

    }
}
