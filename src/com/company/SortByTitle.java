package com.company;

import java.util.Comparator;

public class SortByTitle implements Comparator<Book> {
    public int compare(Book a, Book b) {
        return a.getTitle().compareTo(b.getTitle());
}
}
