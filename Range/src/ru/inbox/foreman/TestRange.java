package ru.inbox.foreman.ru.inbox.foreman;

import java.util.ArrayList;

/**
 * Test Method for class Range
 * .............\------------------F---------------------\
 * ____________3.2====А======7_____________12====С=====18.5________________>
 * .................5------В-------10
 *
 * @author SergeyNF
 * @since 20.06.2018
 */
public class TestRange {

    public static void main(String[] arg) {

        Range A = new Range(3.2, 7);
        Range B = new Range(5, 10);
        Range C = new Range(12, 18.5);
        Range F = new Range(3.2, 18.5);

        System.out.printf("Диапазон А (%.2f, %.2f)%n", A.getFrom(), A.getTo());
        System.out.printf("Диапазон B (%.2f, %.2f)%n", B.getFrom(), B.getTo());
        System.out.printf("Диапазон C (%.2f, %.2f)%n%n", C.getFrom(), C.getTo());

        // Проверка вхождения числа
        double number = 4.0;

        System.out.printf("Число %.2f %s в диапазон A длинной %.2f%n", number, (A.isInside(number)) ? "входит" : "не входит", A.calcLength());
        System.out.printf("Число %.2f %s в диапазон В длинной %.2f%n%n", number, (B.isInside(number)) ? "входит" : "не входит", B.calcLength());

        // Проверка пересечения
        Range D = A.interceptRange(B);
        if (D != null) {
            System.out.printf("Результат пересечения A и В диапазон D (%.2f, %.2f)%n", D.getFrom(), D.getTo());
        } else {
            System.out.println("Результат пересечения, А и В не пересекаются%n");
        }

        Range E = A.interceptRange(C);
        if (E != null) {
            System.out.printf("Результат пересечения A и C диапазон E (%.2f, %.2f)%n%n", E.getFrom(), E.getTo());
        } else {
            System.out.println("Результат пересечения, А и C не пересекаются");

        }

        // Объединение диапазонов
        sumRanges(A, B);
        sumRanges(B, F);
        sumRanges(C, B);
        sumRanges(A, C);


        // Разность диапазонов
        diffRange(A, B);
        diffRange(F, C);
        diffRange(C, F);
    }

    private static void sumRanges(Range one, Range two) {

        System.out.printf("Результат объединения диапазонов %s и %s%n", one.rangeToString(), two.rangeToString());
        ArrayList<Range> sun = Range.sumOfRanges(one, two);

        for (int i = 0; i < sun.size(); ++i) {
            Range range = sun.get(i);
            System.out.printf("Диапазон %d (%.2f, %.2f)%n", i + 1, range.getFrom(), range.getTo());
        }
        System.out.println();
    }

    private static void diffRange(Range one, Range two) {
        System.out.printf("Результат вычитания из диапазона %s диапазона %s%n", one.rangeToString(), two.rangeToString());

        ArrayList<Range> subtract = Range.subtractOfRange(one, two);

        if (subtract != null) {
            for (int i = 0; i < subtract.size(); ++i) {
                Range range = subtract.get(i);
                System.out.printf("Диапазон %d (%.2f, %.2f)%n", i + 1, range.getFrom(), range.getTo());
            }
        } else {
            System.out.println("Результат, диапазон нулевой длины либо бесконечность");
        }
        System.out.println();
    }
}
