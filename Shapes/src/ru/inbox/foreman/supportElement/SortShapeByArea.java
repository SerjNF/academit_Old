package ru.inbox.foreman.supportElement;

import ru.inbox.foreman.parent.Shape;

import java.util.Comparator;

/**
 * Определение сортировки объектов Shape по площади
 */
public class SortShapeByArea implements Comparator<Shape> {
    @Override
    public int compare(Shape shapeOne, Shape shapeTwo) {
        return Double.compare(shapeOne.getArea(), shapeTwo.getArea());
    }
}
