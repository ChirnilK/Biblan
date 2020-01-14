package com.company;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;
    private String description;
    private boolean available;

    public Book(String title, String author, String description, boolean available) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void bookInfo(){
        System.out.printf("Book title : %s, Author: %s, Description : %s, Available %s:\n", title, author,description,available);
    }

}