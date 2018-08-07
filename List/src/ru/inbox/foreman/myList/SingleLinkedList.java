package ru.inbox.foreman.myList;

import ru.inbox.foreman.exception.MyListIsEmptyException;

/**
 * Класс коллекции
 *
 * @param <T> parameter
 * @author SergeyNF
 */
public class SingleLinkedList<T> {
    private ListItem<T> head;
    private int size = 0;

    private int lastSearchIndex = 0;
    private ListItem<T> lastSearchItem = null;
    private ListItem<T> prevItem = null;


    public SingleLinkedList() {
    }

    /**
     * Добавление элементов в список
     *
     * @param data добавляемые данные
     */
    public void add(T data) {
        this.head = new ListItem<>(data, this.head);
        this.size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ListItem<T> p = this.head; p != null; p = p.getNext()) {
            if (p.getNext() == null) {
                sb.append(p.getData());
            } else {
                sb.append(p.getData()).append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Возвращает размер списка
     *
     * @return размер списка, целое число
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Получение значения первого (последнего добавленного в список) элемента
     *
     * @return данные типа <T>
     */
    public T get() {
        checkCollection();
        return this.head.getData();
    }

    /**
     * Получение данные из списка по индексу
     *
     * @param index индекс
     * @return данные типа <T>
     */
    public T getAtIndex(int index) {
        checkIndex(index);
        if (index == 0) {
            return get();
        }
        searchElementAtIndex(index);

        return this.lastSearchItem.getData();
    }

    /**
     * Установка значения по индексу
     *
     * @param index индекс
     * @param data  новое значение
     * @return старое значение, типа <T>
     */
    public T setAtIndex(int index, T data) {
        checkIndex(index);
        T oldData;
        if (index == 0) {
            oldData = head.getData();
            this.head.setData(data);
            return oldData;
        }
        searchElementAtIndex(index);
        oldData = this.lastSearchItem.getData();
        this.lastSearchItem.setData(data);
        return oldData;

    }

    /**
     * Удаление значения по индексу
     *
     * @param index индекс
     * @return удаленное значение
     */
    public T removeAtIndex(int index) {
        checkIndex(index);
        if (index == 0) {
            return remove();
        }
        searchElementAtIndex(index);
        this.prevItem.setNext(lastSearchItem.getNext());
        this.size--;
        T oldData = lastSearchItem.getData();
        this.lastSearchItem = null;
        this.lastSearchIndex = 0;
        return oldData;
    }

    /**
     * удаление первого элемента
     *
     * @return удаленный элемент
     */
    public T remove() {
        checkCollection();
        T oldData = head.getData();
        this.head = head.getNext();
        size--;
        return oldData;
    }

    /**
     * Вставка элемента по индексу
     *
     * @param index индекс
     * @param data  новые данные
     */
    public void addAtIndex(int index, T data) {
        checkIndex(index);
        if (index == 0) {
            add(data);
        }
        searchElementAtIndex(index);
        this.prevItem.setNext(new ListItem<>(data, this.lastSearchItem));
        lastSearchIndex = 0;
        lastSearchItem = head;
        size++;
    }

    /**
     * Удаление данных
     *
     * @param data удаляемые данные
     * @return true - данные найдены и удалены, в противном случае false
     */
    public boolean remove(T data) {
        checkCollection();
        if (this.head.getData().equals(data)) {
            this.head = head.getNext();
            size--;
            return true;
        }

        for (ListItem<T> p = this.head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(data)) {
                prev.setNext(p.getNext());
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Разворот коллекции
     */
    public void reverse() {
        checkCollection();
        if (size >= 2) {
            ListItem<T> newLastItem = new ListItem<>(head.getData());
            for (ListItem<T> p = this.head.getNext(), prev = newLastItem, next = head; next != null; prev = p, p = next) {
                if ((next = p.getNext()) == null) {
                    head = p;
                }
                p.setNext(prev);
            }
        }
    }

    /**
     * Копирование коллекции
     *
     * @return новая коллекция
     */
    public SingleLinkedList<T> copy() {
        checkCollection();
        SingleLinkedList<T> returnedCollection = new SingleLinkedList<>();
        returnedCollection.add(this.head.getData());
        for (ListItem<T> t = this.head.getNext(), r = returnedCollection.head; t != null; t = t.getNext(), r = r.getNext()) {
            r.setNext(new ListItem<>(t.getData()));
        }
        return returnedCollection;
    }

    /**
     * Поиск элемента и запоминание значения последнего поиска.
     * Поиск по индексу в сторону увеличения
     *
     * @param index требуемый индекс
     */
    private void searchElementAtIndex(int index) {
        ListItem<T> p = head.getNext();
        int startIndex = 1;
        if (index >= this.lastSearchIndex) {
            startIndex = this.lastSearchIndex;
            p = this.lastSearchItem != null ? this.lastSearchItem : head;
        }
        for (; startIndex != index; startIndex++) {
            this.prevItem = p;
            p = p.getNext();
        }
        this.lastSearchItem = p;
        this.lastSearchIndex = index;
    }

    /**
     * Проверка индекса
     *
     * @param index индекс
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    /**
     * Проверка коллекции на наличие элементов
     */
    private void checkCollection() {
        if (this.head == null) {
            throw new MyListIsEmptyException("Collection is empty");
        }
    }
}



