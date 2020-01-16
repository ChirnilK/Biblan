package com.company;

import java.util.Comparator;

public class SortByAuthor implements Comparator<Book> {
    public int compare(Book a, Book b) {

        return a.getAuthor().compareTo(b.getAuthor());
    }
}
