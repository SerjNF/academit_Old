package ru.inbox.foreman.MyArraList;

import java.util.*;


public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int length;
    private static int DEFAULT_CAPACITY = 10;
    private boolean singOfChange = false;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        this.items = (T[]) new Object[capacity];
        length = 0;
    }


    @Override
    public int size() {
        return length;
    }

    private void setSingOfChange(boolean sing) {
        this.singOfChange = sing;

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
        setSingOfChange(false);
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (singOfChange) {
                    throw new ConcurrentModificationException("Сollection modified");
                }
                return currentIndex < length;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                T returnElement = items[this.currentIndex];
                this.currentIndex++;
                return returnElement;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.items, this.length);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length) {

            return (T1[]) Arrays.copyOf(items, length, a.getClass());
        }
        System.arraycopy(items, 0, a, 0, length);
        if (a.length > length)
            a[length] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        setSingOfChange(true);
        if (checkCapacity()) {
            ensureCapacity(items.length * 2);
        }
        this.items[this.length] = t;
        this.length++;
        return true;
    }

    public void ensureCapacity(int capacity) {
        setSingOfChange(true);
        int newCapacity = Math.max(capacity, this.items.length);
        items = Arrays.copyOf(items, newCapacity);
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        setSingOfChange(true);
        for (int i = 0; i < this.length; ++i) {
            T currentItem = this.items[i];
            if (currentItem != null && currentItem.equals(o)) {
                if (i < this.length - 1) {
                    System.arraycopy(this.items, i + 1, this.items, i, length - 1 - i);
                }
                length--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() > length) {
            return false;
        }
        for (Object cElement : c) {
            if (!contains(cElement)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        setSingOfChange(true);
        return addAll(this.length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Objects.requireNonNull(c);
        setSingOfChange(true);

        int cSize = c.size();
        if (cSize + this.length >= this.items.length) {
            ensureCapacity((cSize + this.length) * 2);
        }
        if (index == this.length) {
            insertAll(index, c);
        } else {
            System.arraycopy(this.items, index, this.items, index + cSize, length - index);
            insertAll(index, c);
        }
        length += cSize;

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        setSingOfChange(true);

        boolean result = false;
        Object[] resultItem = new Object[length];

        int j = 0;
        for (int i = 0; i < length; ++i) {
            if (!c.contains(items[i])) {
                resultItem[j] = items[i];
                j++;
            } else {
                result = true;
            }
        }
        items = (T[]) resultItem;
        length = j;
        return result;
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        setSingOfChange(true);
        boolean result = false;
        Object[] resultItem = new Object[length];

        int j = 0;
        for (int i = 0; i < length; ++i) {
            if (c.contains(items[i])) {
                resultItem[j] = items[i];
                j++;
                result = true;
            }
        }
        items = (T[]) resultItem;
        length = j;
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        setSingOfChange(true);
        this.items = (T[]) new Object[0];
        length = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return this.items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        setSingOfChange(true);
        T returnValue = items[index];
        this.items[index] = element;
        return returnValue;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        setSingOfChange(true);
        if (checkCapacity()) {
            ensureCapacity(items.length * 2);
        }
        if (index != this.length) {
            System.arraycopy(this.items, index, this.items, index + 1, length + 1 - index);
        }
        items[index] = element;
        length++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        setSingOfChange(true);
        T returnValue = items[index];
        if (index < this.length - 1) {
            System.arraycopy(this.items, index + 1, this.items, index, length - 1 - index);
        }
        length--;
        return returnValue;
    }

    @Override
    public int indexOf(Object o) {
        Objects.requireNonNull(o);
        for (int i = 0; i <= this.length; ++i) {
            T currentItem = items[i];
            if (currentItem != null && currentItem.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Objects.requireNonNull(o);

        for (int i = this.length - 1; i >= 0; --i) {
            T currentItem = items[i];
            if (currentItem != null && currentItem.equals(o)) {
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

        setSingOfChange(false);

        return new ListIterator<T>() {
            private int currentIndex = index;
            private boolean hasNextStep = true;
            private int prevIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < length;

            }

            @Override
            public T next() {
                if (singOfChange) {
                    throw new ConcurrentModificationException("Сollection modified");
                }

                if (currentIndex >= length) {
                    throw new NoSuchElementException("No Such Element");
                }

                T returnElement = items[this.currentIndex];
                prevIndex = currentIndex;
                currentIndex++;
                hasNextStep = true;
                return returnElement;
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public T previous() {
                if (singOfChange) {
                    throw new ConcurrentModificationException("Сollection modified");
                }

                if (currentIndex < 0) {
                    throw new NoSuchElementException("No Such Element");
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
                    setSingOfChange(true);
                    System.arraycopy(items, currentIndex + 1, items, currentIndex, length - 1 - currentIndex);
                    length--;
                    currentIndex--;
                    hasNextStep = false;
                }
            }

            @Override
            public void set(T t) {
                if (hasNextStep) {
                    setSingOfChange(true);
                    items[prevIndex] = t;
                }
            }

            @Override
            public void add(T t) {
                if (checkCapacity()) {
                    ensureCapacity(items.length * 2);
                }

                System.arraycopy(items, index, items, prevIndex + 1, length + 1 - prevIndex);
                items[currentIndex] = t;
                currentIndex++;
                length++;
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }


    private void checkIndexForAdd(int index) {
        if (index > this.length) {
            throw new ArrayIndexOutOfBoundsException(printThrowOutOfBoundException());
        }
    }

    private String printThrowOutOfBoundException() {
        return "Выход за пределы массива";
    }

    private void checkIndex(int index) {
        if (index >= this.length) {
            throw new ArrayIndexOutOfBoundsException(printThrowOutOfBoundException());
        }
    }

    @SuppressWarnings("unchecked")
    private void insertAll(int index, Collection<? extends T> c) {
        Iterator iterator = c.iterator();
        for (int i = index; iterator.hasNext(); i++) {
            items[i] = (T) iterator.next();
        }
    }

    private boolean checkCapacity() {
        return items.length == length;
    }

}
