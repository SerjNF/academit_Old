package ru.inbox.foreman.supportElement;


import ru.inbox.foreman.parent.Shape;

import java.util.Comparator;

/**
 * Определение сортировки объектов Shape по периметру
 */
public class SortShapeByPerimeter implements Comparator<Shape> {
    @Override
    public int compare(Shape shapeOne, Shape shapeTwo) {
        return Double.compare(shapeOne.getPerimeter(), shapeTwo.getPerimeter());
    }
}