package ru.inbox.foreman.test;

//import ru.inbox.foreman.MyArraList.MyArrayList;

import ru.inbox.foreman.MyArraList.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class Test1 {
    public static void main(String[] arg) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        System.out.printf("isEmpty: %s%n", myArrayList.isEmpty());
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(5);
        myArrayList.add(null);
        myArrayList.add(12);
        myArrayList.add(0);
        myArrayList.add(10);
        System.out.println("isEmpty: " + myArrayList.isEmpty());

        System.out.println("Length = " + myArrayList.size());

        Iterator<Integer> iterator = myArrayList.iterator();
        System.out.println("Проход итератором:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        System.out.printf("Удаление значения 0: %s%n", myArrayList.remove(new Integer(0)));
        System.out.println("toString(toArray): " + Arrays.toString(myArrayList.toArray()));
        System.out.println("Length = " + myArrayList.size());

//        System.out.printf("Удаление значения null: %s%n", myArrayList.remove(null));
//        System.out.println("toString(toArray): " + Arrays.toString(myArrayList.toArray()));
//        System.out.println("Length = " + myArrayList.size());

        System.out.println("добавление 33 по индексу 3");
        myArrayList.add(3, 33);
        System.out.println("MyArrayList toString(toArray): " + Arrays.toString(myArrayList.toArray()));
        System.out.println("Length = " + myArrayList.size());

        System.out.println("добавление 66 по индексу 6");
        myArrayList.add(6, 66);
        System.out.println("toString(toArray): " + Arrays.toString(myArrayList.toArray()));
        System.out.println("Length = " + myArrayList.size());

        System.out.println("Значение, удаленное по индексу 5: " + myArrayList.remove(5));
        System.out.println("MyArrayList toString(toArray): " + Arrays.toString(myArrayList.toArray()));
        System.out.println("Length = " + myArrayList.size());


        ArrayList<Integer> list = new ArrayList<>(3);

        list.add(1);
        list.add(2);
        list.add(5);
        list.add(33);
        list.add(null);
        list.add(66);
        list.add(2);
        list.add(5);
        list.add(33);
        list.add(null);
        list.add(66);
      //  list.add(10);

        System.out.println("retailAll: " + myArrayList.retainAll(list));
        System.out.println(Arrays.toString(myArrayList.toArray()));
        System.out.println("Length = " + myArrayList.size());

        System.out.println("Вторая коллекция: " + Arrays.toString(list.toArray()));

        System.out.println("Проверка вхождения второй коллекции в первую: " + myArrayList.containsAll(list));

        System.out.print("к первой коллекции добавлена вторая: ");
        System.out.println(myArrayList.addAll(list));
        System.out.println("MyArrayList toString(toArray): " + Arrays.toString(myArrayList.toArray()));
        System.out.println("Length = " + myArrayList.size());

        System.out.print("к первой коллекции добавлена вторая по индексу 3: ");
        System.out.println(myArrayList.addAll(3, list));
        System.out.println("MyArrayList toString(toArray): " + Arrays.toString(myArrayList.toArray()));
        System.out.println("Length = " + myArrayList.size());


        System.out.println("222 индекс последнего вхождения: " + myArrayList.lastIndexOf(222));

        ListIterator<Integer> listIterator = myArrayList.listIterator(2);
        System.out.println("Проход лист-итератором индекса 2, вставляя значение 505 и добавляя 1000:");
        listIterator.set(505);
        listIterator.add(1000);
        listIterator.add(null);
        listIterator.remove();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();
//new ArrayList<>(Arrays.asList(8, 9))
        System.out.print("удаление из первой коллекции элементов второй коллекции: ");
        System.out.println(myArrayList.removeAll(list));
        System.out.println("MyArrayList toString(toArray): " + Arrays.toString(myArrayList.toArray()));
        System.out.println("Length = " + myArrayList.size());
    }
}
