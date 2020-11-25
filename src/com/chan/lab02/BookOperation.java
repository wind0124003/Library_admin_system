// Chan Wai Chi, 19060801d

package com.chan.lab02;

public interface BookOperation<E> {
    public default MyLinkedList<E> addBook(E e, MyLinkedList<E> list){
        list.add(e);
        return list;
    }

    public MyLinkedList<E> removeBook(String string, MyLinkedList<E> list);
}
