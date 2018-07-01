package ru.inbox.foreman.testVector;

import ru.inbox.foreman.vector.Vector;

public class TestVector {


    public static void main(String[] art) {
        Vector v1;
        Vector v2;
        try {
            v1 = new Vector(3, new double[]{1.0, 5, 4});
            v2 = new Vector(2, new double[]{2, 6});
            System.out.println(v1.toString());
            System.out.println(v2.toString());
            System.out.println(v1.addition(v2).toString());
            System.out.println(v1.subtraction(v2).toString());
            System.out.println(v2.getVectorLength());
        } catch (IllegalArgumentException e) {
            System.out.println("не допустимая длина массива");
        }
    }


}
