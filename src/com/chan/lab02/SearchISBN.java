package com.chan.lab02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchISBN implements Searchable {
    @Override
    public boolean setFindCondition(String pat ,Book book) {
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(book.getISBN());
        return matcher.find();
    }

    @Override
    public boolean setFindCondition(String pat1, String pat2, Book book) {
        return false;
    }


}
