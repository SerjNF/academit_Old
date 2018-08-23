package ru.inbox.foreman.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.inbox.foreman.person.Person;
import ru.inbox.foreman.tree.Tree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TreeTest {


    private Tree<Person> testTree;

    @Before
    public void createField(){
        class PersonComparator implements Comparator<Person> {

            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        }
        testTree = new Tree<Person>(new PersonComparator());
    }


    @After
    public void clearField(){
        testTree = null;
    }
    @Test
    public void testAdd(){
        testTree.add(new Person("Ivan", 15));
        testTree.add(new Person("Efim", 45));
        assertEquals(2, testTree.getSize());
    }
    @Test
    public void testIteratorInDepth(){
        testAdd();
        Iterator<Person> testIterator = testTree.iteratorInDepth();
        assertTrue(testIterator.hasNext());
        testIterator.next().setName("Федор");
        assertTrue(testIterator.hasNext());
        System.out.println(testIterator.next().getName());
        assertFalse(testIterator.hasNext());
        testIterator.remove();

        Iterator<Person> testIterator1 = testTree.iteratorInDepth();
        assertTrue(testIterator1.hasNext());
        System.out.println(testIterator1.next().getName());
        assertTrue(testIterator1.hasNext());
        System.out.println(testIterator1.next().getName());

    }

}