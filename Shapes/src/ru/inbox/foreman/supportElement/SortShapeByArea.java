package ru.inbox.foreman.supportElement;

import ru.inbox.foreman.parent.Shape;

import java.util.Comparator;

/**
 * Определение сортировки объектов Shape по площади
 */
public class SortShapeByArea implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
