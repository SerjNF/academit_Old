package ru.inbox.foreman.descendant;

import ru.inbox.foreman.parent.Shape;
import ru.inbox.foreman.supportElement.Line;

/**
 * Класс объектов треугольник
 *
 * @author SergeyNF
 * @since 23.06.2018
 */
public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    private Line a;
    private Line b;
    private Line c;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        a = new Line(this.x1, this.y1, this.x2, this.y2);
        b = new Line(this.x3, this.y3, this.x2, this.y2);
        c = new Line(this.x1, this.y1, this.x3, this.y3);
    }

    @Override
    public double getWidth() {
        return Math.max(this.x1, Math.max(this.x2, this.x3));
    }

    @Override
    public double getHeight() {
        return Math.max(this.y1, Math.max(this.y2, this.y3));
    }

    @Override
    public double getArea() {
        double semiPerimeter = (a.getLineLength() + b.getLineLength() + c.getLineLength()) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - a.getLineLength()) * (semiPerimeter - b.getLineLength()) * (semiPerimeter - c.getLineLength()));
    }

    @Override
    public double getPerimeter() {
        return a.getLineLength() + b.getLineLength() + c.getLineLength();
    }

    private Line getA() {
        return a;
    }

    private Line getB() {
        return b;
    }

    private Line getC() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Triangle t = (Triangle) o;
        return a.equals(t.a) && b.equals(t.b) && c.equals(t.c);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        int prime = 33;
        hash = prime * hash + (a != null ? a.hashCode() : 0);
        hash = prime * hash + (b != null ? b.hashCode() : 0);
        hash = prime * hash + (c != null ? c.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() + ", S = " + getArea() + ", P = " + getPerimeter();
    }
}
