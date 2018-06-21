package ru.inbox.foreman.ru.inbox.foreman.range;


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
        Math.min(from, to);
        this.to = Math.max(from, to);
    }

    /**
     * Static method
     * Объединение диапазонов. rangeOne + rangeTwo
     *
     * @param range второй диапазон
     * @return ArrayList  диапазонов. Результат может состоять из однго или дву диапазонов
     */
    public Range[] sumOfRanges( Range range) {

        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();
        Range[] resultRange = new Range[2];

        if ((this.from <= rangeFrom && this.to >= rangeFrom) || (this.from <= rangeTo && this.to >= rangeTo)) {
            resultRange[0] = new Range(Math.min(this.from, rangeFrom), Math.max(this.to, rangeTo));
            // нет общего диапазона
        } else {
            resultRange[0] = this;
            resultRange[1] = range;
        }
        return resultRange;
    }

    /**
     * Static method
     * Вычитание диапазонов. this(A) - range(B)
     *
     *
     * @param range    второй диапазон
     * @return Массив диапазонов. Результат может состоять из однго или дву диапазонов. Если вычитаем равные диапазоны или из меньшего больший, возвращает null
     */
    public Range[] subtractOfRange(Range range) {

        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        // Если вычитаем равные диапазоны или из меньшего больший, возвращает ноль
        if (this.from >= rangeFrom && this.to <= rangeTo) {
            return null;
        }
        Range[] resultRange = new Range[2];
        // Проверка, что В входит в А
        if (this.from <= rangeFrom && this.to >= rangeTo) {

            if (this.from != rangeFrom) {
                resultRange[0] = new Range(this.from, rangeFrom);
            }
            if (rangeTo != this.to) {
                resultRange[1] = new Range(rangeTo, this.to);
            }
            // Проверка, что А пересечение В
        } else if (this.from <= rangeFrom && this.to >= rangeFrom) {
            resultRange[0] = new Range(this.from, rangeFrom);
            // Проверка, что В пересечение А
        } else if (this.to >= rangeTo && this.from <= rangeTo) {
            resultRange[0] = new Range(rangeTo, this.to);
            // нет общего диапазона
        } else {
            resultRange[0] = this;
            resultRange[1] = range;
        }
        return resultRange;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public String rangeToString() {
        return "(" + from + ", " + to + ")";
    }

    /**
     * Длина диапазона
     *
     * @return вещественное число.
     */
    public double calcLength() {
        return to - from;
    }

    /**
     * Проверка вхождения числа в диапазон
     *
     * @param number проверяемое число
     * @return boolean  результат проверки. true - входит
     */
    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    /**
     * Пересечение диапазонов.
     *
     * @param range Диапазон, проверяемый на пересечение с данным
     * @return При отсутствии возвращает null, создавая пустую ссылку. В противном случае, диапазон - ообщий для исходных
     */
    public Range intersectRanges(Range range) {
        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        if (range.isInside(from) || range.isInside(to)) {
            return new Range(Math.max(from, rangeFrom), Math.min(to, rangeTo));
        }
        return null;
    }


}