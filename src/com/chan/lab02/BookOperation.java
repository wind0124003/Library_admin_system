package com.chan.lab02;

public interface BookOperation<E> {
    public MyLinkedList<E> addBook(E e, MyLinkedList<E> list);

    public MyLinkedList<E> removeBook(E e, MyLinkedList<E> list);
}
