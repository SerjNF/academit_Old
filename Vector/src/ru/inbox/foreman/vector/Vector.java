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
    public Vector(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("");
        } else {
            this.vector = new double[n];
        }
    }

    /**
     * Конструктор. копирование вектора
     *
     * @param vector вектор
     */
    public Vector(Vector vector) {
        if (vector == null) {
            this.vector = null;
        } else {
            this.vector = Arrays.copyOf(vector.vector, vector.vector.length);
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
    public Vector(int n, double[] vector) throws IllegalArgumentException {
        if (n >= vector.length) {
            this.vector = new double[n];
        } else {
            throw new IllegalArgumentException();
        }
        System.arraycopy(vector, 0, this.vector, 0, vector.length);
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
     * @param vector прибавляемый вектор
     * @return новый объект, результирующий вектор
     */
    public Vector addition(Vector vector) {
        if (vector == null) {
            return new Vector(this.vector);
        }
        Vector tempVector;
        if (this.vector.length < vector.vector.length) {
            tempVector = new Vector(vector.vector.length, this.vector);
            return new Vector(sumArray(tempVector.vector, vector.vector));
        }
        if (this.vector.length > vector.vector.length) {
            tempVector = new Vector(this.vector.length, vector.vector);
            return new Vector(sumArray(tempVector.vector, this.vector));
        }
        return new Vector(sumArray(this.vector, vector.vector));
    }

    /**
     * Вычитание из вектора другого вуктора
     *
     * @param vector вычитаемый вектор
     * @return новый объект, результирующий вектор
     */
    public Vector subtraction(Vector vector) {
        if (vector == null) {
            return new Vector(this.vector);
        }
        Vector tempVector;
        if (this.vector.length < vector.vector.length) {
            tempVector = new Vector(vector.vector.length, this.vector);
            return new Vector(diffArray(tempVector.vector, vector.vector));
        }
        if (this.vector.length > vector.vector.length) {
            tempVector = new Vector(this.vector.length, vector.vector);
            return new Vector(diffArray(this.vector, tempVector.vector));
        }
        return new Vector(diffArray(this.vector, vector.vector));
    }

    /**
     * Разварот вектора (умножение на -1)
     *
     * @return новый объект, результирующий вектор
     */
    public Vector reverse() {
        double[] resVector = new double[this.vector.length];
        for (int i = 0; i < this.vector.length; ++i) {
            resVector[i] = this.vector[i] * (-1);
        }
        return new Vector(resVector);
    }

    /**
     * Умножение вектора на скаляр
     *
     * @param scalar double, величина на которую умножается вектор
     * @return Vector, умноженный на скаляр
     */
    public Vector multiplicationOnScalar(double scalar) {
        double[] resVector = new double[this.vector.length];
        for (int i = 0; i < this.vector.length; ++i) {
            resVector[i] = this.vector[i] * scalar;
        }
        return new Vector(resVector);
    }


    /**
     * Возврат значения из массива вектора по индексу
     *
     * @param pos индекс в массиве
     * @return значение компанента по индексу
     * @throws IllegalArgumentException - исключение при значении индекса выходящего за пределами массива
     */
    public double getComponentVector(int pos) throws IllegalArgumentException {
        if (pos > this.vector.length - 1 || pos < 0) {
            throw new IllegalArgumentException();
        } else {
            return this.vector[pos];
        }
    }

    /**
     * Установка значения в массиве вектора по индексу
     *
     * @param pos    индекс в массиве
     * @param number устанавливаемое значение
     * @throws IllegalArgumentException - исключение при значении индекса выходящего за пределами массива
     */
    public void setComponentVector(int pos, double number) throws IllegalArgumentException {
        if (pos > this.vector.length - 1 || pos < 0) {
            throw new IllegalArgumentException();
        } else {
            this.vector[pos] = number;
        }
    }

    /**
     * длина вектора
     *
     * @return double, длина вектора
     */
    public double getVectorLength() {
        double vectorLength = 0;
        for (double v : this.vector) {
            vectorLength = vectorLength + v * v;
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
        return v1.addition(v2);
    }

    /**
     * Статический метод вычитания из вектора v1 вектора v2
     *
     * @param v1 вектор 1 - из которого вычитают
     * @param v2 вектор 2 - вычитаемый
     * @return результирующий вектор, с наибольшей разверностью
     */
    public static Vector diffVectors(Vector v1, Vector v2) {
        return v1.subtraction(v2);
    }

    /**
     * Статик метод скалярного умножения векторов
     *
     * @param v1 вектор 1
     * @param v2 вектор 2
     * @return везультат скалярного умножения векторов
     */
    public static double multiplicationVectors(Vector v1, Vector v2) {
        if (v1 == null || v2 == null) {
            return 0.0;
        }
        Vector tempVector;
        if (v1.vector.length < v2.vector.length) {
            tempVector = new Vector(v2.vector.length, v1.vector);

            return multiplicationVectors(tempVector.vector, v2.vector);
        }
        if (v1.vector.length > v2.vector.length) {
            tempVector = new Vector(v1.vector.length, v2.vector);
            return multiplicationVectors(v1.vector, tempVector.vector);
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
    private double[] diffArray(double[] array1, double[] array2) {
        double[] resArray = new double[array1.length];
        for (int i = 0; i < array1.length; ++i) {
            resArray[i] = array1[i] - array2[i];
        }
        return resArray;
    }

    /**
     * функция вычисления суммы произведения сомпанентов массивов
     *
     * @param array1 массив 1
     * @param array2 массив 2
     * @return double, результат выполнения
     */
    private static double multiplicationVectors(double[] array1, double[] array2) {
        double result = 0;
        for (int i = 0; i < array1.length; ++i) {
            result = result + array1[i] * array2[i];
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
