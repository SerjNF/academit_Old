package ru.inbox.foreman.testMatrix;

import ru.inbox.foreman.matrix.Matrix;

import java.util.Arrays;

public class testMatrix {
    public static void main(String[] arg) {

        //TODO try Catch
        Matrix m1 = new Matrix(3, 2);
        System.out.println(m1.toString());
        System.out.println();

        double[][] matrix = new double[][]{{2, 3}, {6, 5, 7}, {11, 12, 15}, {5,6,9,7,8},{8,9,6,52,1}};
        Matrix m2 = new Matrix(matrix);
        System.out.println(m2.toString());
        System.out.println();

        Matrix m3 = new Matrix(m2);
        System.out.println(m3.toString());
        System.out.println();
        System.out.printf("Размер матрицы m1 = %s%n", Arrays.toString(m1.getSize()));
        System.out.printf("Размер матрицы m2 = %s%n", Arrays.toString(m2.getSize()));
        System.out.printf("Размер матрицы m3 = %s%n", Arrays.toString(m3.getSize()));
        System.out.println();

        System.out.printf("Вектор из матрицы 2, колонка 3 : %s%n", m2.getVectorInColumn(2).toString());
        System.out.println();
        m3.transposition();
        System.out.printf("Транспонирование матрицы m3:%n%s%n", m3.toString());
    }
}
