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

        singleLinkedList.reverse();
        System.out.println("revers");
        System.out.println(singleLinkedList.toString());

        System.out.println("Первый элемент: " + singleLinkedList.get());

        int indexElement = 3;

        System.out.printf("Элемент №%d: %s%n", indexElement, singleLinkedList.getAtIndex(indexElement));

        System.out.printf("Элемент №%d: %s заменен на ЗАМЕНА%n", indexElement, singleLinkedList.setAtIndex(indexElement, "ЗАМЕНА"));
        System.out.printf("Элемент №%d: %s%n", indexElement, singleLinkedList.getAtIndex(indexElement));
        System.out.println(singleLinkedList.toString());

        System.out.printf("Элемент №%d: %s удален%n", indexElement, singleLinkedList.removeAtIndex(indexElement));
        System.out.printf("Элемент №%d: %s%n", indexElement, singleLinkedList.getAtIndex(indexElement));
        System.out.println("Size = " + singleLinkedList.getSize());
        System.out.println(singleLinkedList.toString());

        String addWord = "ВСТАВКА";
        singleLinkedList.addAtIndex(indexElement, addWord);
        System.out.printf("Перед элементом №%d вставлен %s%n", indexElement, addWord);

        indexElement = 4;
        System.out.printf("Элемент №%d: %s%n", indexElement, singleLinkedList.getAtIndex(indexElement));
        System.out.println("Size = " + singleLinkedList.getSize());
        System.out.println(singleLinkedList.toString());

        addWord = "the";
        System.out.printf("Удаление элемента %s: %s%n", addWord, singleLinkedList.remove(addWord));
        System.out.println(singleLinkedList.toString());
        System.out.println("Size = " + singleLinkedList.getSize());

        addWord = "if";
        System.out.printf("Удаление элемента %s: %s%n", addWord, singleLinkedList.remove(addWord));
        System.out.println(singleLinkedList.toString());
        System.out.println("Size = " + singleLinkedList.getSize());

        while (singleLinkedList.getSize() != 0) {
            singleLinkedList.remove();
        }
        System.out.println(singleLinkedList.toString());
        System.out.println("Size = " + singleLinkedList.getSize());
    }
}


