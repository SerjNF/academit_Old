package ru.inbox.foreman.hashTable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static int DEFAULT_CAPACITY = 10;
    private int size;
    private ArrayList<T>[] elements;
    private int modCount = 0;


    public HashTable(int capacity) {
        //noinspection unchecked
        elements = (ArrayList<T>[]) new ArrayList[capacity];
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
        if (elements[index] == null) {
            return false;
        }
        return elements[index].contains(o);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int expectedModCount = modCount;
            private int indexItem = 0;
            private int indexElement = 0;
            private int currentItem = 0;

            @Override
            public boolean hasNext() {
                return currentItem < size;
            }

            @Override
            public Object next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Collection modified");
                }
                for (; indexElement < elements.length; ++indexElement) {
                    if (elements[indexElement] == null) {
                        continue;
                    }
                    if (indexItem >= elements[indexElement].size()) {
                        indexItem = 0;
                        continue;
                    }
                    currentItem++;
                    int j = indexItem;
                    indexItem++;
                    return elements[indexElement].get(j);
                }
                throw new NoSuchElementException("No Such Element");
            }
        };
    }

    @Override
    public Object[] toArray() {
        int indexItem = 0;
        int indexElement = 0;
        int ArrayIndex = 0;
        Object[] returnArray = new Object[size];
        while (indexElement < elements.length) {
            if (elements[indexElement] != null) {
                if (indexItem >= elements[indexElement].size()) {
                    indexItem = 0;
                    indexElement++;
                    continue;
                }
                returnArray[ArrayIndex] = elements[indexElement].get(indexItem);
                indexItem++;
                ArrayIndex++;
            } else {
                indexElement++;
            }
        }
        return returnArray;
    }


    @Override
    public boolean add(Object o) {
        Objects.requireNonNull(o);
        //noinspection unchecked
        int index = getIndex((T) o);
        if (elements[index] == null) {
            elements[index] = new ArrayList<>();
        }
        //noinspection unchecked
        elements[index].add((T) o);
        size++;
        modCount++;
        return true;
    }

    private int getIndex(T o) {
        return Math.abs(o.hashCode() % elements.length);
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        //noinspection unchecked
        int index = getIndex((T) o);
        if (elements[index] == null) {
            return false;
        }
        size--;
        modCount++;
        return elements[index].remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        Objects.requireNonNull(c);
        for (Object cItem : c) {
            if (!add(cItem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        //noinspection unchecked
        elements = (ArrayList<T>[]) new ArrayList[DEFAULT_CAPACITY];
        size = 0;
        modCount++;
    }

    @Override
    public boolean retainAll(Collection c) {
        Objects.requireNonNull(c);
        //noinspection unchecked
        ArrayList<T>[] resultArrayList = (ArrayList<T>[]) new ArrayList[elements.length];
        int resultSize = 0;

        for (Object cItem : c) {
            Objects.requireNonNull(cItem);
            //noinspection unchecked
            int index = getIndex((T) cItem);
            ArrayList<T> arrayListAtIndex = elements[index];
            if (arrayListAtIndex != null) {
                for (T item : arrayListAtIndex) {
                    if (Objects.equals(item, cItem)) {
                        if (resultArrayList[index] == null) {
                            resultArrayList[index] = new ArrayList<>();
                        }
                        resultArrayList[index].add(item);
                        resultSize++;
                    }
                }
            }
        }
        boolean result = size != resultSize;
        if (result) {
            modCount++;
        }
        elements = resultArrayList;
        size = resultSize;
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

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Object[] a) {
        T[] array = (T[]) toArray();
        if (a.length < size) {
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return (T[]) a;
    }
}
