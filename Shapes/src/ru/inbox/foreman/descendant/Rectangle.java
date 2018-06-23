package ru.inbox.foreman.descendant;

import ru.inbox.foreman.parent.Shape;

/**
 * Класс объектов прямоугольник
 *
 * @author SergeyNF
 * @since 23.06.2018
 */
public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String shapesName, double width, double height) {
        super(shapesName);
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
        if (!(o instanceof Rectangle)) {
            return false;
        }
        Rectangle r = (Rectangle) o;
        return (o == this) || (r.getHeight() == this.getHeight() && r.getWidth() == this.getWidth());// || (r.getWidth() == this.getHeight() && r.getHeight() == this.getWidth());
    }

    @Override
    public int hashCode() {
        int hash = 2;
        int prime = 44;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }
}
