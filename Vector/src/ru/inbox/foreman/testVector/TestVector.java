package ru.inbox.foreman.testVector;

import ru.inbox.foreman.vector.Vector;

/**
 * Testing class Vector
 */
public class TestVector {

    public static void main(String[] art) {

        try {
            Vector v1 = new Vector(3, new double[]{1.0, 5, 4});
            Vector v2 = new Vector(2, new double[]{2, 6});
            Vector v3 = new Vector(5);
            Vector v4 = Vector.sumVectors(v1, v3);

            System.out.printf("размерность вектора v1 = %d%n", v1.getSize());
            System.out.printf("размерность вектора v2 = %d%n", v2.getSize());
            System.out.printf("размерность вектора v3 = %d%n", v3.getSize());
            System.out.printf("размерность вектора v4 = %d%n", v4.getSize());

            System.out.printf("вектор v1 = %s%n", v1.toString());
            System.out.printf("вектор v2 = %s%n", v2.toString());
            System.out.printf("вектор v3 = %s%n", v3.toString());
            System.out.printf("вектор v4 = %s%n", v4.toString());


            System.out.printf("v1 + v2 = %s%n", v1.addition(v2).toString());
            System.out.printf("v1 - v2 = %s%n", v1.subtraction(v2).toString());

            System.out.printf("длина вектора v1 = %s%n", v1.getVectorLength());
            System.out.printf("длина вектора v2 = %s%n", v2.getVectorLength());

            System.out.printf("вектор v1 * 5 = %s%n", v1.multiplicationOnScalar(5.0));

            System.out.println("значение вектора v1[2] заменено на -5");
            v1.setComponentVector(2, -5);

            System.out.printf("значение вектора v1[2] = %.1f%n", v1.getComponentVector(2));
            System.out.printf("развернутый вектор v2 = %s%n", v2.reverse().toString());

            System.out.printf("сложение векторов v1 + v2 = %s%n", Vector.sumVectors(v1, v2));
            System.out.printf("разность векторов v1 - v2 = %s%n", Vector.diffVectors(v1, v2));
            System.out.printf("разность векторов v2 - v1 = %s%n", Vector.diffVectors(v2, v1));

            System.out.printf("скалярное умножение векторов v1 * v2 = %s%n", Vector.multiplicationVectors(v1, v2));

            Vector error = new Vector(0);

        } catch (IllegalArgumentException e) {
            System.out.println("не допустимая длина массива");
        }
    }
}
