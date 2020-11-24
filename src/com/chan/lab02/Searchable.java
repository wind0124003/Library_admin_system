package com.chan.lab02;

import java.util.regex.PatternSyntaxException;

public interface Searchable {
    default MyLinkedList<Book> search(String pat, MyLinkedList<Book> list) {
        MyLinkedList<Book> tempList = new MyLinkedList<>();
        Book book;
        boolean matchFound = false;

        for (Book value : list) {
            book = value;
            matchFound = setFindCondition(pat, book);
            if (matchFound) {
                tempList.addLast(book);
            }
        }
        return tempList;
    }

    default MyLinkedList<Book> search(String pat1, String pat2, MyLinkedList<Book> list) {
        MyLinkedList<Book> tempList = new MyLinkedList<>();
        Book book;
        boolean matchFound = false;

        for (Book value : list) {
            book = value;
            try {
                matchFound = setFindCondition(pat1, pat2, book);
            }
            catch (PatternSyntaxException e){
                System.out.println("PatternSyntaxException: ");
                System.out.println("Description: "+ e.getDescription());
                System.out.println("Index: "+ e.getIndex());
                System.out.println("Message: "+ e.getMessage());
                System.out.println("Pattern: "+ e.getPattern());
            }
            finally {
                if (matchFound) {
                    tempList.addLast(book);
                }
            }

        }
        return tempList;
    }

    boolean setFindCondition(String pat, Book book);

    boolean setFindCondition(String pat1, String pat2, Book book);
}
