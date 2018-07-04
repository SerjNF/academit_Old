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
            throw new IllegalArgumentException("");
        }
        this.vector = new double[n];
    }

    /**
     * Конструктор. копирование вектора
     *
     * @param v вектор
     */
    public Vector(Vector v) {
        if (v == null) {
            this.vector = null;
        } else {
            this.vector = Arrays.copyOf(v.vector, v.vector.length);
        }
    }

    /**
     * Конструктор. Заполнение вектора значениями из массива
     *
     * @param vector массив со значениями
     */
    private Vector(double[] vector) {
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
        if (n < vector.length) {
            throw new IllegalArgumentException();
        }
        this.vector = Arrays.copyOf(vector, n);
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
        if (this.vector.length < v.vector.length) {
            this.vector = sumArray(Arrays.copyOf(this.vector, v.vector.length), v.vector);
        } else if (this.vector.length > v.vector.length) {
            this.vector = sumArray(Arrays.copyOf(v.vector, this.vector.length), this.vector);
        } else {
            vector = sumArray(this.vector, v.vector);
        }
    }

    /**
     * Вычитание из вектора другого вектора
     *
     * @param v вычитаемый вектор
     */
    public void subtraction(Vector v) {
        if (this.vector.length < v.vector.length) {
            this.vector = diffArray(Arrays.copyOf(this.vector, v.vector.length), v.vector);
        } else if (this.vector.length > v.vector.length) {
            this.vector = diffArray(this.vector, Arrays.copyOf(v.vector, this.vector.length));
        } else {
            this.vector = diffArray(this.vector, v.vector);
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
     * @param pos индекс в массиве
     * @return значение компанента по индексу
     * @throws IndexOutOfBoundsException - исключение при значении индекса выходящего за пределами массива
     */
    public double getVectorComponent(int pos) {
        if (pos > this.vector.length - 1 || pos < 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.vector[pos];

    }

    /**
     * Установка значения в массиве вектора по индексу
     *
     * @param pos    индекс в массиве
     * @param number устанавливаемое значение
     * @throws IndexOutOfBoundsException - исключение при значении индекса выходящего за пределами массива
     */
    public void setVectorComponent(int pos, double number) {
        if (pos > this.vector.length - 1 || pos < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.vector[pos] = number;

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

    /**
     * Статический метод сложения 2х векторов
     *
     * @param v1 вектор 1
     * @param v2 вектор 2
     * @return резулютирующий вектор, с наибольшей разверностью
     */

    public static Vector sumVectors(Vector v1, Vector v2) {
        if (v1.vector.length < v2.vector.length) {
            return new Vector(sumArray(Arrays.copyOf(v1.vector, v2.vector.length), v2.vector));
        }
        if (v1.vector.length > v2.vector.length) {
            return new Vector(sumArray(Arrays.copyOf(v2.vector, v1.vector.length), v1.vector));
        }
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
        if (v1.vector.length < v2.vector.length) {
            return new Vector(diffArray(Arrays.copyOf(v1.vector, v2.vector.length), v2.vector));
        }
        if (v1.vector.length > v2.vector.length) {
            return new Vector(diffArray(v1.vector, Arrays.copyOf(v2.vector, v1.vector.length)));
        }
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
        if (v1.vector.length < v2.vector.length) {
            return multiplicationVectors(Arrays.copyOf(v1.vector, v2.vector.length), v2.vector);
        }
        if (v1.vector.length > v2.vector.length) {
            return multiplicationVectors(v1.vector, Arrays.copyOf(v2.vector, v1.vector.length));
        }
        return multiplicationVectors(v1.vector, v2.vector);
    }

    /**
     * Функция сложения 2х массивов почленно
     *
     * @param array1 массив 1
     * @param array2 массив 2
     * @return массив, результат сложения
     */
    private static double[] sumArray(double[] array1, double[] array2) {
        double[] resArray = new double[array1.length];
        for (int i = 0; i < array1.length; ++i) {
            resArray[i] = array1[i] + array2[i];
        }
        return resArray;
    }

    /**
     * функция вычитания из массива 1 массива 2 почленно
     *
     * @param array1 массив 1
     * @param array2 массив 2
     * @return массив, резулттат вычитания
     */
    private static double[] diffArray(double[] array1, double[] array2) {
        double[] resArray = new double[array1.length];
        for (int i = 0; i < array1.length; ++i) {
            resArray[i] = array1[i] - array2[i];
        }
        return resArray;
    }

    /**
     * функция вычисления суммы произведения компанентов массива
     *
     * @param array1 массив 1
     * @param array2 массив 2
     * @return double, результат выполнения
     */
    private static double multiplicationVectors(double[] array1, double[] array2) {
        double result = 0;
        for (int i = 0; i < array1.length; ++i) {
            result += array1[i] * array2[i];
        }
        return result;
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
        //   return Arrays.equals(this.vector, v.vector);  //
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
