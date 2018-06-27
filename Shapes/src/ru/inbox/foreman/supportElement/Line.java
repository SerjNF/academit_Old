package ru.inbox.foreman.supportElement;

/**
 * Класс линия. Вспомогательный для треугольника
 */
public class Line {
    private double x1;
    private double x2;
    private double y1;
    private double y2;

    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    /**
     * Длина линии
     *
     * @return double - длина линии
     */
    public double getLineLength() {
        return Math.sqrt((Math.pow(this.x1 - this.x2, 2) + Math.pow(this.y1 - this.y2, 2)));
    }

    @Override
    public int hashCode() {
        int hash = 4;
        int prime = 44;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return x1 == line.x1 && x2 == line.x2 && y1 == line.y1 && y2 == line.y2;
    }
}

