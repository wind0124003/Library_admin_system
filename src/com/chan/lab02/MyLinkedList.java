// Chan Wai Chi, 19060801d

package com.chan.lab02;
//import MyLinkedList.Node;

public class MyLinkedList<E> implements MyList<E> {
    protected Node<E> head, tail;
    protected int size = 0; // Number of elements in the list

    /**
     * Create an empty list
     */
    public MyLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Create a list from an array of objects
     */
    public MyLinkedList(E[] objects) {
        for (E element : objects) {
            addLast(element);
        }
    }

    /**
     * Return the head element in the list
     */
    public E getFirst() {
        return head.element;
    }

    /**
     * Return the last element in the list
     */
    public E getLast() {
        return tail.element;
    }

    /**
     * Add an element to the beginning of the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        if (tail == null) {
            tail = head;
        }
    }

    /**
     * Add an element to the end of the list
     */
    public void addLast(E e) {
        if (tail == null) {
            head = tail = new Node<>(e);
        } else {
            tail.next = new Node<>(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    /** Add a new element at the specified index in this list.
     *  The index of the head element is 0
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            // Find the location
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            // Add the node at the specified index
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    /**
     * Remove the head node and
     * return the object that is contained in the removed node.
     */
    public E removeFirst() {
        if (size == 0)
            return null;
        else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /**
     * Remove the last node
     * and return the object that is contained in the removed node.
     */
    public E removeLast() {
        if (size == 0) return null;
        else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++)
                current = current.next;
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    @Override
    /** Remove the element at the specified position in this
     *  list. Return the element that was removed from the list. */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    @Override
    /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    @Override
    /** Clear the list */
    public void clear() {
        Node<E> current = head;
        while(current != null) {
            current = current.next;
            removeFirst();
        }
    }

    @Override
    /** Return true if this list contains the element e */
    public boolean contains(Object e) {
        Node<E> current = head;
        boolean ifFind = false;

        while (current != null) {
            if (e.equals(current.element)) { // the list contains the elemetn e
                ifFind = true;    // set ifFind to true and break the loop
                break;
            } else {
                current = current.next;    // go to next node
            }
        }
        return ifFind;
    }

    @Override
    /** Return the element at the specified index */
    public E get(int index) {
        Node<E> current = head;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        return current.next.element;
    }

    @Override
    /** Return the index of the first matching element in
     *  this list. Return -1 if no match. */
    public int indexOf(Object e) {
        int index = -1;
        int firstIndex = -1;
        Node<E> current = head;
        if (e.equals(head.element)) {
            firstIndex = 0;
        } else {
            while (current != null) {
                if (e.equals(current.element)) {
                    firstIndex = index + 1;
                    break;
                } else {
                    current = current.next;
                    index++;
                }
            }
        }
        return firstIndex;
    }

    @Override
    /** Return the index of the last matching element in this list.
     *  Return -1 if no match. */
    public int lastIndexOf(E e) {
        int lastIndex = -2;
        int index = -1;
        Node<E> current = head;

        if (e.equals(tail.element)) {
            lastIndex = size - 2;
        } else {
            while (current != null) {
                if (e.equals(current.element)) {    // Compare the element
                    lastIndex = index;    // Update the index of the last matching elements
                    current = current.next;
                    index++;
                } else {
                    current = current.next;    // Goto next node
                    index++;
                }
            }
        }
        return lastIndex+1;
    }

    @Override
    /** Replace the element at the specified position
     *  in this list with the specified element. */
    public E set(int index, E e) {
        Node<E> current = head;
        E replacedElement;
        // Left as an exercise
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        replacedElement = current.element;
        current.element = e;
        return replacedElement;
    }

    @Override
    /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements java.util.Iterator<E> {
        private Node<E> current = head; // Current node
        private int index = -1; // initial index before head

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            index++;
            current = current.next;
            return e;
        }

        @Override
        // remove the last element returned by the iterator
        public void remove() {
            MyLinkedList.this.remove(index);
        }
    }


    protected static class Node<E> {
        E element;
        Node<E> next;

        /* Constructor with specified element*/
        public Node(E e) {
            element = e;
            next = null;
        }

        /* Constructor */
        public Node() {
            element = null;
            next = null;
        }
    }

    @Override
    /** Return the number of elements in this list */
    public int size() {
        return size;
    }
}