// Chan Wai Chi, 19060801d

package com.chan.lab02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchISBNTitle implements Searchable {
    @Override
    public boolean setFindCondition(String pat, Book book) {

        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(book.getTitle());
        return matcher.find();
    }

    public boolean setFindCondition(String pat1, String pat2, Book book) {
        boolean matchFound = false;
        Pattern pattern = Pattern.compile(pat1);
        Matcher matcher = pattern.matcher(book.getISBN());
        if (matcher.find())
            matchFound = true;

        if (matchFound == false) {
            pattern = Pattern.compile(pat2);
            matcher = pattern.matcher(book.getTitle());
            if (matcher.find())
                matchFound = true;
        }
        return matchFound;
    }

}
