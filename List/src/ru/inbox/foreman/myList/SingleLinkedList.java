package ru.inbox.foreman.myList;

import ru.inbox.foreman.exception.MyListIsEmptyException;

import java.util.Objects;

/**
 * Класс коллекции
 *
 * @param <T> parameter
 * @author SergeyNF
 */
public class SingleLinkedList<T> {
    private ListItem<T> head;
    private int size = 0;

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

    @Override
    @SuppressWarnings({"unchecked", "EqualsWhichDoesntCheckParameterClass"})
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        SingleLinkedList<T> ob = (SingleLinkedList<T>) obj;

        if (size != ob.size) {
            return false;
        }
        for (ListItem<T> p = this.head, o = ob.head; p != null; p = p.getNext(), o = o.getNext()) {
            if (!Objects.equals(p.getData(), o.getData())) {
                return false;
            }
        }
        return true;
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
        return this.getElementAtIndex(index).getData();
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
        ListItem<T> elementAtIndex = getElementAtIndex(index);
        T oldData = elementAtIndex.getData();
        elementAtIndex.setData(data);
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
        ListItem<T> elementAtPrevIndex = getElementAtIndex(index - 1);
        T oldData = elementAtPrevIndex.getNext().getData();
        elementAtPrevIndex.setNext(elementAtPrevIndex.getNext().getNext());
        this.size--;
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if (index == 0) {
            add(data);
        } else {
            ListItem<T> elementAtPrevIndex = getElementAtIndex(index - 1);
            elementAtPrevIndex.setNext(new ListItem<>(data, elementAtPrevIndex.getNext()));
            size++;
        }
    }

    /**
     * Удаление данных
     *
     * @param data удаляемые данные
     * @return true - данные найдены и удалены, в противном случае false
     */
    public boolean remove(T data) {
        if (head == null) {
            return false;
        }
        if (Objects.equals(this.head.getData(), data)) {
            this.head = head.getNext();
            size--;
            return true;
        }
        for (ListItem<T> p = this.head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(p.getData(), data)) {
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
        SingleLinkedList<T> returnedCollection = new SingleLinkedList<>();
        if (this.head == null) {
            return returnedCollection;
        }
        returnedCollection.add(this.head.getData());
        for (ListItem<T> t = this.head.getNext(), r = returnedCollection.head; t != null; t = t.getNext(), r = r.getNext()) {
            r.setNext(new ListItem<>(t.getData()));
        }
        returnedCollection.size = size;
        return returnedCollection;
    }

    /**
     * Поиск элемента и запоминание значения последнего поиска.
     * Поиск по индексу в сторону увеличения
     *
     * @param index требуемый индекс
     */
    private ListItem<T> getElementAtIndex(int index) {
        //  checkCollection();
        ListItem<T> p = this.head;
        int startIndex = 0;

        for (; startIndex != index; startIndex++) {
            p = p.getNext();
        }
        return p;
    }

    /**
     * Проверка индекса
     *
     * @param index индекс
     */
    private void checkIndex(int index) {
        checkCollection();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    /**
     * Проверка коллекции на наличие элементов
     */
    private void checkCollection() {
        if (head == null) {
            throw new MyListIsEmptyException("Collection is empty");
        }
    }
}



