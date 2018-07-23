package ru.inbox.foreman.testMatrix;

import ru.inbox.foreman.matrix.Matrix;
import ru.inbox.foreman.vector.Vector;

/**
 * Matrix test
 *
 * @author SergeyNF
 * @since 14.07.2018
 */
public class TestMatrix {
    public static void main(String[] arg) {


        try {
            Matrix m1 = new Matrix(3, 2);
            System.out.printf("m1: %n%s", m1.toString());
            System.out.println();

            double[][] matrix2 = new double[][]{{1, 2, 6,}, {4, 3, -1}, {2, -2, 5}};
            Matrix m2 = new Matrix(matrix2);
            System.out.printf("m2: %n%s", m2.toString());
            System.out.println();

            double[][] matrix3 = new double[][]{{2, 3, 1}, {6, 5, 1}, {11, 121, 1}};
            Matrix m3 = new Matrix(matrix3);
            System.out.printf("m3: %n%s", m3.toString());
            System.out.println();

            Matrix m4 = new Matrix(new double[][]{{2, 3}, {6, 5}, {11, 12}});
            System.out.printf("m4: %n%s", m4.toString());
            System.out.println();

            System.out.printf("Размер матрицы m1 = %d x %d%n", m1.getColumn(), m1.getRows());
            System.out.printf("Размер матрицы m2 = %d x %d%n", m2.getColumn(), m2.getRows());
            System.out.printf("Размер матрицы m3 = %d x %d%n", m3.getColumn(), m3.getRows());
            System.out.println();

            System.out.printf("Вектор из матрицы 2, колонка 2: %s", m2.getVectorInColumn(1).toString());
            System.out.println();

            m3.transposition();
            System.out.printf("Транспонирование матрицы m3:%n%s", m3.toString());
            System.out.println();

            m3.setVectorInRow(1, new Vector(3, new double[]{4, 4, 4}));
            System.out.printf("Замена стороки 2 матрицы m3 на {4, 4, 4}:%n%s", m3.toString());
            System.out.println();

            m3.multiplicationMatrixOnScalar(5.0);
            System.out.printf("Умножение матрицы m3 на 5:%n%s", m3.toString());
            System.out.println();

            m3.additionMatrix(m2);
            System.out.printf("Матрица 3 после прибавления к ней матрицы 2: %n%s%n", m3.toString());
            System.out.println("Проверка что матрица m2 не изменилась");
            System.out.println(m2.toString());

            m3.subtractionMatrix(m4);
            System.out.printf("матрицы 3 после вычитания из неё матрицы 4: %n%s%n", m3.toString());

            System.out.printf("Результирующий вектор после  умножения матрицы 3 на вектор {1, 2, 3} : %n%s%n", m3.multiplicationMatrixOnVector(new Vector(new double[]{1, 2, 3})).toString());

            System.out.printf("Размер матрицы m2 = %d x %d%n", m2.getColumn(), m2.getRows());
            System.out.printf("Определитель матрицы m2 = %.2f%n%n", m2.getDetermination());

            System.out.printf("Произведение матриц:%n{{1, 0, 2}, {0, -1, 3}, {4, 0, 5}} * {{2, 7, 1}, {3, 2, -4}, {1, -3, 5}}%n%n%s%n", Matrix.multiplication(new Matrix(new double[][]{{2, 7, 1}, {3, 2, -4}}), new Matrix(new double[][]{{1, 0}, {0, -1}, {4, 0}})));
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
