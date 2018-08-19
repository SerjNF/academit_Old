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
        int index = getIndex(o);
        if (elements[index] == null) {
            return false;
        }
        return elements[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int indexItem = 0;
            private int indexElement = 0;
            private int currentItem = 0;

            @Override
            public boolean hasNext() {
                return currentItem < size;
            }

            @Override
            public T next() {
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
        Object[] returnArray = new Object[size];
        for (T item : this) {
            returnArray[indexItem] = item;
            indexItem++;
        }
        return returnArray;
    }


    @Override
    public boolean add(Object o) {
        int index = getIndex(o);
        if (elements[index] == null) {
            elements[index] = new ArrayList<>();
        }
        //noinspection unchecked
        elements[index].add((T) o);
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);
        if (elements[index] == null || !elements[index].remove(o)) {
            return false;
        }
        size--;
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        Objects.requireNonNull(c);
        int expectedSize = size;
        for (Object cItem : c) {
            add(cItem);
        }
        return size != expectedSize;
    }

    @Override
    public void clear() {
        for (ArrayList<T> element : elements) {
            if (element != null) {
                element.clear();
            }
        }
        size = 0;
        modCount++;
    }

    @Override
    public boolean retainAll(Collection c) {
        Objects.requireNonNull(c);
        int resultSize = 0;
        for (ArrayList<T> element : elements) {
            if (element != null) {
                element.retainAll(c);
                resultSize += element.size();
            }
        }
        if (size != resultSize) {
            modCount++;
            size = resultSize;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        Objects.requireNonNull(c);
        int resultSize = 0;

        for (ArrayList<T> element : elements) {
            if (element != null) {
                //noinspection SuspiciousMethodCalls
                element.removeAll(c);
                resultSize += element.size();
            }
        }
        if (size != resultSize) {
            modCount++;
            size = resultSize;
            return true;
        }
        return false;
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

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }
        return Math.abs(o.hashCode() % elements.length);
    }

}
