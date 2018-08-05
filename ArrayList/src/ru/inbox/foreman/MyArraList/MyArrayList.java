package ru.inbox.foreman.MyArraList;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] item;
    private int length;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        int DEF_CAPACITY = 10;
        this.item = (T[]) new Object[DEF_CAPACITY];
        length = 0;
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        this.item = (T[]) new Object[capacity];
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
        for (int i = 0; i < this.length; ++i) {
            if (item[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < length;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                T returnElement = item[this.currentIndex];
                this.currentIndex++;
                return returnElement;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.item, this.length);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length)
            // Make a new array of a's runtime type, but my contents:
            return (T1[]) Arrays.copyOf(item, length, a.getClass());
        System.arraycopy(item, 0, a, 0, length);
        if (a.length > length)
            a[length] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        if (checkCapacity()) {
            ensureCapacity(item.length * 2);
        }
        this.item[this.length] = t;
        this.length++;
        return true;
    }

    private void ensureCapacity(int capacity) {
        item = Arrays.copyOf(item, capacity);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.length; ++i) {
            if (this.item[i].equals(o)) {
                if (i < this.length - 1) {
                    System.arraycopy(this.item, i + 1, this.item, i, length - 1 - i);
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
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
//        if (c.size() == 0 || c == this) {
//            return false;
//        } else {
//            int cSize = c.size();
//            if (cSize + this.length < this.item.length) {
//                ensureCapacity((cSize + this.length) * 2);
//                Iterator iterator = c.iterator();
//                for (int i = length; iterator.hasNext(); i++) {
//                    item[i] = (T) iterator.next();
//                }
//                length += cSize;
//            }
//            return true;
//        }
        return addAll(this.length, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Objects.requireNonNull(c);
        if (c.size() == 0 || c == this) {
            return false;
        } else {
            int cSize = c.size();
            if (cSize + this.length >= this.item.length) {
                ensureCapacity((cSize + this.length) * 2);
            }
            if (index == this.length) {
                insertAll(index, c);
            } else {
                System.arraycopy(this.item, index, this.item, index + cSize, length - index);
                insertAll(index, c);
            }
            length += cSize;
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean result = false;
        Object[] resultItem = new Object[length];

        int j = 0;
        for (int i = 0; i < length; ++i) {
            if (!c.contains(item[i])) {
                resultItem[j] = item[i];
                j++;
            } else {
                result = true;
            }
        }
        item = (T[]) resultItem;
        length = j;
        return result;
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean result = false;
        Object[] resultItem = new Object[length];

        int j = 0;
        for (int i = 0; i < length; ++i) {
            if (c.contains(item[i])) {
                resultItem[j] = item[i];
                j++;
                result = true;
            }
        }
        item = (T[]) resultItem;
        length = j;
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.item = (T[]) new Object[0];
        length = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return this.item[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T returnValue = item[index];
        this.item[index] = element;
        return returnValue;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        if (checkCapacity()) {
            ensureCapacity(item.length * 2);
        }
        if (index != this.length) {
            System.arraycopy(this.item, index, this.item, index + 1, length + 1 - index);
        }
        item[index] = element;
        length++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T returnValue = item[index];
        if (index < this.length - 1) {
            System.arraycopy(this.item, index + 1, this.item, index, length - 1 - index);
        }
        length--;
        return returnValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i <= this.length; ++i) {
            if (item[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = this.length - 1; i >= 0; --i) {
            if (item[i].equals(o)) {
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
            private int currentIndex = index;
            private boolean hasNextStep = true;
            private int prevIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < length;

            }

            @Override
            public T next() {
                if (currentIndex >= length) {
                    throw new NoSuchElementException("No Such Element");
                }
                T returnElement = item[this.currentIndex];
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
                if (currentIndex < 0) {
                    throw new NoSuchElementException("No Such Element");
                }
                T returnElement = item[currentIndex];
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
                    System.arraycopy(item, currentIndex + 1, item, currentIndex, length - 1 - currentIndex);
                    length--;
                    currentIndex--;
                    hasNextStep = false;
                }
            }

            @Override
            public void set(T t) {
                if (hasNextStep) {

                    item[prevIndex] = t;
                }
            }

            @Override
            public void add(T t) {
                if (checkCapacity()) {
                    ensureCapacity(item.length * 2);
                }
                System.arraycopy(item, index, item, prevIndex + 1, length + 1 - prevIndex);
                item[currentIndex] = t;
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
            item[i] = (T) iterator.next();
        }
    }

    private boolean checkCapacity() {
        return item.length == length;
    }

}
