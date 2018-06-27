package ru.inbox.foreman.test;

import ru.inbox.foreman.descendant.Circle;
import ru.inbox.foreman.descendant.Rectangle;
import ru.inbox.foreman.descendant.Square;
import ru.inbox.foreman.descendant.Triangle;
import ru.inbox.foreman.parent.Shape;
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
                new Square(1.6),
                new Square(3.9),
                new Rectangle(6.9, 1.2),
                new Rectangle(6.9, 1.2),
                new Triangle(1, 4, -1, -2, -2, 7),
                new Triangle(1, 4, -1, -2, -2, 7),
                new Circle(2.9),
                new Circle(1.1),};

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
        System.out.printf("Результат сравнения Треугольника 1 и 2: %s%n", shapes[4].equals(shapes[5]));
        System.out.printf("Результат сравнения Круга 1 и 2: %s%n", shapes[6].equals(shapes[7]));

        System.out.println();
        System.out.printf("Хэш-код Квадрата 1: %s%n", shapes[0].hashCode());
        System.out.printf("Хэш-код Квадрата 2: %s%n", shapes[1].hashCode());
        System.out.printf("Хэш-код Прямоугольника 1: %s%n", shapes[2].hashCode());
        System.out.printf("Хэш-код Прямоугольника 2: %s%n", shapes[3].hashCode());
        System.out.printf("Хэш-код Треугольник 1: %s%n", shapes[4].hashCode());
        System.out.printf("Хэш-код Треугольник 2: %s%n", shapes[5].hashCode());
        System.out.printf("Хэш-код Круг 1: %s%n", shapes[6].hashCode());
        System.out.printf("Хэш-код Круг 2: %s%n", shapes[7].hashCode());
    }

    /**
     * Функия поиска фигуры с площадью на месте number после максимальной
     *
     * @param shapes массив фигур
     * @param number номер возвращаемой фигуры
     * @return фигура
     */
    private static Shape searchShapeWithNumberOfArea(Shape[] shapes, int number) {
        Shape[] tempShapes = Arrays.copyOf(shapes, shapes.length);
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
    private static Shape searchShapeWithNumberOfPerimeter(Shape[] shapes, int number) {
        Shape[] tempShapes = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(tempShapes, new SortShapeByPerimeter());
        return tempShapes[number - 1];
    }

    /**
     * Вывод в консоль информации по фигуре
     *
     * @param shape фигура
     */
    private static void showInfoShape(Shape shape) {
        System.out.printf("Фигура класса %-40s высотой %5.2f, шириной %5.2f, S = %5.2f, P = %5.2f%n", shape.getClass().getName(), shape.getHeight(), shape.getWidth(), shape.getArea(), shape.getPerimeter());
    }

}
