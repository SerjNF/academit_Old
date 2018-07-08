package ru.inbox.foreman.matrix;


import ru.inbox.foreman.vector.Vector;

public class Matrix {
    private Vector[] matrix;

    /**
     * Матрица нулей размера h на w
     *
     * @param h Число сторк матрицы
     * @param w Число параметоров в строке
     */
    public Matrix(int h, int w) {
        if (h <= 0 || w <= 0) {
            throw new IllegalArgumentException("Размерность матрицы не можеты быть меньше либо равной 0");
        }
        this.matrix = new Vector[h];
        for (int i = 0; i < h; ++i) {
            this.matrix[i] = new Vector(w);
        }
    }

    /**
     * Конструктор копирования
     *
     * @param matrix Матрица которую копируем
     */
    public Matrix(Matrix matrix) {
        this.matrix = new Vector[matrix.matrix.length];
        for (int i = 0; i < matrix.matrix.length; ++i) {
            this.matrix[i] = new Vector(matrix.matrix[i]);
        }
    }

    /**
     * Конструктор из двумерного массива
     *
     * @param matrix двумерный массив
     */
    public Matrix(double[][] matrix) {
        int maxLength = matrix[0].length;
        for (double[] tmp : matrix) {
            maxLength = Math.max(maxLength, tmp.length);
        }

        this.matrix = new Vector[matrix.length];
        for (int i = 0; i < matrix.length; ++i) {
            this.matrix[i] = new Vector(maxLength, matrix[i]);
        }
    }

    /**
     * Конструктор из массива векторов-строк
     *
     * @param vectors массив векторов
     */
    public Matrix(Vector[] vectors) {
        this.matrix = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; ++i) {
            matrix[i] = new Vector(vectors[i]);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < matrix.length; ++i) {
            if (i < matrix.length) {
                sb.append(String.format("%s, %n", matrix[i].toString()));
            } else {
                sb.append(String.format("%s}", matrix[i].toString()));
            }
        }
        return sb.toString();
    }

    /**
     * Получение размеров матрицы
     *
     * @return Массив двух int: высота и ширина матрицы
     */
    public int[] getSize() {
        return new int[]{this.matrix.length, this.matrix[0].getSize()};
    }

    /**
     * Получение вектора-строки по индексу сторки
     *
     * @param indexRow индекс стороки
     * @return Вектор
     */
    public Vector getVectorInRow(int indexRow) {
        if (indexRow >= this.matrix.length || indexRow < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.matrix.length);
        }
        return this.matrix[indexRow];
    }

    /**
     * Задание вектора-строки по индексу сторки
     *
     * @param indexRow индекс стороки
     * @param vector   Вектор
     */
    public void setVectorInRow(int indexRow, Vector vector) {
        if (indexRow >= this.matrix.length || indexRow < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.matrix.length);
        }
        this.matrix[indexRow] = new Vector(vector);
    }

    /**
     * Получение вектора-строки по индексу колонки
     *
     * @param indexColumn индекс колонки
     * @return Вектор
     */
    public Vector getVectorInColumn(int indexColumn) {
        if (indexColumn >= this.matrix[0].getSize() || indexColumn < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.matrix[0].getSize());
        }
        double[] resArray = new double[this.matrix.length];
        for (int i = 0; i < resArray.length; ++i) {
            resArray[i] = this.matrix[i].getVectorComponent(indexColumn);
        }
        return new Vector(resArray.length, resArray);
    }

    /**
     * Транспонирование матрицы
     */
    public void transposition() {
        if (this.matrix.length == this.matrix[0].getSize()) {
            double tmp;
            for (int y = 0; y < this.matrix.length; ++y) {
                for (int x = y + 1; x < this.matrix.length; ++x) {
                    tmp = this.matrix[y].getVectorComponent(x);
                    this.matrix[y].setVectorComponent(x, this.matrix[x].getVectorComponent(y));
                    this.matrix[x].setVectorComponent(y, tmp);
                }
            }
        } else {
            Vector [] tmp = new Vector[matrix[0].getSize()];
        }
    }
}
