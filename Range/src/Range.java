package ru.inbox.foreman;

/**Класс Range. Диапазон определён от меньшего числа к большему
 * @author SergeyNF
 * @since 20.06.2018
 */
public class Range {

    private double from;
    private double to;

    Range(){
    }

    Range(double from, double to) {
        this.from = from >= to ?  from : to;
        this.to = from >= to ?  to : from;
    }

    public Double calcLength() {
        return from - to;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public static void main(String[] arg){
        System.out.printf("%f",new Range(5.0,6.5).calcLength());
    }
}