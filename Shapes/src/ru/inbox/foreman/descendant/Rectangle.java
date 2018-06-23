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

    public Rectangle(String shapesName, double width, double heigth) {
        super(shapesName);
        this.width = width;
        this.height = heigth;
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
}
