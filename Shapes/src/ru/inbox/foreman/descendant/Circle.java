package ru.inbox.foreman.descendant;

import ru.inbox.foreman.parent.Shape;

/**
 * Класс объектов круг
 *
 * @author SergeyNF
 * @since 23.06.2018
 */
public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        //super(nameShape);
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return this.radius * 2;
    }

    @Override
    public double getHeight() {
        return this.radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Circle c = (Circle) o;
        return c.radius == this.radius;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int prime = 20;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() + ", S = " + getArea() + ", P = " + getPerimeter();
    }

}
