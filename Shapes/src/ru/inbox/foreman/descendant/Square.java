package ru.inbox.foreman.descendant;

import ru.inbox.foreman.parent.Shape;

/**
 * Класс объектов квадрат
 *
 * @author SergeyNF
 * @since 23.06.2018
 */
public class Square extends Shape {
    private double width;
    private double height;

    public Square(String shapesName, double width) {
        super(shapesName);
        this.width = width;
        this.height = width;
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
        return this.width * this.width;
    }

    @Override
    public double getPerimeter() {
        return this.width * 4;
    }
}
