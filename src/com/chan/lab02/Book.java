// Chan Wai Chi, 19060801d

package com.chan.lab02;

public class Book {
    private String title;
    private String ISBN;
    private boolean available;
    private MyQueue<String> reservedQueue;

    /** Constructor */
    public Book() {
        title = "";
        ISBN = "";
        available = true;
        reservedQueue = new MyQueue<>();
    }

    /** Constructor with title, isbn, available */
    public Book(String ISBN, String title, boolean available, MyQueue<String> waitingQueue) {
        this.title = title;
        this.ISBN = ISBN;
        this.available = available;
        this.reservedQueue = waitingQueue;
    }

    /** Constructor with title, ISBN
     * @param ISBN
     * @param title*/
    public Book(String ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
        this.reservedQueue = new MyQueue<>();
        this.available = true;
    }

    /** Get the book title */
    public String getTitle() {
        return title;
    }

    /** Set the book title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Get the ISBN of the book */
    public String getISBN() {
        return ISBN;
    }

    /** Set the ISBN of the book */
    public void setISBN(String isbn) {
        this.ISBN = isbn;
    }

    /** Get the status of available */
    public boolean isAvailable() {
        return available;
    }

    /** Set the status of available */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /** Get the reserved queue of the book */
    public MyQueue<String> getReservedQueue () {
        return reservedQueue;
    }

    /** Set the reserved queue of the book */
    public void setReservedQueue(MyQueue<String> queue) {
        reservedQueue = queue;
    }

}
