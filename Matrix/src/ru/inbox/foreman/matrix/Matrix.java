package ru.inbox.foreman.matrix;


import ru.inbox.foreman.vector.Vector;

/**
 * Класс матрицы из векторов
 *
 * @author SergeyNF
 * @since 14.07.2018
 */
public class Matrix {
    private Vector[] rows;

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
        this.rows = new Vector[h];
        for (int i = 0; i < h; ++i) {
            this.rows[i] = new Vector(w);
        }
    }

    /**
     * Конструктор копирования
     *
     * @param matrix Матрица которую копируем
     */
    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];
        for (int i = 0; i < matrix.rows.length; ++i) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    /**
     * Конструктор из двумерного массива
     *
     * @param matrix двумерный массив
     */
    public Matrix(double[][] matrix) {
        if (matrix.length < 1 || matrix[0].length < 1) {
            throw new IllegalArgumentException("Размерность матрицы не можеты быть меньше либо равной 0");
        }
        int maxLength = matrix[0].length;
        for (double[] tmp : matrix) {
            maxLength = Math.max(maxLength, tmp.length);
        }

        this.rows = new Vector[matrix.length];
        for (int i = 0; i < matrix.length; ++i) {
            this.rows[i] = new Vector(maxLength, matrix[i]);
        }
    }

    /**
     * Конструктор из массива векторов-строк
     *
     * @param vectors массив векторов
     */
    public Matrix(Vector[] vectors) {
        if (vectors.length < 1) {
            throw new IllegalArgumentException("Размерность матрицы не можеты быть меньше либо равной 0");
        }
        int maxLengthVectors = vectors[0].getSize();
        for (Vector tmpVector : vectors) {
            maxLengthVectors = Math.max(tmpVector.getSize(), maxLengthVectors);
        }
        this.rows = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(maxLengthVectors, vectors[i].toArray());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < rows.length; ++i) {
            if (i < rows.length - 1) {
                sb.append(rows[i].toString()).append(",").append(System.lineSeparator());
            } else {
                sb.append(rows[i].toString()).append("}");
            }
        }
        return sb.toString();
    }

    /**
     * Получение высоты матрицы
     *
     * @return высота матрицы
     */
    public int getColumn() {
        return this.rows.length;
    }

    /**
     * Получение ширины матрицы
     *
     * @return ширина матрицы
     */
    public int getRows() {
        return this.rows[0].getSize();
    }

    /**
     * Получение вектора-строки по индексу сторки
     *
     * @param indexRow индекс стороки
     * @return Вектор
     */
    public Vector getVectorInRow(int indexRow) {
        if (indexRow >= this.rows.length || indexRow < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.rows.length);
        }
        return new Vector(rows[indexRow]);
    }

    /**
     * Задание вектора-строки по индексу сторки
     *
     * @param indexRow индекс стороки
     * @param vector   Вектор
     */
    public void setVectorInRow(int indexRow, Vector vector) {
        if (indexRow >= this.rows.length || indexRow < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.rows.length);
        }
        int vectorSizeInRow = rows[indexRow].getSize();
        if (vectorSizeInRow != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора не соответствует вектору в строке ");
        }
        for (int i = 0; i < vectorSizeInRow; ++i) {
            this.rows[indexRow].setVectorComponent(i, vector.getVectorComponent(i));
        }
    }


    /**
     * Получение вектора-строки по индексу колонки
     *
     * @param indexColumn индекс колонки
     * @return Вектор
     */
    public Vector getVectorInColumn(int indexColumn) {
        if (indexColumn >= this.getRows() || indexColumn < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.rows[0].getSize());
        }
        double[] resArray = new double[this.rows.length];
        for (int i = 0; i < resArray.length; ++i) {
            resArray[i] = this.rows[i].getVectorComponent(indexColumn);
        }
        return new Vector(resArray.length, resArray);
    }

    /**
     * Транспонирование матрицы
     */
    public void transposition() {
        // Если матрица квадрат, не пересоздавая вектора заменяем компоненты
        if (this.rows.length == this.rows[0].getSize()) {
            for (int y = 0; y < this.rows.length; ++y) {
                for (int x = y + 1; x < this.rows.length; ++x) {
                    double tmp = this.rows[y].getVectorComponent(x);
                    this.rows[y].setVectorComponent(x, this.rows[x].getVectorComponent(y));
                    this.rows[x].setVectorComponent(y, tmp);
                }
            }
        } else {    // Если не квадрат, вектора заменяются на новые
            Vector[] tmpMatrix = new Vector[this.rows[0].getSize()];
            for (int i = 0; i < tmpMatrix.length; ++i) {
                tmpMatrix[i] = new Vector(this.getVectorInColumn(i));
            }
            this.rows = tmpMatrix;
        }
    }

    /**
     * Умножение матрицы на скаляр
     *
     * @param scalar скаляр на который умножаем
     */
    public void multiplicationMatrixOnScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplicationOnScalar(scalar);
        }
    }

    /**
     * Прибавление к матрице другой матрицы
     *
     * @param matrix прибавляемая матрица
     */
    public void additionMatrix(Matrix matrix) {
        if (this.rows.length != matrix.rows.length || this.getVectorInRow(0).getSize() != matrix.getVectorInRow(0).getSize()) {
            throw new IllegalArgumentException("Матрицы разных размерностей");
        }
        for (int i = 0; i < matrix.rows.length; ++i) {
            this.rows[i].addition(matrix.rows[i]);
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
    public Vector multiplicationMatrixOnVector(Vector vector) {

        if (this.rows[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException(String.format("Вектор должен быть длинной %d", this.rows[0].getSize()));
        }

        Vector resVector = new Vector(this.rows.length);
        for (int i = 0; i < this.rows.length; ++i) {
            double tmp = 0;
            for (int j = 0; j < this.rows[0].getSize(); ++j) {
                tmp += this.rows[i].getVectorComponent(j) * vector.getVectorComponent(j);
            }
            resVector.setVectorComponent(i, tmp);
        }
        return resVector;
    }

    /**
     * геттер опрделителя матрицы
     *
     * @return определитель матрицы
     */
    public double getDetermination() {
        if (this.rows.length != this.rows[0].getSize()) {
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
        int height = m.rows.length;
        // Для матрицы 1 на 1 сразу решение. Проверяем первому уровню
        if (height == 1) {
            return m.rows[0].getVectorComponent(0);
        }
        // Для матрицы 2 на 2 сразу решение. Проверяем только по одной стороне
        if (height == 2) {
            return m.rows[0].getVectorComponent(0) * m.rows[1].getVectorComponent(1) - m.rows[1].getVectorComponent(0) * m.rows[0].getVectorComponent(1);
        }
        // определяем вектор с наибольшим количеством нолей
        int vectorWithMaxNull = searchVectorWithMaxNull(m);

        double determinant = 0.0;
        double epsilon = 10e-10;
        // пробегаем по матрице на выбранной строке и суммируем результат произведения (-1) в степени n+2 элемена в этой строке и разложенной относительно него матрицы
        for (int i = 0; i < m.rows.length; i++) {
            double matrixComponent = m.rows[vectorWithMaxNull].getVectorComponent(i);
            determinant += Math.abs(matrixComponent) <= epsilon ? 0 : matrixComponent * Math.pow(-1.0, i + 2) * calcDeterminant(decompositionMatrix(m, vectorWithMaxNull, i));
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

        for (int i = 0; i < matrix.rows.length; i++) {
            int countNull = 0;

            for (int j = 0; j < matrix.rows[0].getSize(); j++) {
                if (Math.abs(matrix.rows[i].getVectorComponent(j)) <= epsilon) {
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
        double[][] decompositionMatrix = new double[matrix.rows.length - 1][matrix.rows[0].getSize() - 1];

        for (int i = 0, m = 0; i < matrix.rows.length; i++) {
            if (i == vector) {
                continue;
            }
            for (int j = 0, n = 0; j < matrix.rows[0].getSize(); j++) {
                if (j != vectorComponent) {
                    decompositionMatrix[m][n] = matrix.rows[i].getVectorComponent(j);
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
     * Статический метод, вычитания из матрицы м1 матрицу м2
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
        if (m1.rows[0].getSize() != m2.rows.length) {
            throw new IllegalArgumentException("высота матрицы 2 должна быть равна ширине матрицы 1");
        }
        Matrix result = new Matrix(m1.rows.length, m2.rows[0].getSize());
        for (int i = 0; i < result.rows.length; ++i) {
            for (int j = 0; j < result.rows[0].getSize(); ++j) {
                result.rows[i].setVectorComponent(j, Vector.multiplicationVectors(m1.getVectorInRow(i), m2.getVectorInColumn(j)));
            }
        }
        return result;
    }
}

