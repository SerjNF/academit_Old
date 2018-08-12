package ru.inbox.foreman.hashTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class HashTable<T> implements Collection<T> {
    private static int DEFAULT_CAPACITY = 10;
    private int size;
    private ArrayList<T>[] arrayLists;
    private int modCount = 0;


    public HashTable(int capacity) {
        //noinspection unchecked
        arrayLists = (ArrayList<T>[]) new ArrayList[capacity];
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
        Objects.requireNonNull(o);
        //noinspection unchecked
        int index = getIndex((T) o);
        if (arrayLists[index] == null) {
            return false;
        }
        return arrayLists[index].contains(o);
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
        Objects.requireNonNull(o);
        //noinspection unchecked
        int index = getIndex((T) o);
        if (arrayLists[index] == null) {
            arrayLists[index] = new ArrayList<>();
        }
        //noinspection unchecked
        arrayLists[index].add((T) o);
        size++;
        modCount++;
        return true;
    }

    private int getIndex(T o) {
        return Math.abs(o.hashCode() % arrayLists.length);
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        //noinspection unchecked
        int index = getIndex((T) o);
        if (arrayLists[index] == null) {
            return false;
        }
        size--;
        modCount++;
        return arrayLists[index].remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        Objects.requireNonNull(c);
        for (Object obj : c) {
            if (!add(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        //noinspection unchecked
        arrayLists = (ArrayList<T>[]) new ArrayList[DEFAULT_CAPACITY];
        size = 0;
        modCount++;
    }

    @Override
    public boolean retainAll(Collection c) {
        Objects.requireNonNull(c);
        size = 0;
        boolean result = false;
        for (ArrayList<T> items : arrayLists) {
            if (items != null) {
                result = items.retainAll(c);
                size += items.size();
                modCount++;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection c) {
        Objects.requireNonNull(c);
        boolean result = false;
        for (Object obj : c) {
            if (remove(obj)) {
                result = true;
                modCount++;
            }
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection c) {
        Objects.requireNonNull(c);
        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T[] toArray(Object[] a) {
        return (T[]) new Object[0];
    }
}
