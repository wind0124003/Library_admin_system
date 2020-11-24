package com.chan.lab02;

import java.util.Iterator;

public class AdminOperation implements BookOperation<Book>, UserOperation<Book>{
    /**
     * Add a book into the given list
     */
    @Override
    public MyLinkedList<Book> addBook(Book book, MyLinkedList<Book> list) {
        list.addLast(book);
        return list;
    }

    /**
     * Remove a book from the given list
     */
    @Override
    public MyLinkedList<Book> removeBook(Book book, MyLinkedList<Book> list) {
        MyLinkedList<Book> tempList = list; // 23/11 TODO: remove the book function
        return tempList;
    }

    public MyLinkedList<Book> removeBook(String ISBN, MyLinkedList<Book> list) {
        MyLinkedList<Book> tempList = list; //23/11 TODO: remove the book function
        return tempList;
    }

    public MyLinkedList<Book> searchBook(String ISBN, String title, MyLinkedList<Book> list) {
        return list; //23/11 TODO: searchBook
    }

    // 23/11 TODO: saveBook
    public MyLinkedList<Book> saveBook(String ISBN, String newISBN, String newTitle, MyLinkedList<Book> list) {
        MyLinkedList<Book> tempList = list;
        return tempList;
    }

    //23/11 TODO: getBook
    public Book getBook(String ISBN, MyLinkedList<Book> list) {
        return new Book(ISBN, "");
    }

    /**
     * Sort the given linked list by book title, ascending order
     */
    public MyLinkedList<Book> sortTitleAscending(MyLinkedList<Book> list) {
        Sortable sortTitle = new SortTitle();
        MyLinkedList<Book> tempList = sortTitle.sortAscending(list);
        return tempList;
    }

    /**
     * Sort the given linked list by book title, descending order
     */
    public MyLinkedList<Book> sortTitleDescending(MyLinkedList<Book> list) {
        Sortable sortTitle = new SortTitle();
        MyLinkedList<Book> tempList = sortTitle.sortDescending(list);
        return tempList;
    }

    /**
     * Sort the given linked list by book ISBN, ascending order
     */
    public MyLinkedList<Book> sortISBNAscending(MyLinkedList<Book> list) {
       Sortable sortISBN = new SortISBN();
       MyLinkedList<Book> tempList = sortISBN.sortAscending(list);
       return tempList;
    }

    /**
     * Sort the given linked list by book ISBN, descending order
     */
    public MyLinkedList<Book> sortISBNDescending(MyLinkedList<Book> list) {
        Sortable sortISBN = new SortISBN();
        MyLinkedList<Book> tempList = sortISBN.sortDescending(list);
        return tempList;
    }

    /**
     * Check if the linked list contains the given ISBN
     */
    public boolean checkIfContains(String targetISBN, MyLinkedList<Book> list) {
        boolean ifFind = false;
        Iterator<Book> iterator = list.iterator();
        Book book;
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book.getISBN().contains(targetISBN)) {
                ifFind = true;
                break;
            }
        }
        return ifFind;
    }

    /**
     * Check if the linked list contains the given book
     */
    public boolean checkIfContains(Book targetBook, MyLinkedList<Book> list) {
        boolean ifFind = false;
        Iterator<Book> iterator = list.iterator();
        Book sourceBook;
        while (iterator.hasNext()) {
            sourceBook = iterator.next();
            if (sourceBook.getISBN().contains(targetBook.getISBN())) {
                ifFind = true;
                break;
            }
        }
        return ifFind;
    }

    /**
     * Return the string of error (database contains the book)
     */
    public String returnStrContainError(Book book) {
        StringBuilder strErrorMessage = new StringBuilder("Error: ");
        strErrorMessage.append("the database already contains this book\n");
        strErrorMessage.append("(ISBN: " + book.getISBN() + ")");
        return strErrorMessage.toString();
    }

    public String returnStrContainError(String isbn) {
        StringBuilder strErrorMessage = new StringBuilder("Error: ");
        strErrorMessage.append("the database already contains this book\n");
        strErrorMessage.append("(ISBN: " + isbn + ")");
        return strErrorMessage.toString();
    }

    /**
     * Return the string of error (database does not contains the book)
     */
    public String returnStrNotContainError(Book book) {
        StringBuilder strErrorMessage = new StringBuilder("Error: ");
        strErrorMessage.append("the database does not contain this book ");
        strErrorMessage.append("(ISBN: " + book.getISBN() + ")");
        return strErrorMessage.toString();
    }

    /**
     * Return the string of error (database does not contain the book)
     */
    public String returnStrNotContainError(String ISBN) {
        StringBuilder strErrorMessage = new StringBuilder("Error: ");
        strErrorMessage.append("the database does not contain this book ");
        strErrorMessage.append("(ISBN: " + ISBN + ")");
        return strErrorMessage.toString();
    }

    /**
     * Return the string of book information
     */
    public String returnStrBookInfo(Book book) {
        StringBuilder strBookInfo = new StringBuilder();
        strBookInfo.append("ISBN : " + book.getISBN());
        strBookInfo.append("\nTitle : " + book.getTitle());
        strBookInfo.append("\nAvailable : " + book.isAvailable());
        return strBookInfo.toString();
    }

    /**
     * Return the book after borrowing this book
     */
    @Override
    public Book borrowBook(Book book) {
        book.setAvailable(false);
        return book;
    }

    /* Check if  the queue is null,
     * If yes, set available to true.
     * If no, set available to false, and dequeue it once.
     * Get the name of dequeued item.
     */
    @Override
    public Book returnBook(Book book) {
        MyQueue<String> queue = book.getReservedQueue();
        if (queue == null) {
            book.setAvailable(true);
        } else {
            book.setAvailable(false);
            queue.dequeue();
            book.setReservedQueue(queue);
        }
        return book;
    }


    @Override
    public Book reserveBook(Book book) {
        return null;
    }

    /**
     * Return the string of waiting queue of the given book
     */
    public String returnWaitingQueue(Book book) {
        StringBuilder strQueue = new StringBuilder("The waiting queue:\n");
        MyQueue<String> queue = book.getReservedQueue();
        if (queue == null) {
            strQueue = new StringBuilder("No people waiting for this book.");
        } else {
            while (queue.getSize() != 0) {
                strQueue.append(queue.dequeue() + "\n");
            }
        }
        return strQueue.toString();
    }


}
