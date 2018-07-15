package ru.inbox.foreman.matrix;


import ru.inbox.foreman.vector.Vector;

/**
 * Класс матрицы из векторов
 *
 * @author SergeyNF
 * @since 14.07.2018
 */
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
            if (i < matrix.length - 1) {
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
        return new Vector(matrix[indexRow]);
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
        // если передаваемый вектор меньше данного
        if (matrix[indexRow].getSize() > vector.getSize()) {
            for (int i = 0; i < vector.getSize(); ++i) {
                matrix[indexRow].setVectorComponent(i, vector.getVectorComponent(i));
            } // усли передаваемый больше данного
        } else if (matrix[indexRow].getSize() < vector.getSize()) {
            Vector tmp = new Vector(vector.getSize());
            for (int i = 0; i < matrix.length; ++i) {
                if (i == indexRow) {
                    matrix[i] = new Vector(vector);
                }
                matrix[i].addition(tmp);
            }
        } else { // если равны
            this.matrix[indexRow] = new Vector(vector);
        }
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
        // Если матрица квадрат, не пересоздавая вектора заменяем компоненты
        if (this.matrix.length == this.matrix[0].getSize()) {
            double tmp;
            for (int y = 0; y < this.matrix.length; ++y) {
                for (int x = y + 1; x < this.matrix.length; ++x) {
                    tmp = this.matrix[y].getVectorComponent(x);
                    this.matrix[y].setVectorComponent(x, this.matrix[x].getVectorComponent(y));
                    this.matrix[x].setVectorComponent(y, tmp);
                }
            }
        } else {    // Если не квадрат, вектора заменяются на новые
            Vector[] tmpMatrix = new Vector[this.matrix[0].getSize()];
            for (int i = 0; i < tmpMatrix.length; ++i) {
                tmpMatrix[i] = new Vector(this.getVectorInColumn(i));
            }
            this.matrix = tmpMatrix;
        }
    }

    /**
     * Умножение матрицы на скаляр
     *
     * @param scalar скаляр на который умножаем
     */
    public void multiplicationMatrixOnScalar(double scalar) {
        for (Vector vector : matrix) {
            vector.multiplicationOnScalar(scalar);
        }
    }

    /**
     * Прибавление к матрице другой матрицы
     *
     * @param matrix прибавляемая матрица
     */
    public void additionMatrix(Matrix matrix) {
        if (this.matrix.length < matrix.matrix.length) {
            Vector[] tmp = this.matrix;
            this.matrix = new Vector[matrix.matrix.length];
            for (int i = 0; i < this.matrix.length; ++i) {
                if (i >= tmp.length) {
                    this.matrix[i] = new Vector(tmp[0].getSize());
                } else {
                    this.matrix[i] = new Vector(tmp[i]);
                }
            }
        }
        for (int i = 0; i < matrix.matrix.length; ++i) {
            this.matrix[i].addition(matrix.matrix[i]);
        }
    }

    /**
     * Вычитание из матрицы другой матрицы
     *
     * @param matrix вычитаемая матрица
     */
    public void subtractionMatrix(Matrix matrix) {
        Matrix tmp = new Matrix(matrix);
        tmp.multiplicationMatrixOnScalar(-1.0);
        this.additionMatrix(tmp);
    }

    /**
     * Умножение матрицы на вектор по правилу «строка на столбец» через статический метод
     *
     * @param vector строка вектор, на которую умножается матрица
     */
    public void multiplicationMatrixOnVector(Vector vector) {

//        if (this.matrix[0].getSize() != vector.getSize()) {
//            throw new IllegalArgumentException(String.format("Вектор должен быть длинной %d", this.matrix[0].getSize()));
//        }
//
//        Vector resVector = new Vector(this.matrix.length);
//        for (int i = 0; i < this.matrix.length; ++i) {
//            double tmp = 0;
//            for (int j = 0; j < this.matrix[0].getSize(); ++j) {
//                tmp += this.matrix[i].getVectorComponent(j) * vector.getVectorComponent(j);
//            }
//            resVector.setVectorComponent(i, tmp);
//        }
//        this.matrix = new Vector[1];
//        this.matrix[0] = resVector;

        Vector[] tmp = new Vector[vector.getSize()];
        for (int i = 0; i < vector.getSize(); ++i) {
            tmp[i] = new Vector(1);
            tmp[i].setVectorComponent(0, vector.getVectorComponent(i));
        }
        this.matrix = Matrix.multiplication(this, new Matrix(tmp)).matrix;
    }

    /**
     * геттер опрделителя матрицы
     *
     * @return определитель матрицы
     */
    public double getDetermination() {
        if (this.matrix.length != this.matrix[0].getSize()) {
            throw new IllegalArgumentException("Матрица должна быть размерностью NхN");
        }
        return calcDeterminant(this);
    }

    /**
     * функция расчета определителя матрицы размерностью n*n
     *
     * @return определитель матрицы. Тип возвращаемого значения double.
     */
    private double calcDeterminant(Matrix m) {
        //  число векторов
        int height = m.matrix.length;
        // Для матрицы 1 на 1 сразу решение. Проверяем первому уровню
        if (height == 1) {
            return m.matrix[0].getVectorComponent(0);
        }
        // Для матрицы 2 на 2 сразу решение. Проверяем только по одной стороне
        if (height == 2) {
            return m.matrix[0].getVectorComponent(0) * m.matrix[1].getVectorComponent(1) - m.matrix[1].getVectorComponent(0) * m.matrix[0].getVectorComponent(1);
        }
        // определяем вектор с наибольшим количеством нолей
        int vectorWithMaxNull = searchVectorWithMaxNull(m);

        double determinant = 0.0;
        double epsilon = 10e-10;
        // пробегаем по матрице на выбранной строке и суммируем результат произведения (-1) в степени n+2 элемена в этой строке и разложенной относительно него матрицы
        for (int i = 0; i < m.matrix.length; i++) {
            determinant += m.matrix[vectorWithMaxNull].getVectorComponent(i) <= epsilon ? 0 : m.matrix[vectorWithMaxNull].getVectorComponent(i) * Math.pow(-1.0, i + 2) * calcDeterminant(decompositionMatrix(m, vectorWithMaxNull, i));
        }
        return determinant;
    }

    /**
     * функция ищет строку вектор в матрице с максимальным количеством нолей
     *
     * @param matrix матрица в которой нежно искать
     * @return номер строки вектора с максимальным количеством нолей. Тип возвращаемого значения int. По умолчанию строка 0.
     */
    private int searchVectorWithMaxNull(Matrix matrix) {
        int vectorNumber = 0;
        int maxNull = 0;
        double epsilon = 10e-10;

        for (int i = 0; i < matrix.matrix.length; i++) {
            int countNull = 0;

            for (int j = 0; j < matrix.matrix[0].getSize(); j++) {
                if (Math.abs(matrix.matrix[i].getVectorComponent(j)) <= epsilon) {
                    countNull++;
                }
            }
            if (countNull > maxNull) {
                maxNull = countNull;
                vectorNumber = i;
            }
        }
        return vectorNumber;
    }

    /**
     * функция разложения переданной матрицы
     *
     * @param matrix          переданная матрица
     * @param vector          номер вектора с элементом относительно которого нужно разложить
     * @param vectorComponent элемент вектора относительно которого нужно разложить
     * @return матрицу размерностью на 1 меньше. Тип возвращаемого значения double.
     */
    private Matrix decompositionMatrix(Matrix matrix, int vector, int vectorComponent) {
        double[][] decompositionMatrix = new double[matrix.matrix.length - 1][matrix.matrix[0].getSize() - 1];

        for (int i = 0, m = 0; i < matrix.matrix.length; i++) {
            if (i == vector) {
                continue;
            }
            for (int j = 0, n = 0; j < matrix.matrix[0].getSize(); j++) {
                if (j != vectorComponent) {
                    decompositionMatrix[m][n] = matrix.matrix[i].getVectorComponent(j);
                    n++;
                }
            }
            m++;
        }
        return new Matrix(decompositionMatrix);
    }

    /**
     * Статический метод, разность матриц
     *
     * @param m1 матрица 1
     * @param m2 матрица 2
     * @return сумма матриц
     */
    public static Matrix sumMatrix(Matrix m1, Matrix m2) {
        Matrix tmp = new Matrix(m1);
        tmp.additionMatrix(m2);
        return tmp;
    }

    /**
     * Статический метод, вычитания из матрицы м2 матрицы м2
     *
     * @param m1 матрица 1
     * @param m2 матрица 1
     * @return разность матриц 1 и 2
     */
    public static Matrix diffMatrix(Matrix m1, Matrix m2) {
        Matrix tmp = new Matrix(m1);
        tmp.subtractionMatrix(m2);
        return tmp;
    }

    /**
     * Произведение матриц
     *
     * @param m1 м1
     * @param m2 м2
     * @return результат произведения, матрица
     */
    public static Matrix multiplication(Matrix m1, Matrix m2) {
        Matrix result = new Matrix(m1.matrix.length, m2.matrix[0].getSize());
        for (int i = 0; i < result.matrix.length; ++i) {
            for (int j = 0; j < result.matrix[0].getSize(); ++j) {
                result.matrix[i].setVectorComponent(j, Vector.multiplicationVectors(m1.getVectorInRow(i), m2.getVectorInColumn(j)));
            }
        }
        return result;
    }
}

