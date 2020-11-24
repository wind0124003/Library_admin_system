package com.chan.lab02;

import java.util.Iterator;

public class AdminOperation implements BookOperation<Book>, UserOperation<Book> {
    /**
     * Add a book into the given list
     */
    @Override
    public MyLinkedList<Book> addBook(Book book, MyLinkedList<Book> list) {
        list.addLast(book);
        return list;
    }

    public MyLinkedList<Book> removeBook(String ISBN, MyLinkedList<Book> list) {
        MyLinkedList<Book> tempList = list;
        Book book = getBook(ISBN, tempList);
        int index = tempList.indexOf(book);
        System.out.println(index);
        tempList.remove(index);
        System.out.println(tempList);
        return tempList;
    }

    public MyLinkedList<Book> searchBook(String ISBN, String title, MyLinkedList<Book> list) {
        MyLinkedList<Book> tempList = list;
        if ((ISBN.isEmpty() == false) && (title.isEmpty() == false)) { // ISBN text field and title text field is not empty
            Searchable searchISBNTitle = new SearchISBNTitle();
            tempList = searchISBNTitle.search(ISBN, title, tempList);
        } else if ((ISBN.isEmpty() == false) && (title.isEmpty() == true)) { // ISBN text field is not empty
            Searchable searchISBN = new SearchISBN();
            tempList = searchISBN.search(ISBN, tempList);
        } else if ((ISBN.isEmpty() == true) && (title.isEmpty() == false)) { // title text field is not empty
            Searchable searchTitle = new SearchTitle();
            tempList = searchTitle.search(title, tempList);
        }

        return tempList;
    }

    public MyLinkedList<Book> saveBook(String ISBN, String newISBN, String newTitle,
                                       MyLinkedList<Book> list) {
        MyLinkedList<Book> tempList = list;
        Book book = getBook(ISBN, tempList);
        int index = tempList.indexOf(book);
        book.setTitle(newTitle);
        book.setISBN(newISBN);
        tempList.set(index, book);
        return tempList;
    }

    public MyLinkedList<Book> saveBook(Book book, MyLinkedList<Book> list){
        Book tempBook = getBook(book.getISBN(),list);
        int index = list.indexOf(tempBook);
        list.set(index,book);
        return list;
    }


    public Book getBook(String ISBN, MyLinkedList<Book> list) {
        Book findBook = null;
        for (Book book : list) {
            if (book.getISBN().equals(ISBN)) {
                findBook = book;
            }
        }
        return findBook;
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
            if (book.getISBN().equals(targetISBN)) {
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
    public String returnBook(Book book) {
        String name = "";
        MyQueue<String> queue = book.getReservedQueue();
        if (queue.getSize() == 0) {
            book.setAvailable(true);
        } else {
            book.setAvailable(false);
            name = queue.dequeue();
            book.setReservedQueue(queue);
        }
        return name;
    }

    @Override
    public Book reserveBook(Book book, String name) {
        Book newBook = book;
        MyQueue<String> newQueue = newBook.getReservedQueue();
        newQueue.enqueue(name);
        newBook.setReservedQueue(newQueue);
//        System.out.println(newQueue);
        return newBook;
    }

//
//    /**
//     * Return the string of waiting queue of the given book
//     */
//    public String returnWaitingQueue(Book book) {
//        StringBuilder strQueue = new StringBuilder("The waiting queue:\n");
//        Book tempBook = book;
//        MyQueue<String> newQueue = new MyQueue<>();
//        Book newBook = new Book();
//        newBook.setISBN(tempBook.getISBN());
//        newBook.setTitle(tempBook.getTitle());
//        newBook.setAvailable(tempBook.isAvailable());
//        // newBook.setReservedQueue(tempBook.getReservedQueue());
//
//        MyQueue<String> reservedQueue = tempBook.getReservedQueue();
//        if (reservedQueue == null) {
//            strQueue = new StringBuilder("No people waiting for this book.");
//        } else {
//            while (reservedQueue.getSize() != 0) {
//                String name = reservedQueue.dequeue();
//                newQueue.enqueue(name);
//                strQueue.append(name + "\n");
//            }
//        }
//        newBook.setReservedQueue(newQueue);
//        book = newBook;
//        return strQueue.toString();
//    }
}
