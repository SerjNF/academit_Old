package ru.inbox.foreman.testVector;

import ru.inbox.foreman.vector.Vector;

/**
 * Testing class Vector
 */
public class TestVector {


    public static void main(String[] art) {
        Vector v1;
        Vector v2;
        try {
            v1 = new Vector(3, new double[]{1.0, 5, 4});
            v2 = new Vector(2, new double[]{2, 6});

            System.out.printf("размерность вектора v1 = %d%n", v1.getSize());
            System.out.printf("размерность вектора v2 = %d%n", v2.getSize());

            System.out.printf("вектор v1 = %s%n", v1.toString());
            System.out.printf("вектор v2 = %s%n", v2.toString());
            System.out.printf("прибавление к вектору v1 вектора v2 = %s%n", v1.addition(v2).toString());
            System.out.printf("вычитание из вектора v1 вектора v2 = %s%n", v1.subtraction(v2).toString());
            System.out.printf("длина вектра v1 = %s%n", v1.getVectorLength());
            System.out.printf("длина вектра v2 = %s%n", v2.getVectorLength());

            System.out.println("значение вектора v1 по индексу 1 заменено на -5");
            v1.setComponentVector(1, -5);

            System.out.printf("значение вектора v1 по индексу 1 = %.1f%n", v1.getComponentVector(1));
            System.out.printf("развернутый вектор v2 = %s%n", v2.reverse().toString());
            System.out.printf("сложение векторов v1 и v2 = %s%n", Vector.sumVectors(v1, v2));
            System.out.printf("разность векторов v1 - v2 = %s%n", Vector.diffVectors(v1, v2));
            System.out.printf("разность векторов v2 - v1 = %s%n", Vector.diffVectors(v2, v1));
            System.out.printf("скалярное умножение векторов v1 * v2 = %s%n", Vector.multiplicationVectors(v1, v2));

        } catch (IllegalArgumentException e) {
            System.out.println("не допустимая длина массива");
        }
    }


}
