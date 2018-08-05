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
    public Vector(double[] vector) {
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
        // что бы ворнинги убрать, для tmp использовал конструктор со значениями из массива
        Vector tmp = new Vector(v1.vector);
        tmp.addition(v2);
        return tmp;
    }

    /**
     * Статический метод вычитания из вектора v1 вектора v2
     *
     * @param v1 вектор 1 - из которого вычитают
     * @param v2 вектор 2 - вычитаемый
     * @return результирующий вектор, с наибольшей разверностью
     */
    public static Vector diffVectors(Vector v1, Vector v2) {
        Vector tmp = new Vector(v1);
        tmp.subtraction(v2);
        return tmp;
    }

    /**
     * Статик метод скалярного умножения векторов
     *
     * @param v1 вектор 1
     * @param v2 вектор 2
     * @return везультат скалярного умножения векторов
     */
    public static double multiplicationVectors(Vector v1, Vector v2) {
        int minLength = Math.min(v1.vector.length, v2.vector.length);
        double result = 0;
        for (int i = 0; i < minLength; ++i) {
            result += v1.vector[i] * v2.vector[i];
        }
        return result;
    }

    /**
     * Получение размерности массива
     *
     * @return int, размерность массива
     */
    public int getSize() {
        return vector.length;
    }

    public double[] toArray() {
        return Arrays.copyOf(vector, vector.length);
    }

    /**
     * Прибавление к вектору другого вектора
     *
     * @param v прибавляемый вектор
     */
    public void addition(Vector v) {
        if (v.vector.length <= this.vector.length) {
            for (int i = 0; i < v.vector.length; ++i) {
                vector[i] += v.vector[i];
            }
        } else {
            double[] tmp = Arrays.copyOf(v.vector, v.vector.length);
            for (int i = 0; i < this.vector.length; ++i) {
                tmp[i] += this.vector[i];
            }
            this.vector = tmp;
        }
    }

    /**
     * Вычитание из вектора другого вектора
     *
     * @param v вычитаемый вектор
     */
    public void subtraction(Vector v) {
        if (v.vector.length <= this.vector.length) {
            for (int i = 0; i < v.vector.length; ++i) {
                vector[i] -= v.vector[i];
            }
        } else {
            double[] tmp = Arrays.copyOf(this.vector, v.vector.length);
            for (int i = 0; i < v.vector.length; ++i) {
                tmp[i] -= v.vector[i];
            }
            this.vector = tmp;
        }
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
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.vector.length);
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
