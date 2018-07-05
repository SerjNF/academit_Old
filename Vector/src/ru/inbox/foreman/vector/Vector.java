package ru.inbox.foreman.vector;

import java.util.Arrays;

/**
 * Класс вектора размерности n
 *
 * @author SergeyNF
 * @since 02.07.2018
 */
public class Vector {

    private double[] vector;

    /**
     * Конструктор вектора назмерности n.
     *
     * @param n размерность
     * @throws IllegalArgumentException - исключение при размерности ветора меньше либо равной 0
     */
    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора не можеты быть меньше либо равной 0");
        }
        this.vector = new double[n];
    }

    /**
     * Конструктор. копирование вектора
     *
     * @param v вектор
     */
    public Vector(Vector v) {
        this.vector = Arrays.copyOf(v.vector, v.vector.length);
    }

    /**
     * Конструктор. Заполнение вектора значениями из массива
     *
     * @param vector массив со значениями
     */
    private Vector(double[] vector) {
        if (vector.length <= 0) {
            throw new IllegalArgumentException("Размерность вектора не можеты быть меньше либо равной 0");
        }
        this.vector = Arrays.copyOf(vector, vector.length);
    }

    /**
     * Конструктор. создание массива размерности n и заполнение вектора значениями из массива
     *
     * @param n      размерность
     * @param vector массив со значениями
     * @throws IllegalArgumentException исключение при длине массива боьшей размерности
     */
    public Vector(int n, double[] vector) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора не можеты быть меньше либо равной 0");
        }
        this.vector = Arrays.copyOf(vector, n);
    }

    /**
     * Статический метод сложения 2х векторов
     *
     * @param v1 вектор 1
     * @param v2 вектор 2
     * @return результирующий вектор, с наибольшей разверностью
     */

    public static Vector sumVectors(Vector v1, Vector v2) {
        return new Vector(sumArray(v1.vector, v2.vector));
    }

    /**
     * Статический метод вычитания из вектора v1 вектора v2
     *
     * @param v1 вектор 1 - из которого вычитают
     * @param v2 вектор 2 - вычитаемый
     * @return результирующий вектор, с наибольшей разверностью
     */
    public static Vector diffVectors(Vector v1, Vector v2) {
        return new Vector(diffArray(v1.vector, v2.vector));
    }

    /**
     * Статик метод скалярного умножения векторов
     *
     * @param v1 вектор 1
     * @param v2 вектор 2
     * @return везультат скалярного умножения векторов
     */
    public static double multiplicationVectors(Vector v1, Vector v2) {
        int maxLength = v1.vector.length >= v2.vector.length ? v1.vector.length : v2.vector.length;
        double result = 0;
        for (int i = 0; i < maxLength; ++i) {
            result += (i < v1.vector.length ? v1.vector[i] : 0) * (i < v2.vector.length ? v2.vector[i] : 0);
        }
        return result;
    }

    /**
     * Функция сложения 2х массивов почленно
     *
     * @param array1 массив 1
     * @param array2 массив 2
     * @return массив, результат сложения
     */
    private static double[] sumArray(double[] array1, double[] array2) {
        double[] resArray = new double[array1.length >= array2.length ? array1.length : array2.length];
        for (int i = 0; i < resArray.length; ++i) {
            resArray[i] = (i < array1.length ? array1[i] : 0) + (i < array2.length ? array2[i] : 0);
        }
        return resArray;
    }

    /**
     * функция вычитания из массива 1 массива 2 почленно
     *
     * @param array1 массив 1
     * @param array2 массив 2
     * @return массив, результат вычитания
     */
    private static double[] diffArray(double[] array1, double[] array2) {
        double[] resArray = new double[array1.length >= array2.length ? array1.length : array2.length];
        for (int i = 0; i < resArray.length; ++i) {
            resArray[i] = (i < array1.length ? array1[i] : 0) - (i < array2.length ? array2[i] : 0);
        }
        return resArray;
    }

    /**
     * Получение размерности массива
     *
     * @return int, размерность массива
     */
    public int getSize() {
        return vector.length;
    }

    /**
     * Прибавление к вектору другого вектора
     *
     * @param v прибавляемый вектор
     */
    public void addition(Vector v) {
        this.vector = sumArray(this.vector, v.vector);
    }

    /**
     * Вычитание из вектора другого вектора
     *
     * @param v вычитаемый вектор
     */
    public void subtraction(Vector v) {
        this.vector = diffArray(this.vector, v.vector);
    }

    /**
     * Разварот вектора (умножение на -1)
     */
    public void reverse() {
        multiplicationOnScalar(-1);
    }

    /**
     * Умножение вектора на скаляр
     *
     * @param scalar double, величина на которую умножается вектор
     */
    public void multiplicationOnScalar(double scalar) {
        for (int i = 0; i < this.vector.length; ++i) {
            this.vector[i] *= scalar;
        }
    }

    /**
     * Возврат значения из массива вектора по индексу
     *
     * @param index индекс в массиве
     * @return значение компанента по индексу
     * @throws IndexOutOfBoundsException - исключение при значении индекса выходящего за пределами массива
     */
    public double getVectorComponent(int index) {
        if (index >= this.vector.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.vector[index];
    }

    /**
     * Установка значения в массиве вектора по индексу
     *
     * @param index  индекс в массиве
     * @param number устанавливаемое значение
     * @throws IndexOutOfBoundsException - исключение при значении индекса выходящего за пределами массива
     */
    public void setVectorComponent(int index, double number) {
        if (index >= this.vector.length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.vector.length);
        }
        this.vector[index] = number;

    }

    /**
     * длина вектора
     *
     * @return double, длина вектора
     */
    public double getVectorLength() {
        double vectorLength = 0;
        for (double v : this.vector) {
            vectorLength += v * v;
        }
        return Math.sqrt(vectorLength);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < vector.length; ++i) {
            sb.append(vector[i]);
            if (i < vector.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = vector.length;
        int prime = 20;
        hash = prime * hash + Arrays.hashCode(vector);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Vector v = (Vector) o;

        if (this.vector.length != v.vector.length) {
            return false;
        }
        for (int i = 0; i < this.vector.length; ++i) {
            if (this.vector[i] != v.vector[i]) {
                return false;
            }
        }
        return true;
    }
}
