package ru.inbox.foreman.parent;

/**
 * Класс объектов фигура
 *
 * @author SergeyNF
 * @since 23.06.2018
 */
public class Shape {
    private final String shapesName;

    protected Shape(String shapesName) {
        this.shapesName = shapesName;
    }

    public double getWidth() {
        return 0;
    }

    public double getHeight() {
        return 0;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    public String getName() {
        return shapesName;
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() + ", Фигура - " + shapesName + ", S = " + getArea() + ", P = " + getPerimeter();
    }
}
