package ru.inbox.foreman.supportElement;

/**
 * Класс линия. Вспомогательны для треугольника
 */
public class Line {
    private double x1;
    private double x2;
    private double y1;
    private double y2;

    public Line(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Длина линии
     * @return double - длина линии
     */
    public double lengthLine() {
        return Math.sqrt((Math.pow(this.x1 - this.x2, 2) + Math.pow(this.y1 - this.y2, 2)));
    }
}
