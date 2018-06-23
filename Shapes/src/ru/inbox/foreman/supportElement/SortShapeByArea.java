package ru.inbox.foreman.supportElement;

import ru.inbox.foreman.parent.Shape;

import java.util.Comparator;

/**
 * Определение сортировки объектов Shape по площади
 */
public class SortShapeByArea implements Comparator<Shape> {
    @Override
    public int compare(Shape shapeOne, Shape shapeTwo) {
        if (shapeOne.getArea() > shapeTwo.getArea()){
            return 1;
        } else if (shapeOne.getArea() < shapeTwo.getArea()){
            return -1;
        } else {
            return 0;
        }
    }
}
