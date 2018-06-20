package ru.inbox.foreman;

import java.util.ArrayList;

/**
 * Test Method
 *              \------------------F---------------------\
 * ____________3.2====А======7_____________12====С=====18.5________________________________>
 *                  5------В-------10
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
            System.out.println("А и В не пересекаются%n");
        }

        Range E = A.interceptRange(C);
        if (E != null) {
            System.out.printf("Результат пересечения A и C диапазон D (%.2f, %.2f)%n%n", E.getFrom(), E.getTo());
        } else {
            System.out.println("А и C не пересекаются");

        }

        // объединение диапазонов
        System.out.println();
        System.out.println("Результат объединения диапазонов А и В");
        ArrayList<Range> sunAandB = Range.sumOfRanges(A, B);

        for (int i = 0; i < sunAandB.size(); ++i) {
            Range range = sunAandB.get(i);
            System.out.printf("Диапазон %d (%.2f, %.2f)%n", i + 1, range.getFrom(), range.getTo());
        }
        System.out.println();

        System.out.println("Результат объединения диапазонов А и С");
        ArrayList<Range> sunAC = Range.sumOfRanges(A, C);

        for (int i = 0; i < sunAC.size(); ++i) {
            Range range = sunAC.get(i);
            System.out.printf("Диапазон %d (%.2f, %.2f)%n", i + 1, range.getFrom(), range.getTo());
        }
        System.out.println();

        // Разность диапазонов

        System.out.println();
        System.out.println("Результат вычитания из А, В");
        ArrayList<Range> subtractAB = Range.subtractOfRange(A, B);

        for (int i = 0; i < subtractAB.size(); ++i) {
            Range range = subtractAB.get(i);
            System.out.printf("Диапазон %d (%.2f, %.2f)%n", i + 1, range.getFrom(), range.getTo());
        }
        System.out.println();


        System.out.printf("Результат вычитания из F(%.2f, %.2f), B%n", F.getFrom(), F.getTo());
        ArrayList<Range> subtractFB = Range.subtractOfRange(F, B);

        for (int i = 0; i < subtractFB.size(); ++i) {
            Range range = subtractFB.get(i);
            System.out.printf("Диапазон %d (%.2f, %.2f)%n", i + 1, range.getFrom(), range.getTo());
        }
        System.out.println();

    }
}
