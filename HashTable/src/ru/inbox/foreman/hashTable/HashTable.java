package ru.inbox.foreman.hashTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HashTable<T> implements Collection<T> {
    private static int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] arrayLists;

    public HashTable(int capacity) {
        arrayLists = new Object[capacity];
        size = 0;
    }

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        int index = Math.abs(o.hashCode() % arrayLists.length);
        if(arrayLists[index] == null){

            arrayLists[index] = new ArrayList<>();
        }
        ArrayList<T> item = (ArrayList<T>) arrayLists[index];
        item.add((T)o);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public T[] toArray(Object[] a) {
        return (T[]) new Object[0];
    }
}
