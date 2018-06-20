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

    Range() {
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    Range(double from, double to) {
        this.from = from >= to ? from : to;
        this.to = from >= to ? to : from;
    }

    public double calcLength() {
        return from - to;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range interceptRange(Range range) {
        double rangeFrom = range.getFrom();
        double rangeTo = range.getTo();

        if (range.isInside(from) || range.isInside(to)) {
            return new Range(from >= rangeFrom ? from : rangeFrom, to >= rangeTo ? to : rangeTo);
        }
        return null;
    }

    public ArrayList<Range> mergeRange(Range range) {
        ArrayList<Range> resultRange = new ArrayList<>();

        if (range.isInside(from) || range.isInside(to)) {
            resultRange.add(new Range(from <= range.getFrom() ? from : range.getFrom(), to >= range.getTo() ? to : range.getTo()));
        } else {
            resultRange.add(range);
            resultRange.add(this);
        }
        return resultRange;
    }


}