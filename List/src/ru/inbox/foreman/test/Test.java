package ru.inbox.foreman.test;

import ru.inbox.foreman.myList.SingleLinkedList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Test
 *
 * @author SergeyNF
 */
public class Test {

    public static void main(String[] arg) {
        SingleLinkedList<String> singleLinkedList = new SingleLinkedList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNext()) {
                singleLinkedList.add(scanner.next().split("[\\s ,.()]")[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Size = " + singleLinkedList.getSize());
        System.out.println(singleLinkedList.toString());

        SingleLinkedList<String> newList = singleLinkedList.copy();
        System.out.println("copy");
        System.out.println(newList.toString());
        System.out.println("Size = " + newList.getSize());

        singleLinkedList.reverse();
        System.out.println("revers");
        System.out.println(singleLinkedList.toString());

        System.out.println("Первый элемент: " + singleLinkedList.get());

        int indexElement = 2;

        System.out.printf("Элемент №%d: %s%n", indexElement, singleLinkedList.getAtIndex(indexElement));

        System.out.printf("Элемент №%d: %s заменен на: ЗАМЕНА%n", indexElement, singleLinkedList.setAtIndex(indexElement, "ЗАМЕНА"));
        System.out.printf("Элемент №%d: %s%n", indexElement, singleLinkedList.getAtIndex(indexElement));
        System.out.println(singleLinkedList.toString());

        System.out.printf("Элемент №%d: %s удален%n", indexElement, singleLinkedList.removeAtIndex(indexElement));
        System.out.printf("Элемент №%d: %s%n", indexElement -1, singleLinkedList.getAtIndex(indexElement-1));
        System.out.println("Size = " + singleLinkedList.getSize());
        System.out.println(singleLinkedList.toString());

        String word = "ВСТАВКА";
        singleLinkedList.addAtIndex(indexElement -1, word);
        System.out.printf("Перед элементом №%d вставлен %s%n", indexElement, word);

        indexElement = 0;
        System.out.printf("Элемент №%d: %s%n", indexElement, singleLinkedList.getAtIndex(indexElement));
        System.out.println("Size = " + singleLinkedList.getSize());
        System.out.println(singleLinkedList.toString());

        word = "the";
        System.out.printf("Удаление элемента %s: %s%n", word, singleLinkedList.remove(word));
        System.out.println(singleLinkedList.toString());
        System.out.println("Size = " + singleLinkedList.getSize());


        singleLinkedList.add(null);
        singleLinkedList.add(null);


        word = "if";
        System.out.printf("Удаление элемента %s: %s%n", word, singleLinkedList.remove(word));
        System.out.println(singleLinkedList.toString());
        System.out.println("Size = " + singleLinkedList.getSize());


        System.out.println("Удаление элементов");
        while (singleLinkedList.getSize() != 0) {
            singleLinkedList.remove();
        }
        System.out.println(singleLinkedList.toString());
        System.out.println("Size = " + singleLinkedList.getSize());

        System.out.println("Реверс пустого");
        singleLinkedList.reverse();
    //    System.out.println("Удаление в пустом" + singleLinkedList.remove());

    }
}


