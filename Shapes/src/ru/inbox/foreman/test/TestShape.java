package ru.inbox.foreman.test;

import ru.inbox.foreman.descendant.*;
import ru.inbox.foreman.parent.*;
import ru.inbox.foreman.supportElement.SortShapeByArea;
import ru.inbox.foreman.supportElement.SortShapeByPerimeter;

import java.util.Arrays;

/**
 * Класс с методами для тестирования объектов, птомков класса Shape
 *
 * @author SergeyNF
 * @since 23.06.2018
 */
public class TestShape {

    public static void main(String[] arg) {

        Shape[] shapes = new Shape[]{
                new Square("Квадрат 1", 1.6),
                new Square("Квадрат 2", 3.9),
                new Rectangle("Прямоугольник 1", 6.9, 0.2),
                new Rectangle("Прямоугольник 2", 0.2, 6.9),
                new Triangle("Треугольник 1", 0, 5, 0, 0, 4, 3),
                new Triangle("Треугольник 2", 1, 4, -1, -2, -2, 7),
                new Circle("Круг 1", 2.9),
                new Circle("Круг 2", 1.1),};

        System.out.println("Данные всех фигур");
        for (Shape shape : shapes) {
            showInfoShape(shape);
        }

        System.out.println();
        System.out.println("Данные фигруры с максимальной площадью");
        int number = 1;
        showInfoShape(searchShapeWithNumberOfArea(shapes, number));

        System.out.println();
        System.out.println("Данные фигруры со вторым по счету периметром");
        number = 2;
        showInfoShape(searchShapeWithNumberOfPerimeter(shapes, number));

    }

    /**
     * Функия поиска фигуры с площадью на месте number после максимальной
     *
     * @param shapes массив фигур
     * @param number номер возвращаемой фигуры
     * @return фигура
     */
    private static Shape searchShapeWithNumberOfArea(Shape[] shapes, int number) {
        Arrays.sort(shapes, new SortShapeByArea());
        return shapes[shapes.length - 1 - (number - 1)];
    }

    /**
     * Функия поиска фигуры с периметром на месте number после минимального
     *
     * @param number номер возвращаемой фигуры
     * @param shapes массив фигур
     * @return фигура
     */
    private static Shape searchShapeWithNumberOfPerimeter(Shape[] shapes, int number) {
        Arrays.sort(shapes, new SortShapeByPerimeter());
        return shapes[number - 1];
    }

    /**
     * Вывод в консоль информации по фигуре
     *
     * @param shape фигура
     */
    private static void showInfoShape(Shape shape) {
        System.out.printf("Фигура %-15s высотой %5.2f, шириной %5.2f, S = %5.2f, P = %5.2f%n", shape.getName(), shape.getHeight(), shape.getWidth(), shape.getArea(), shape.getPerimeter());
    }

}
