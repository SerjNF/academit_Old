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
     * Static method
     * Объединение диапазонов. rangeOne + rangeTwo
     *
     * @param range второй диапазон
     * @return ArrayList  диапазонов. Результат может состоять из однго или дву диапазонов
     */
    public Range[] sumOfRanges(Range range) {

        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();
        Range[] resultRange;

        if ((this.from <= rangeFrom && this.to >= rangeFrom) || (this.from <= rangeTo && this.to >= rangeTo)) {
            resultRange = new Range[]{new Range(Math.min(this.from, rangeFrom), Math.max(this.to, rangeTo))};
            // нет общего диапазона
        } else {
            resultRange = new Range[]{this, range};
        }
        return resultRange;
    }

    /**
     * Static method
     * Вычитание диапазонов. this(A) - range(B)
     *
     * @param range второй диапазон
     * @return Массив диапазонов. Результат может состоять из однго или дву диапазонов. Если вычитаем равные диапазоны или из меньшего больший, возвращает null
     */
    public Range[] subtractOfRange(Range range) {

        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        // Если вычитаем равные диапазоны или из меньшего больший, возвращает ноль
        if (this.from >= rangeFrom && this.to <= rangeTo) {
            return null;
        }
        Range[] resultRange;
        // Проверка, что В входит в А
        if (this.from <= rangeFrom && this.to >= rangeTo) {
            Range rOne = null;
            Range rTwo = null;
            if (this.from != rangeFrom) {
                rOne = new Range(this.from, rangeFrom);
            }
            if (rangeTo != this.to) {
                rTwo = new Range(rangeTo, this.to);
            }

            if(rOne !=null && rTwo != null){
                resultRange = new Range[] {rOne, rTwo};
            } else{
                resultRange = new Range[] { rOne != null ? rOne : rTwo};
            }

            // Проверка, что А пересечение В
        } else if (this.from <= rangeFrom && this.to >= rangeFrom) {
            resultRange = new Range[]{new Range(this.from, rangeFrom)};
            // Проверка, что В пересечение А
        } else if (this.to >= rangeTo && this.from <= rangeTo) {
            resultRange = new Range[]{new Range(rangeTo, this.to)};
            // нет общего диапазона
        } else {
            resultRange = new Range[]{this, range};
        }
        return resultRange;
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
     * Пересечение диапазонов.
     *
     * @param range Диапазон, проверяемый на пересечение с данным
     * @return При отсутствии возвращает null, создавая пустую ссылку. В противном случае, диапазон - общий для исходных
     */
    public Range intersectRanges(Range range) {
        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        if (this.from <=rangeFrom && this.to >= rangeFrom || rangeTo <= this.to && rangeTo >=this.from) {
            return new Range(Math.max(this.from, rangeFrom), Math.min(this.to, rangeTo));
        }
        return null;
    }
}