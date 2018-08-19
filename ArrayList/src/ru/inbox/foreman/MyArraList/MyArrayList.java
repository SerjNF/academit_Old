package ru.inbox.foreman.MyArraList;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private T[] items;
    private int length;
    private int modCount = 0;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        items = (T[]) new Object[capacity];
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < length;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Collection modified");
                }
                if (currentIndex >= length) {
                    throw new NoSuchElementException(noSuchElement());
                }
                T returnElement = items[currentIndex];
                currentIndex++;
                return returnElement;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length) {
            return (T1[]) Arrays.copyOf(items, length, a.getClass());
        }
        System.arraycopy(items, 0, a, 0, length);
        if (a.length > length) {
            a[length] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        ensureCapacity(length + 1);
        items[length] = t;
        length++;
        modCount++;
        return true;
    }

    public void ensureCapacity(int minCapacity) {
        int itemsLength = items.length;
        if (minCapacity > itemsLength) {
            int newLength = minCapacity <= itemsLength * 2 ? itemsLength * 2 : minCapacity;
            items = Arrays.copyOf(items, newLength);
        }
    }

    @SuppressWarnings("unchecked")
    public void trimToSize() {
        if (length < items.length) {
            items = (length == 0) ? (T[]) new Object[]{} : Arrays.copyOf(items, length);
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; ++i) {
            if (Objects.equals(items[i], o)) {
                if (i < length - 1) {
                    System.arraycopy(items, i + 1, items, i, length - 1 - i);
                }
                length--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        for (Object cElement : c) {
            if (!contains(cElement)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Objects.requireNonNull(c);
        int cSize = c.size();
        ensureCapacity(cSize + length);
        if (index == length) {
            insertAll(index, c);
        } else {
            System.arraycopy(items, index, items, index + cSize, length - index);
            insertAll(index, c);
        }
        length += cSize;
        modCount++;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] resultItems = new Object[length];

        int j = 0;
        for (int i = 0; i < length; ++i) {
            if (!c.contains(items[i])) {
                resultItems[j] = items[i];
                j++;
            }
        }
        if (j != length) {
            modCount++;
            length = j;
            items = (T[]) resultItems;
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] resultItems = new Object[length];

        int j = 0;
        for (int i = 0; i < length; ++i) {
            if (c.contains(this.items[i])) {
                resultItems[j] = items[i];
                j++;
            }
        }
        if (j != length) {
            modCount++;
            length = j;
            items = (T[]) resultItems;
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        items = (T[]) new Object[0];
        length = 0;
        modCount++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T returnValue = items[index];
        items[index] = element;
        return returnValue;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity(length + 1);
        if (index != length) {
            System.arraycopy(items, index, items, index + 1, length + 1 - index);
        }
        items[index] = element;
        length++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T returnValue = items[index];
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - 1 - index);
        }
        length--;
        modCount--;
        return returnValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; ++i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; --i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Out of range");
        }
        return new ListIterator<T>() {
            private int expectedModCount = modCount;
            private int currentIndex = index;
            private boolean hasNextStep = true;
            private int prevIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < length;
            }

            @Override
            public T next() {
                checkModification();
                if (currentIndex >= length) {
                    throw new NoSuchElementException(noSuchElement());
                }
                T returnElement = items[this.currentIndex];
                prevIndex = currentIndex;
                currentIndex++;
                hasNextStep = true;
                return returnElement;
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex >= 0;
            }

            @Override
            public T previous() {
                checkModification();
                if (currentIndex < 0) {
                    throw new NoSuchElementException(noSuchElement());
                }
                T returnElement = items[currentIndex];
                prevIndex = currentIndex;
                currentIndex--;
                hasNextStep = true;
                return returnElement;
            }

            @Override
            public int nextIndex() {
                return hasNext() ? currentIndex : length;
            }

            @Override
            public int previousIndex() {
                return hasPrevious() ? currentIndex - 1 : -1;
            }

            @Override
            public void remove() {
                if (hasNextStep) {
                    System.arraycopy(items, currentIndex + 1, items, currentIndex, length - 1 - currentIndex);
                    length--;
                    hasNextStep = false;
                }
            }

            @Override
            public void set(T t) {
                if (hasNextStep) {
                    items[prevIndex] = t;
                }
            }

            @Override
            public void add(T t) {
                ensureCapacity(length + 1);
                System.arraycopy(items, prevIndex, items, prevIndex + 1, length - prevIndex);
                items[prevIndex] = t;
                length++;
            }

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Collection modified");
                }
            }
        };
    }

    private String noSuchElement() {
        return "No Such Element";
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void checkIndexForAdd(int index) {
        if (index > length) {
            throw new ArrayIndexOutOfBoundsException(printThrowOutOfBoundException());
        }
    }

    private String printThrowOutOfBoundException() {
        return "Выход за пределы массива";
    }

    private void checkIndex(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException(printThrowOutOfBoundException());
        }
    }

    private void insertAll(int index, Collection<? extends T> c) {
        Iterator<? extends T> iterator = c.iterator();
        for (int i = index; iterator.hasNext(); i++) {
            items[i] = iterator.next();
        }
    }
}
