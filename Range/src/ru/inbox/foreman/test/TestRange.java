package ru.inbox.foreman.test;


import ru.inbox.foreman.range.Range;

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

        Range A = new Range(7, 3.2);
        Range B = new Range(5, 10);
        Range C = new Range(12, 18.5);
        Range F = new Range(3.2, 18.5);
        Range G = new Range(5, 12);

        System.out.printf("Диапазон А (%.2f, %.2f)%n", A.getFrom(), A.getTo());
        System.out.printf("Диапазон B (%.2f, %.2f)%n", B.getFrom(), B.getTo());
        System.out.printf("Диапазон C (%.2f, %.2f)%n%n", C.getFrom(), C.getTo());

        // Проверка вхождения числа
        double number = 4.0;

        System.out.printf("Число %.2f %s в диапазон A длинной %.2f%n", number, (A.isInside(number)) ? "входит" : "не входит", A.calcLength());
        System.out.printf("Число %.2f %s в диапазон В длинной %.2f%n%n", number, (B.isInside(number)) ? "входит" : "не входит", B.calcLength());

        // Проверка пересечения
        Range D = A.intersectRanges(B);
        if (D != null) {
            System.out.printf("Результат пересечения A и В диапазон D (%.2f, %.2f)%n%n", D.getFrom(), D.getTo());
        } else {
            System.out.println("Результат пересечения, А и В не пересекаются");
            System.out.println();
        }

        Range E = A.intersectRanges(C);
        if (E != null) {
            System.out.printf("Результат пересечения A и C диапазон E (%.2f, %.2f)%n%n", E.getFrom(), E.getTo());
        } else {
            System.out.println("Результат пересечения, А и C не пересекаются");
            System.out.println();
        }

        // Объединение диапазонов
        sumRanges(A, B);
        sumRanges(A, F);
        sumRanges(C, B);
        sumRanges(A, C);

        // Разность диапазонов
        diffRange(A, B);
        diffRange(F, C);
        diffRange(C, F);
        diffRange(C, G);
        diffRange(F, B);
        diffRange(A, A);
    }

    private static void sumRanges(Range one, Range two) {

        System.out.printf("Результат объединения диапазонов %s и %s%n", one.rangeToString(), two.rangeToString());
        Range[] sun = one.sumOfRanges(two);

        for (int i = 0; i < sun.length; ++i) {
            Range range = sun[i];
            System.out.printf("Диапазон %d (%.2f, %.2f)%n", i + 1, range.getFrom(), range.getTo());
        }
        System.out.println();
    }

    private static void diffRange(Range one, Range two) {
        System.out.printf("Результат вычитания из диапазона %s диапазона %s%n", one.rangeToString(), two.rangeToString());

        Range[] subtract = one.subtractOfRange(two);

        if (subtract.length != 0) {
            if (subtract[0] == null) {
                System.out.println("Путое пересечение");
                System.out.println();
                return;
            }
            for (int i = 0; i < subtract.length; ++i) {
                Range range = subtract[i];
                System.out.printf("Диапазон %d (%.2f, %.2f)%n", i + 1, range.getFrom(), range.getTo());
            }
        } else {
            System.out.println("Нет пересечения, либо результат вычитания - бесконечность");
        }
        System.out.println();
    }
}
