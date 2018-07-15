package ru.inbox.foreman.testMatrix;

import ru.inbox.foreman.matrix.Matrix;
import ru.inbox.foreman.vector.Vector;

import java.util.Arrays;

/**
 * Matrix test
 *
 * @author SergeyNF
 * @since 14.07.2018
 */
public class testMatrix {
    public static void main(String[] arg) {


        try {
            Matrix m1 = new Matrix(3, 2);
            System.out.printf("m1: %n%s", m1.toString());
            System.out.println();

            double[][] matrix2 = new double[][]{{1, 2, 6}, {4, 3, -1}, {2, -2, 5}};
            Matrix m2 = new Matrix(matrix2);
            System.out.printf("m2: %n%s", m2.toString());
            System.out.println();

            double[][] matrix3 = new double[][]{{2, 3}, {6, 5}, {11, 12}, {5, 6}};
            Matrix m3 = new Matrix(matrix3);
            System.out.printf("m3: %n%s", m3.toString());
            System.out.println();

            Matrix m4 = new Matrix(new double[][]{{2, 3}, {6, 5}, {11, 12}, {5, 6}, {9, 7}});
            System.out.printf("m4: %n%s", m4.toString());
            System.out.println();

            System.out.printf("Размер матрицы m1 = %s%n", Arrays.toString(m1.getSize()));
            System.out.printf("Размер матрицы m2 = %s%n", Arrays.toString(m2.getSize()));
            System.out.printf("Размер матрицы m3 = %s%n", Arrays.toString(m3.getSize()));
            System.out.println();

            System.out.printf("Вектор из матрицы 2, колонка 2: %s", m2.getVectorInColumn(1).toString());
            System.out.println();

            m3.transposition();
            System.out.printf("Транспонирование матрицы m3:%n%s", m3.toString());
            System.out.println();

            m3.setVectorInRow(1, new Vector(4, new double[]{4, 4, 4, 4}));
            System.out.printf("Замена стороки 2 матрицы m3 на {4, 4, 4, 4}:%n%s", m3.toString());
            System.out.println();

            m3.multiplicationMatrixOnScalar(5.0);
            System.out.printf("Умножение матрицы m3 на 5:%n%s", m3.toString());
            System.out.println();

            m3.additionMatrix(m2);
            System.out.printf("матрица 3 после прибавления к ней матрицы 2: %n%s%n", m3.toString());
            System.out.println("Проверка что матрица m2 не изменилась");
            System.out.println(m2.toString());

            m3.subtractionMatrix(m4);
            System.out.printf("матрицы 3 после вычитания из неё матрицы 4: %n%s%n", m3.toString());

            m3.multiplicationMatrixOnVector(new Vector(new double[]{1, 2, 3}));
            System.out.printf("матрицы 3 после умножениея на вектор {1, 2, 3} : %n%s%n", m3.toString());

            System.out.printf("Определитель матрицы m2 = %.2f%n%n", m2.getDetermination());

            System.out.printf("Произведение матриц%n{{1, 0, 2}, {0, -1, 3}, {4, 0, 5}} * {{2, 7, 1}, {3, 2, -4}, {1, -3, 5}}%n%n%s%n", Matrix.multiplication(new Matrix(new double[][]{{1, 0, 2}, {0, -1, 3}, {4, 0, 5}}), new Matrix(new double[][]{{2, 7, 1}, {3, 2, -4}, {1, -3, 5}})));
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
