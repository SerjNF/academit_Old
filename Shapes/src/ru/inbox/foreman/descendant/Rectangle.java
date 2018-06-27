package ru.inbox.foreman.descendant;

import ru.inbox.foreman.parent.Shape;

/**
 * Класс объектов прямоугольник
 *
 * @author SergeyNF
 * @since 23.06.2018
 */
public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getArea() {
        return this.width * this.height;
    }

    @Override
    public double getPerimeter() {
        return (this.width + this.height) * 2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle r = (Rectangle) o;
        return r.height == this.height && r.width == this.width;
    }

    @Override
    public int hashCode() {
        int hash = 2;
        int prime = 44;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() + ", S = " + getArea() + ", P = " + getPerimeter();
    }
}
