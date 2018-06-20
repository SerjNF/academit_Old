package ru.inbox.foreman;


import java.util.ArrayList;

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
     * конструктор по умолчанию
     */
    Range() {
    }

    /**
     * Конструктор с параметрами
     * @param from начало диапазона
     * @param to конец диапазона
     */
    Range(double from, double to) {
        this.from = from <= to ? from : to;
        this.to = from <= to ? to : from;

    }

    double getFrom() {
        return from;
    }

    double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    void setTo(double to) {
        this.to = to;
    }


    /**
     * Длина диапазона
     *
     * @return вещественное число.
     */
    double calcLength() {
        return Math.abs(from - to);
    }

    /**
     * Проверка вхождения числа в диапазон
     *
     * @param number проверяемое число
     * @return boolean  результат проверки. true - входит
     */
    boolean isInside(double number) {
        return number >= from && number <= to;
    }

    /**
     * Пересечение диапазонов.
     *
     * @param range Диапазон, проверяемый на пересечение с данным
     * @return При отсутствии возвращает null, создавая пустую ссылку. В противном случае, диапазон - ообщий для исходных
     */
    Range interceptRange(Range range) {
        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        if (range.isInside(from) || range.isInside(to)) {
            return new Range(from >= rangeFrom ? from : rangeFrom, to <= rangeTo ? to : rangeTo);
        }
        return null;
    }

    /**
     * Static method
     * Объединение диапазонов. rangeOne + rangeTwo
     *
     * @param rangeOne первый диапазон
     * @param rangeTwo второй диапазон
     * @return ArrayList  диапазонов. Результат может состоять из однго или дву диапазонов
     */
    static ArrayList<Range> sumOfRanges(Range rangeOne, Range rangeTwo) {
        double rangeOneFrom = rangeOne.getFrom();
        double rangeOneTo = rangeOne.getTo();
        double rangeTwoFrom = rangeTwo.getFrom();
        double rangeTwoTo = rangeTwo.getTo();
        ArrayList<Range> resultRange = new ArrayList<>();

        if (rangeOne.isInside(rangeTwoFrom) || rangeOne.isInside(rangeTwoTo)) {
            resultRange.add(new Range(rangeOneFrom <= rangeTwoFrom ? rangeOneFrom : rangeTwoFrom, rangeOneTo >= rangeTwoTo ? rangeOneTo : rangeTwoTo));
        // нет общего диапазона
        } else {
            resultRange.add(rangeOne);
            resultRange.add(rangeTwo);
        }
        return resultRange;
    }

    /**
     * Static method
     * Вычитание диапазонов. rangeOne - rangeTwo
     *
     * @param rangeOne первый диапазон (A)
     * @param rangeTwo второй диапазон (B)
     * @return ArrayList  диапазонов. Результат может состоять из однго или дву диапазонов
     */
    static ArrayList<Range> subtractOfRange(Range rangeOne, Range rangeTwo) {
        double rangeOneFrom = rangeOne.getFrom();
        double rangeOneTo = rangeOne.getTo();
        double rangeTwoFrom = rangeTwo.getFrom();
        double rangeTwoTo = rangeTwo.getTo();
        if(rangeOneFrom == rangeTwoFrom && rangeOneTo == rangeTwoTo){
            return null;
        }
        ArrayList<Range> resultRange = new ArrayList<>();
        // Проверка, что В входит в А
        if (rangeOne.isInside(rangeTwoFrom) && rangeOne.isInside(rangeTwoTo)) {
            resultRange.add(new Range(rangeOneFrom, rangeTwoFrom));
            resultRange.add((new Range(rangeTwoTo, rangeOneTo)));
        // Проверка, что А пересечение В
        } else if (rangeOneFrom <= rangeTwoFrom && rangeOneTo >= rangeTwoFrom) {
            resultRange.add(new Range(rangeOneFrom, rangeTwoFrom));
        // Проверка, что В пересечение А
        } else if (rangeOneTo >= rangeTwoTo && rangeOneFrom <= rangeTwoTo) {
            resultRange.add(new Range(rangeTwoTo, rangeOneTo));
        // нет общего диапазона
        } else {
            resultRange.add(rangeOne);
            resultRange.add(rangeTwo);
        }
        return resultRange;
    }


}