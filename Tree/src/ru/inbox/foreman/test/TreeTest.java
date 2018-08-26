package ru.inbox.foreman.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.inbox.foreman.person.Person;
import ru.inbox.foreman.tree.Tree;

import java.io.*;
import java.util.Comparator;

import static org.junit.Assert.*;

public class TreeTest {

    private Tree<Person> testTreePerson;
    private Tree<String> testTreeEmpty;
    private Tree<String> testTreeString;

    @Before
    public void createField() {
        class PersonComparator implements Comparator<Person> {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        }
        testTreePerson = new Tree<>(new PersonComparator());
        testTreeEmpty = new Tree<>();
        testTreeString = new Tree<>();
    }

    @After
    public void clearField() {
        testTreePerson = null;
    }

    @Test
    public void testAdd() {
        testTreePerson.add(new Person("Ivan", 15));
        testTreePerson.add(new Person("Efim", 45));
        testTreePerson.add(new Person("Ivan", 25));
        testTreePerson.add(new Person("Sergey", 35));
        testTreePerson.add(new Person("Sergey", 35));
        testTreePerson.add(new Person("Petr", 11));
        testTreePerson.add(null);
        testTreePerson.add(new Person("Anton", 42));
        testTreePerson.add(new Person("Efim", 25));
        testTreePerson.add(new Person("Efim", 5));
        testTreePerson.add(new Person("Ivan", 37));
        testTreePerson.add(new Person("Ivan", 14));

        assertEquals(12, testTreePerson.getSize());

        testTreeString.add("Ivan");
        testTreeString.add("Efim");
        testTreeString.add("Ivan");
        testTreeString.add("Sergey");
        testTreeString.add("Sergey");
        testTreeString.add("Petr");
        testTreeString.add("Anton");
        testTreeString.add("Efim");
        testTreeString.add("Efim");
        testTreeString.add("Ivan");
        testTreeString.add("Ivan");
        testTreeString.add(null);

        assertEquals(12, testTreeString.getSize());

    }

    @Test
    public void testFind() {
        testAdd();
        assertEquals(new Person("Sergey", 35), testTreePerson.findNode(new Person("Sergey", 35)).getData());
        assertNotNull(testTreePerson.findNode(null));
        assertNotNull(testTreePerson.findNode(new Person("Sergey", 35)));
        assertNull(testTreePerson.findNode(new Person("Serj", 35)));
        try {
            assertNotEquals(null, testTreeEmpty.findNode(null));
            Assert.fail("Нет исключения");
        } catch (Exception e) {
            assertNotNull(e);
            assertEquals("No Such Element", e.getMessage());
        }
    }

    @Test
    public void testRemove() {
        testAdd();
        assertTrue(testTreePerson.remove(new Person("Sergey", 35)));
        assertTrue(testTreePerson.remove(new Person("Sergey", 35)));
        assertEquals(10, testTreePerson.getSize());
        try {
            assertFalse(testTreeEmpty.remove(null));
            Assert.fail("Нет исключения");
        } catch (Exception e) {
            assertNotNull(e);
            assertEquals("No Such Element", e.getMessage());
        }
    }

    @Test
    public void testVisitVertical() {
        testAdd();
        try (PrintWriter printWriter = new PrintWriter("outputTestTreeVisitVertical.txt")) {
            testTreePerson.visitInVertical(person -> printNodes(person, printWriter)
            );
            printWriter.println("После удаления");
            testTreePerson.remove(new Person("Sergey", 35));
            testTreePerson.remove(new Person("Sergey", 35));
            testTreePerson.visitInVertical(person -> printNodes(person, printWriter)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVisitVerticalRecurs() {
        testAdd();
        try (PrintWriter printWriter = new PrintWriter("outputTestTreeVisitVerticalRecurs.txt")) {
            testTreePerson.visitInVerticalRecurs(person -> printNodes(person, printWriter)
            );
            printWriter.println("После удаления");
            testTreePerson.remove(new Person("Sergey", 35));
            testTreePerson.remove(new Person("Sergey", 35));
            testTreePerson.visitInVerticalRecurs(person -> printNodes(person, printWriter)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVisitHorizontal() {
        testAdd();
        try (PrintWriter printWriter = new PrintWriter("outputTestTreeVisitHorizontal.txt")) {
            testTreePerson.visitInHorizontal(person -> printNodes(person, printWriter));
            printWriter.println("После удаления");
            testTreePerson.remove(new Person("Sergey", 35));
            testTreePerson.remove(new Person("Sergey", 35));
            testTreePerson.visitInHorizontal(person -> printNodes(person, printWriter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printNodes(Person person, PrintWriter printWriter) {
        if (person == null) {
            printWriter.println((String) null);
        } else {
            printWriter.printf("Имя: %10s , возраст: %d%n", person.getName(), person.getAge());
        }
    }
}