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
        assertTrue(testTableHaveData.add(null));

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
        assertFalse(testTableHaveData.contains(null));
        testTableHaveData.add(null);
        assertTrue(testTableHaveData.contains(null));
    }

    @Test
    public void iterator() {
        assertTrue(testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n", "word2"))));
        Iterator<String> iterator = testTableHaveData.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("n", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("word1", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("word2", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("word2", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("word3", iterator.next());
        assertFalse(iterator.hasNext());
        testTableHaveData.add("add");
        try {
            assertNull(iterator.next());
        } catch (Exception e) {
            assertNotNull(e);
            assertNotEquals("", e.getMessage());
        }

        try {
            assertNull(iterator.next());
        } catch (Exception e) {
            assertNotNull(e);
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void toArray() {
        String[] arrayStrings = new String[]{null, "n", "word1", "word2", "word3", "word3"};
        assertTrue(testTableHaveData.addAll(new ArrayList<>(Arrays.asList(null, "word2", "word3", "n", "word3"))));
        assertArrayEquals(arrayStrings, testTableHaveData.toArray());
        assertArrayEquals(new String[0], testTableEmpty.toArray());
    }


    @Test
    public void remove() {
        testTableHaveData.addAll(new ArrayList<>(Collections.singletonList(null)));

        assertTrue(testTableHaveData.remove(null));
        assertTrue(testTableHaveData.remove("word1"));
        assertFalse(testTableHaveData.remove("word2"));
        assertEquals(0, testTableHaveData.size());
        assertFalse(testTableEmpty.remove("word2"));
    }

    @Test
    public void addAll() {
        assertTrue(testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n"))));
        assertEquals(4, testTableHaveData.size());
        assertTrue(testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n", null))));
        assertFalse(testTableHaveData.addAll(testTableEmpty));
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
        assertFalse(testTableHaveData.retainAll((new ArrayList<>(Collections.singletonList("word1")))));
    }

    @Test
    public void removeAll() {
        testTableHaveData.addAll(new ArrayList<>(Arrays.asList("word2", "word3", "n", "n" ,"n" , null, "word2")));
        assertEquals(8, testTableHaveData.size());
        assertFalse(testTableHaveData.removeAll(testTableEmpty));
        assertTrue(testTableHaveData.removeAll(new ArrayList<>(Arrays.asList("word2", "word3", "n"))));
        assertArrayEquals(testTableHaveData.toArray(), new String[]{null, "word1"});
        assertEquals(2, testTableHaveData.size());
        assertFalse(testTableHaveData.removeAll(new ArrayList<>(Arrays.asList("word2", "word3", "n"))));
        assertTrue(testTableHaveData.removeAll(new ArrayList<>(Arrays.asList("word2", "word3", "n", null))));
    }

    @Test
    public void containsAll() {
    }

    @Test
    public void toArray1() {
    }
}