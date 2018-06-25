package ru.inbox.foreman.range;

/**
 * Класс Range. Диапазон определён от меньшего числа к большему
 *
 * @author SergeyNF
 * @since 20.06.2018
 */
public class Range {

    private double from;
    private double to;

    /**
     * Конструктор с параметрами
     *
     * @param from начало диапазона
     * @param to   конец диапазона
     */
    public Range(double from, double to) {
        this.from = Math.min(from, to);
        this.to = Math.max(from, to);
    }

    /**
     * Объединение диапазонов. this(A) + range(B)
     *
     * @param range второй диапазон
     * @return Массив  диапазонов. Результат может состоять из однго или дву диапазонов
     */
    public Range[] sumOfRanges(Range range) {

        // нет общего диапазона
        if (getIntersectRanges(range) == null) {
            return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
        } else {
            return new Range[]{new Range(Math.min(this.from, range.from), Math.max(this.to, range.to))};
        }
    }

    /**
     * Вычитание диапазонов. this(A) - range(B)
     *
     * @param range второй диапазон
     * @return Массив диапазонов. Результат может состоять из одного или двух диапазонов либо массив может быть пустым.
     */
    public Range[] subtractOfRange(Range range) {
        if (getIntersectRanges(range) != null) {
            //из меньшего больший либо равные
            if (this.from >= range.from && this.to <= range.to) {
                return new Range[0];
            }
            //диапазон внутри
            if (this.from < range.from && this.to > range.to) {
                return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
            }
            //пересечение слева
            if (this.from < range.to && this.to > range.from) {
                return new Range[]{new Range(this.from, range.from)};
            }
            //пересечение справа
            if (this.to > range.from && this.from < range.to) {
                return new Range[]{new Range(range.to, this.to)};
            }
        }
        return new Range[0];
    }

    public double getFrom() {
        return this.from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return this.to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public String rangeToString() {
        return "(" + this.from + ", " + this.to + ")";
    }

    /**
     * Длина диапазона
     *
     * @return вещественное число.
     */
    public double calcLength() {
        return this.to - this.from;
    }

    /**
     * Проверка вхождения числа в диапазон
     *
     * @param number проверяемое число
     * @return boolean  результат проверки. true - входит
     */
    public boolean isInside(double number) {
        return number >= this.from && number <= this.to;
    }

    /**
     * Возврат результата пересечения диапазонов.
     *
     * @param range Диапазон, проверяемый на пересечение с данным
     * @return При отсутствии возвращает null, создавая пустую ссылку. В противном случае, диапазон - общий для исходных
     */
    public Range getIntersectRanges(Range range) {
        if (this.from < range.to && this.to > range.from) {
            return new Range(Math.max(this.from, range.from), Math.min(this.to, range.to));
        }
        return null;
    }
}