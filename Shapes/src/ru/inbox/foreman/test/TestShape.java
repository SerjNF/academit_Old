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
                new Rectangle("Прямоугольник 1", 6.9, 1.2),
                new Rectangle("Прямоугольник 2", 1.2, 6.9),
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


        System.out.println();
        System.out.println("Проверка метода toString");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }

        System.out.println();
        System.out.printf("Результат сравнения Прямоугольника 1 и 2: %s%n", shapes[2].equals(shapes[3]));
        System.out.printf("Результат сравнения Круга 1 и 2: %s%n", shapes[6].equals(shapes[7]));

        System.out.println();
        System.out.printf("Хэш-код Квадрата 1: %s%n", shapes[0].hashCode());
        System.out.printf("Хэш-код Прямоугольника 1: %s%n", shapes[2].hashCode());
        System.out.printf("Хэш-код Прямоугольника 2: %s%n", shapes[3].hashCode());
    }

    /**
     * Функия поиска фигуры с площадью на месте number после максимальной
     *
     * @param shapes массив фигур
     * @param number номер возвращаемой фигуры
     * @return фигура
     */
    private static Shape searchShapeWithNumberOfArea(final Shape[] shapes, int number) {
        Shape[] tempShapes = new Shape[shapes.length];
        System.arraycopy(shapes, 0, tempShapes, 0, shapes.length);
        Arrays.sort(tempShapes, new SortShapeByArea());
        return tempShapes[tempShapes.length - 1 - (number - 1)];
    }

    /**
     * Функия поиска фигуры с периметром на месте number после минимального
     *
     * @param number номер возвращаемой фигуры
     * @param shapes массив фигур
     * @return фигура
     */
    private static Shape searchShapeWithNumberOfPerimeter(final Shape[] shapes, int number) {
        Shape[] tempShapes = new Shape[shapes.length];
        System.arraycopy(shapes, 0, tempShapes, 0, shapes.length);
        Arrays.sort(tempShapes, new SortShapeByPerimeter());
        return tempShapes[number - 1];
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
