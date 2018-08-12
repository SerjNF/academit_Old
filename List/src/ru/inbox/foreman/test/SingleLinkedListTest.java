package ru.inbox.foreman.test;

import org.junit.*;
import org.junit.Test;
import ru.inbox.foreman.exception.MyListIsEmptyException;
import ru.inbox.foreman.myList.SingleLinkedList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class SingleLinkedListTest {
    private SingleLinkedList<String> testList1;
    private SingleLinkedList<String> testList2;

    @BeforeClass
    public static void firthTest() {
        System.out.println("Test class SingleLinkedList");
    }

    @Before
    public final void createField() {
        testList1 = new SingleLinkedList<>();
        testList2 = new SingleLinkedList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNext()) {
                testList1.add(scanner.next().split("[\\s ,.()]")[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public final void cleanField() {
        testList1 = null;
        testList2 = null;
    }

    @Test
    public void testConstructor() {
        assertNotNull(testList1);
        assertNotNull(testList2);
    }

    @Test
    public void testGetSize() {
        assertEquals(7, testList1.getSize());
    }

    @Test
    public void tesGet() {
        assertNotNull(testList1.get());
        assertEquals("list", testList1.get());

        try {
            assertNotNull(testList2.get());
            Assert.fail("Did't throws");
        } catch (MyListIsEmptyException e) {
            assertNotNull(e);
            assertEquals("Collection is empty", e.getMessage());
        }
    }

    @Test
    public void testAdd() {
        int size = testList1.getSize();
        testList1.add(null);
        assertEquals(size + 1, testList1.getSize());
        assertNull(testList1.get());

    }

    @Test
    public void testGetAtIndex() {
        assertNotNull(testList1.getAtIndex(0));
        assertNotNull(testList1.getAtIndex(1));
        assertEquals("into", testList1.getAtIndex(2));
        assertEquals("Inserts", testList1.getAtIndex(6));

        try {
            assertNotNull(testList1.getAtIndex(-1));
            assertNotNull(testList1.getAtIndex(7));
            Assert.fail("Did't throws");
        } catch (RuntimeException e) {
            assertNotNull(e);
            assertEquals("Index out of range", e.getMessage());
        }
        try {
            testList2.getAtIndex(0);
            Assert.fail("Did't throws");
        } catch (MyListIsEmptyException e) {
            assertNotNull(e);
            assertEquals("Collection is empty", e.getMessage());
        }
    }

    @org.junit.Test
    public void testSetAtIndex() {
        testList1.setAtIndex(3, "setAtIndex");
        assertEquals(testList1.getAtIndex(3), "setAtIndex");
        try {
            assertNotNull(testList1.setAtIndex(-1, null));
            assertNotNull(testList1.setAtIndex(7, null));
            Assert.fail("Did't throws");
        } catch (RuntimeException e) {
            assertNotNull(e);
            assertEquals("Index out of range", e.getMessage());
        }
        try {
            testList2.setAtIndex(0, null);
            Assert.fail("Did't throws");
        } catch (MyListIsEmptyException e) {
            assertNotNull(e);
            assertEquals("Collection is empty", e.getMessage());
        }
    }

    @org.junit.Test
    public void testRemoveAtIndex() {
        assertNotNull(testList1.removeAtIndex(1));
        assertEquals(testList1.removeAtIndex(1), "into");
        assertEquals(5, testList1.getSize());
        testList1.add(null);
        assertNull(testList1.removeAtIndex(0));

        try {
            assertNotNull(testList1.setAtIndex(-1, null));
            assertNotNull(testList1.setAtIndex(5, null));
            Assert.fail("Did't throws");
        } catch (RuntimeException e) {
            assertNotNull(e);
            assertEquals("Index out of range", e.getMessage());
        }
        try {
            testList2.removeAtIndex(0);
            Assert.fail("Did't throws");
        } catch (MyListIsEmptyException e) {
            assertNotNull(e);
            assertEquals("Collection is empty", e.getMessage());
        }
    }

    @Test
    public void testRemoveData() {
        assertTrue(testList1.remove("list"));
        assertFalse(testList1.remove("list"));
        assertFalse(testList2.remove(null));
    }

    @Test
    public void testAddAtIndex() {
        testList1.addAtIndex(1, null);
        assertEquals(8, testList1.getSize());

        try {
            testList1.addAtIndex(-1, null);
            testList1.addAtIndex(8, null);
            Assert.fail("Did't throws");
        } catch (RuntimeException e) {
            assertNotNull(e);
            assertEquals("Index out of range", e.getMessage());
        }
    }

    @Test
    public void testRemove() {
        assertNotNull(testList1.get());
        testList1.add(null);
        assertNull(testList1.get());
        try {
            testList2.remove();
            Assert.fail("Did't throws");
        } catch (RuntimeException e) {
            assertNotNull(e);
            assertEquals("Collection is empty", e.getMessage());
        }
    }

    @Test
    public void testReverse() {
        int size = testList1.getSize();
        String data = testList1.getAtIndex(size - 1);
        testList1.reverse();
        assertEquals(size, testList1.getSize());
        assertEquals(data, testList1.get());

    }

    @org.junit.Test
    public void testCopy() {
        assertEquals(testList1, testList1.copy());
        SingleLinkedList<String> testCopy = testList2.copy();
        assertNotNull(testCopy);
        assertEquals(0, testCopy.getSize());
        try {
            assertNotNull(testCopy.get());
            Assert.fail("Did't throws");
        } catch (MyListIsEmptyException e) {
            assertNotNull(e);
            assertEquals("Collection is empty", e.getMessage());
        }
    }
}

