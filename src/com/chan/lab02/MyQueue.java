// Chan Wai Chi, 19060801d

package com.chan.lab02;

public class MyQueue<E> {
    private MyLinkedList<E> list;

    MyQueue() {
        list = new MyLinkedList<>();
    }

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        E element = list.getFirst();
        list.removeFirst();
        return element;
    }

    public E peek() {
        E element = list.getFirst();
        return element;
    }

    public int getSize() {
        return list.size;
    }

    public MyLinkedList<E> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Queue: " + list.toString();
    }
}