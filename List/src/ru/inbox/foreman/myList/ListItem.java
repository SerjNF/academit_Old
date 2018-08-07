package ru.inbox.foreman.myList;

/**
 * Класс элементов коллекции
 *
 * @param <T> parameter
 * @author SergeyNF
 */
class ListItem<T> {
    private T data;
    private ListItem<T> next;

    ListItem(T data) {
        this.data = data;
    }

    ListItem(T data, ListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    ListItem<T> getNext() {
        return this.next;
    }

    void setNext(ListItem<T> next) {
        this.next = next;
    }

    T getData() {
        return this.data;
    }

    void setData(T data) {
        this.data = data;
    }
}


