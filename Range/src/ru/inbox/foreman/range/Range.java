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

    public String toString() {
        return String.format("{%.2f, %.2f}", this.from, this.to);
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

    /**
     * Объединение диапазонов. this + range
     *
     * @param range второй диапазон
     * @return Массив  диапазонов. Результат может состоять из однго или дву диапазонов
     */
    public Range[] sumOfRanges(Range range) {

        // нет общего диапазона
        if (this.from > range.to || this.to < range.from) {
            return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
        } else {
            return new Range[]{new Range(Math.min(this.from, range.from), Math.max(this.to, range.to))};
        }
    }

    /**
     * Вычитание диапазонов. this - range
     *
     * @param range второй диапазон
     * @return Массив диапазонов. Результат может состоять из одного или двух диапазонов либо массив может быть пустым.
     */
    public Range[] subtractOfRange(Range range) {
        //нет пересечения
        if (this.from >= range.to || this.to <= range.from) {
            return new Range[]{new Range(this.from, this.to)};
        }
        //диапазон внутри
        if (this.from < range.from && this.to > range.to) {
            return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
        }
        //пересечение справа
        if (this.from < range.from && range.from < this.to) {
            return new Range[]{new Range(this.from, range.from)};
        }
        //пересечение слева
        if (this.to > range.to && this.from < range.to) {
            return new Range[]{new Range(range.to, this.to)};
        }
        return new Range[0];
    }
}