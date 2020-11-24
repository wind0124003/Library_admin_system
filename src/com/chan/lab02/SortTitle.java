package com.chan.lab02;

import java.util.Iterator;

public class SortTitle implements Sortable {
    @Override
    public String[] linkedlistToArray(MyLinkedList<Book> list) {
        String[] titles = new String[list.size];
        Book book;
        Iterator<Book> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            book = iterator.next();
            titles[i] = book.getTitle();
            i++;
        }
        return titles;
    }

    @Override
    public MyLinkedList<Book> arrayToLinkedList(String[] strings, MyLinkedList<Book> list) {
        MyLinkedList<Book> list1 = new MyLinkedList<>();
        Book book;
        for (int j = 0; j < list.size; j++) {
            Iterator<Book> iterator1 = list.iterator();
            while (iterator1.hasNext()) {
                book = iterator1.next();
                if (strings[j].contains(book.getTitle())) {
                    book.setTitle(strings[j]);
                    list1.addLast(book);
                }
            }
        }
        return list1;
    }
}
