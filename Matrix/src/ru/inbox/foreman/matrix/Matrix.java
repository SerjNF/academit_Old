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
     * @param h      Число сторк матрицы
     * @param column Число параметоров в строке
     */
    public Matrix(int h, int column) {
        if (h <= 0 || column <= 0) {
            throw new IllegalArgumentException("Размерность матрицы не можеты быть меньше либо равной 0");
        }
        this.rows = new Vector[h];
        for (int i = 0; i < h; ++i) {
            this.rows[i] = new Vector(column);
        }
    }

    /**
     * Конструктор копирования
     *
     * @param m Матрица которую копируем
     */
    public Matrix(Matrix m) {
        this.rows = new Vector[m.rows.length];
        for (int i = 0; i < m.rows.length; ++i) {
            this.rows[i] = new Vector(m.rows[i]);
        }
    }

    /**
     * Конструктор из двумерного массива
     *
     * @param m двумерный массив
     */
    public Matrix(double[][] m) {
        if (m.length < 1 || m[0].length < 1) {
            throw new IllegalArgumentException("Размерность матрицы не можеты быть меньше либо равной 0");
        }
        int maxLength = m[0].length;
        for (double[] tmp : m) {
            maxLength = Math.max(maxLength, tmp.length);
        }

        this.rows = new Vector[m.length];
        for (int i = 0; i < m.length; ++i) {
            this.rows[i] = new Vector(maxLength, m[i]);
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
    public int getRowsCount() {
        return this.rows.length;
    }

    /**
     * Получение ширины матрицы
     *
     * @return ширина матрицы
     */
    public int getColumnsCount() {
        return this.rows[0].getSize();
    }

    /**
     * Получение вектора-строки по индексу сторки
     *
     * @param indexRow индекс стороки
     * @return Вектор
     */
    public Vector getVectorInRow(int indexRow) {
        if (indexRow >= this.getRowsCount() || indexRow < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.rows.length);
        }
        return new Vector(rows[indexRow]);
    }

    /**
     * Задание вектора-строки по индексу сторки
     *
     * @param indexRow индекс стороки
     * @param v        Вектор
     */
    public void setVectorInRow(int indexRow, Vector v) {
        if (indexRow >= this.getRowsCount() || indexRow < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.getRowsCount());
        }
        int vectorSizeInRow = rows[indexRow].getSize();
        if (vectorSizeInRow != v.getSize()) {
            throw new IllegalArgumentException("Размер вектора не соответствует вектору в строке ");
        }
        for (int i = 0; i < vectorSizeInRow; ++i) {
            this.rows[indexRow].setVectorComponent(i, v.getVectorComponent(i));
        }
    }

    /**
     * Получение вектора-строки по индексу колонки
     *
     * @param indexColumn индекс колонки
     * @return Вектор
     */
    public Vector getVectorInColumn(int indexColumn) {
        if (indexColumn >= this.getColumnsCount() || indexColumn < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах от 0 до " + this.getColumnsCount());
        }
        double[] resArray = new double[this.getRowsCount()];
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
        if (this.getRowsCount() == this.getColumnsCount()) {
            for (int y = 0; y < this.getRowsCount(); ++y) {
                for (int x = y + 1; x < this.getRowsCount(); ++x) {
                    double tmp = this.rows[y].getVectorComponent(x);
                    this.rows[y].setVectorComponent(x, this.rows[x].getVectorComponent(y));
                    this.rows[x].setVectorComponent(y, tmp);
                }
            }
        } else {    // Если не квадрат, вектора заменяются на новые
            Vector[] tmpMatrix = new Vector[this.getColumnsCount()];
            for (int i = 0; i < tmpMatrix.length; ++i) {
                tmpMatrix[i] = this.getVectorInColumn(i);
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
     * @param m прибавляемая матрица
     */
    public void additionMatrix(Matrix m) {
        verifyMatrix(this, m);
        sumRowMatrix(m);
    }

    /**
     * Сложение строк матриц
     *
     * @param m прибавляемая матрица
     */
    private void sumRowMatrix(Matrix m) {
        for (int i = 0; i < m.getRowsCount(); ++i) {
            this.rows[i].addition(m.rows[i]);
        }
    }

    /**
     * Вычитание строк матриц
     *
     * @param m вычитаемая матрица
     */
    private void diffRowMatrix(Matrix m) {
        for (int i = 0; i < m.getRowsCount(); ++i) {
            this.rows[i].subtraction(m.rows[i]);
        }
    }

    /**
     * проверка матриц
     *
     * @param m1 проверяемая матрица 1
     * @param m2 проверяемая матрица 2
     */
    private static void verifyMatrix(Matrix m1, Matrix m2) {
        if (m1.getColumnsCount() != m2.getColumnsCount() || m1.getRowsCount() != m2.getRowsCount()) {
            throw new IllegalArgumentException("Матрицы разных размерностей");
        }
    }

    /**
     * Вычитание из матрицы другой матрицы
     *
     * @param m вычитаемая матрица
     */
    public void subtractionMatrix(Matrix m) {
        verifyMatrix(this, m);
        this.diffRowMatrix(m);
    }

    /**
     * Умножение матрицы на вектор по правилу «строка на столбец» через статический метод
     *
     * @param v строка вектор, на которую умножается матрица
     */
    public Vector multiplicationMatrixOnVector(Vector v) {

        if (this.getColumnsCount() != v.getSize()) {
            throw new IllegalArgumentException(String.format("Вектор должен быть длинной %d", this.getColumnsCount()));
        }

        Vector resVector = new Vector(this.getRowsCount());
        for (int i = 0; i < this.getRowsCount(); ++i) {
            double tmp = 0;
            for (int j = 0; j < this.getColumnsCount(); ++j) {
                tmp += this.rows[i].getVectorComponent(j) * v.getVectorComponent(j);
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
    public double getDeterminant() {
        if (this.getRowsCount() != this.getColumnsCount()) {
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
        int height = m.getRowsCount();
        // Для матрицы 1 на 1 сразу решение. Проверяем первому уровню
        if (height == 1) {
            return m.rows[0].getVectorComponent(0);
        }
        // Для матрицы 2 на 2 сразу решение. Проверяем только по одной стороне
        if (height == 2) {
            return m.rows[0].getVectorComponent(0) * m.rows[1].getVectorComponent(1) - m.rows[1].getVectorComponent(0) * m.rows[0].getVectorComponent(1);
        }
        // определяем вектор с наибольшим количеством нолей
        int rowWithMaxNull = searchRowWithMaxNull(m);

        double determinant = 0.0;
        double epsilon = 10e-10;
        // пробегаем по матрице на выбранной строке и суммируем результат произведения (-1) в степени (№ элемента + № строки) + элемена в этой строке и разложенной относительно него матрицы
        for (int i = 0; i < m.getColumnsCount(); i++) {
            double matrixComponent = m.rows[rowWithMaxNull].getVectorComponent(i);
            determinant += Math.abs(matrixComponent) <= epsilon ? 0 : matrixComponent * Math.pow(-1.0, i + rowWithMaxNull) * calcDeterminant(decomposeMatrix(m, rowWithMaxNull, i));
        }
        return determinant;
    }

    /**
     * функция ищет строку вектор в матрице с максимальным количеством нолей
     *
     * @param m матрица в которой нежно искать
     * @return номер строки вектора с максимальным количеством нолей. Тип возвращаемого значения int. По умолчанию строка 0.
     */
    private int searchRowWithMaxNull(Matrix m) {
        int rowNumber = 0;
        int maxNull = 0;
        double epsilon = 10e-10;

        for (int i = 0; i < m.getRowsCount(); i++) {
            int countNull = 0;

            for (int j = 0; j < m.getColumnsCount(); j++) {
                if (Math.abs(m.rows[i].getVectorComponent(j)) <= epsilon) {
                    countNull++;
                }
            }
            if (countNull > maxNull) {
                maxNull = countNull;
                rowNumber = i;
            }
        }
        return rowNumber;
    }

    /**
     * функция разложения переданной матрицы
     *
     * @param m      переданная матрица
     * @param row    номер строки с элементом относительно которого нужно разложить
     * @param column номер колонки с элементом вектора, относительно которого нужно разложить
     * @return матрицу размерностью на 1 меньше. Тип возвращаемого значения Matrix.
     */
    private Matrix decomposeMatrix(Matrix m, int row, int column) {
        double[][] decompositionMatrix = new double[m.getRowsCount() - 1][m.getColumnsCount() - 1];

        for (int i = 0, k = 0; i < m.getRowsCount(); i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0, n = 0; j < m.getColumnsCount(); j++) {
                if (j != column) {
                    decompositionMatrix[k][n] = m.rows[i].getVectorComponent(j);
                    n++;
                }
            }
            k++;
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
        verifyMatrix(m1, m2);
        Matrix tmp = new Matrix(m1);
        tmp.sumRowMatrix(m2);
        return tmp;
    }

    /**
     * Статический метод, вычитания из матрицы м1 матрицу м2
     *
     * @param m1 матрица 1
     * @param m2 матрица 1
     * @return вычитание из матрицы 1 матрицы 2
     */
    public static Matrix diffMatrix(Matrix m1, Matrix m2) {
        verifyMatrix(m1, m2);
        Matrix tmp = new Matrix(m1);
        tmp.diffRowMatrix(m2);
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
        if (m1.getColumnsCount() != m2.getRowsCount()) {
            throw new IllegalArgumentException("несоответствие размеров матриц");
        }
        Matrix result = new Matrix(m1.getRowsCount(), m2.getColumnsCount());

        for (int i = 0; i < result.getRowsCount(); ++i) {
            for (int j = 0; j < result.getColumnsCount(); ++j) {
                double tmp = 0;
                for (int k = 0; k < m2.getRowsCount(); ++k) {
                    tmp += m1.rows[i].getVectorComponent(k) * m2.rows[k].getVectorComponent(j);
                }
                result.rows[i].setVectorComponent(j, tmp);
            }
        }
        return result;
    }
}

