package ru.inbox.foreman.testLambda;

import ru.inbox.foreman.person.Person;

import java.util.*;
import java.util.stream.*;

public class Test {
    public static void main(String[] arg) {

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Ivan", 15));
        list.add(new Person("Sergey", 30));
        list.add(new Person("Sergey", 35));
        list.add(new Person("Petr", 11));
        list.add(new Person("Anton", 45));
        list.add(new Person("Anton", 42));
        list.add(new Person("Efim", 25));
        list.add(new Person("Efim", 5));

//получить список уникальных имен
        System.out.println(list.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList()));

//вывести список уникальных имен в формате Имена:
        System.out.println(list.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена:", ".")));

//получить список людей младше 18, посчитать для них средний возраст
        List<Person> lowAgePersonList = list.stream()
                .filter(p -> p.getAge() <= 18)
                .collect(Collectors.toList());
        System.out.println(lowAgePersonList.stream()
                .map(Person::getName)
                .collect(Collectors.joining(",", "Их возраст меньше 18: ", "")));
        System.out.printf("Средний возраст: %s%n%n", lowAgePersonList
                .stream()
                .collect(Collectors.averagingInt(Person::getAge)));

//при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        Map<String, Double> list1 = list.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        list1.forEach((age, name) -> System.out.printf("age %s: %s%n", age, name));

//получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        List<String> middleAgePersonList = list.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(middleAgePersonList);

//Создать бесконечный поток корней чисел. С консоли
//прочитать число – сколько элементов нужно вычислить,
//затем – распечатать эти элементы
        System.out.println("Введите длину последовательность квадратов чисел: ");
        int n = new Scanner(System.in).nextInt();
        DoubleStream square = Stream.iterate(0, x -> x + 1)
                .mapToDouble(Math::sqrt)
                .limit(n);
        square.forEach(x -> System.out.printf("%.2f, ", x));
        System.out.println();
        //   System.out.println(Arrays.toString(square.toArray()));

//ряд фибоначи
        LongStream fibonacci = Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                .mapToLong(x -> x[0])
                .limit(n);
        System.out.println(Arrays.toString(fibonacci.toArray()));
    }
}
