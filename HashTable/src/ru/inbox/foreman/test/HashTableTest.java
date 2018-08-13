package ru.inbox.foreman.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.inbox.foreman.hashTable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.*;

public class HashTableTest {
    //таблица с данными
    private HashTable<String> testTableHaveData;
    // пустая таблицы
    private HashTable<String> testTableEmpty;


    @Before
    public void createField() {
        testTableEmpty = new HashTable<>();
        assertNotNull(testTableEmpty);
        testTableHaveData = new HashTable<>();
        assertNotNull(testTableHaveData);

        assertTrue(testTableHaveData.add("word1"));
    }

    @After
    public void clearField() {
        testTableEmpty = null;
    }

    @Test
    public void add() {
        try {
            testTableHaveData.add(null);
            Assert.fail("нет исключения");
        } catch (Exception e) {

            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void size() {
        assertEquals(0, testTableEmpty.size());
        assertEquals(1, testTableHaveData.size());

    }

    @Test
    public void isEmpty() {
        assertTrue(testTableEmpty.isEmpty());
    }

    @Test
    public void contains() {
        assertTrue(testTableHaveData.contains("word1"));
        assertFalse(testTableHaveData.contains("word2"));
        try {
            testTableHaveData.contains(null);
            Assert.fail("нет исключения");
        } catch (Exception e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void iterator() {
        assertTrue(testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n", "word2"))));
        Iterator iter = testTableHaveData.iterator();
        assertTrue(iter.hasNext());
        assertEquals("n", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("word1", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("word2", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("word2", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("word3", iter.next());
        assertFalse(iter.hasNext());
        testTableHaveData.add("add");
        try {
            assertNull(iter.next());
        } catch (Exception e) {
            assertNotNull(e);
            assertNotEquals("", e.getMessage());
        }

        try {
            assertNull(iter.next());
        } catch (Exception e) {
            assertNotNull(e);
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void toArray() {
        String[] arrayStrings = new String[]{"n", "word1", "word2", "word3", "word3"};
        assertTrue(testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n", "word3"))));
        assertArrayEquals(arrayStrings, testTableHaveData.toArray());
        assertArrayEquals(new String[0], testTableEmpty.toArray());
    }


    @Test
    public void remove() {
        assertFalse(testTableHaveData.remove("word2"));
        assertTrue(testTableHaveData.remove("word1"));
        assertFalse(testTableHaveData.remove("word2"));
        assertEquals(0, testTableHaveData.size());
        assertFalse(testTableEmpty.remove("word2"));
        try {
            testTableHaveData.remove(null);
            Assert.fail("нет исключения");
        } catch (Exception e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void addAll() {
        assertTrue(testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n"))));
        assertEquals(4, testTableHaveData.size());
        try {
            testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n", null)));
            Assert.fail("нет исключения");
        } catch (Exception e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void clear() {
        testTableHaveData.clear();
        assertEquals(0, testTableHaveData.size());
        assertNotNull(testTableHaveData);
    }

    @Test
    public void retainAll() {
        assertTrue(testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "word1"))));
        assertTrue(testTableHaveData.retainAll(new ArrayList<>(Collections.singletonList("word1"))));
        assertEquals(testTableHaveData.size(), 2);

        try {
            testTableHaveData.retainAll((new ArrayList<>(Arrays.asList("word2", "word3", "n", null))));
            Assert.fail("нет исключения");
        } catch (Exception e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void removeAll() {
        testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n")));
        assertTrue(testTableHaveData.removeAll(new ArrayList<>(Arrays.asList("word2", "word3", "n"))));
        assertFalse(testTableHaveData.removeAll(new ArrayList<>(Arrays.asList("word2", "word3", "n"))));
        try {
            testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n", null)));
            Assert.fail("нет исключения");
        } catch (Exception e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void containsAll() {
    }

    @Test
    public void toArray1() {
    }
}