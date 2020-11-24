package com.chan.lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public interface Sortable {
    default MyLinkedList<Book> sortAscending(MyLinkedList<Book> list){
        String[] strings = linkedlistToArray(list);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(strings));
        Collections.sort(arrayList);
        return arrayToLinkedList(arrayList.toArray(strings),list);

    }

    default MyLinkedList<Book> sortDescending(MyLinkedList<Book> list) {
        String[] strings = linkedlistToArray(list);
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList(strings));
        Collections.sort(arrayList,Collections.reverseOrder());
        return arrayToLinkedList(arrayList.toArray(strings),list);
    }

    public String[] linkedlistToArray(MyLinkedList<Book> list);

    public MyLinkedList<Book> arrayToLinkedList(String[] strings,MyLinkedList<Book> list);
}
