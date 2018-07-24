package ru.inbox.foreman.delRepeatNumbers;

import java.util.ArrayList;
import java.util.Arrays;

public class DelRepeatNumbers {
    public static void main(String[] arg) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 5, 3, 7, 2, 8, 9, 1, 15, 5, 7, 9));
        ArrayList<Integer> repeatNumbers = new ArrayList<>(numbers.size());

        for (Integer n : numbers) {
            if (!repeatNumbers.contains(n)) {
                repeatNumbers.add(n);
            }
        }
        System.out.println(repeatNumbers.toString());
    }
}
