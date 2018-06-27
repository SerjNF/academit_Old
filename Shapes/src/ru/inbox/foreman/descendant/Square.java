package ru.inbox.foreman.descendant;

import ru.inbox.foreman.parent.Shape;

/**
 * Класс объектов квадрат
 *
 * @author SergeyNF
 * @since 23.06.2018
 */
public class Square implements Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.width;
    }

    @Override
    public double getArea() {
        return this.width * this.width;
    }

    @Override
    public double getPerimeter() {
        return this.width * 4;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Square s = (Square) o;
        return s.width == this.width;
    }

    @Override
    public int hashCode() {
        int hash = 100;
        int prime = 22;
        hash = prime * hash + Double.hashCode(width);
        return hash;
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() + ", S = " + getArea() + ", P = " + getPerimeter();
    }
}
