package ru.inbox.foreman.vector;

import java.util.Arrays;

/**
 * Класс вектора размерности n
 *
 * @author SergeyNF
 * @since 28.06.2018
 */
public class Vector {

    private double[] vector;


    public Vector(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("");
        } else {
            this.vector = new double[n];
        }
    }

    public Vector(Vector vector) throws NullPointerException {
        if (vector == null) {
            throw new NullPointerException();
        } else {
            this.vector = Arrays.copyOf(vector.vector, vector.vector.length);
        }
    }

    private Vector(double[] vector) {
        this.vector = Arrays.copyOf(vector, vector.length);
    }

    public Vector(int n, double[] vector) throws IllegalArgumentException {
        if (n >= vector.length) {
            this.vector = new double[n];
        } else {
            throw new IllegalArgumentException();
        }
        System.arraycopy(vector, 0, this.vector, 0, vector.length);
    }

    public int getSize() {
        return vector.length;
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

    /**
     * Прибавление к вектору другого вектора
     *
     * @param vector прибавляемый вектор
     * @return новый объект, результирующий вектор
     */
    public Vector addition(Vector vector) {
        if (vector == null) {
            return null;
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
     * вычитание из вектора другого вуктора
     *
     * @param vector вычитаемый вектор
     * @return новый объект, результирующий вектор
     */
    public Vector subtraction(Vector vector) {
        if (vector == null) {
            return null;
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

    public double getVectorLength() {
        double vectorLength = 0;
        for (double v : this.vector) {
            vectorLength = vectorLength + v * v;
        }
        return Math.sqrt(vectorLength);
    }

    /**
     * Функция сложения 2х массивов почленно
     *
     * @param array1 массив 1
     * @param array2 массив 2
     * @return массив, результат сложения
     */
    private double[] sumArray(double[] array1, double[] array2) {
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
        if (this.vector.length != v.vector.length){
            return false;
       }
        return Arrays.equals(this.vector, v.vector);
    }
}
