package com.company;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Book implements Serializable {

    private String title;
    private String author;
    private String description;
    private boolean available;
    private LocalDateTime dueDate;


    public Book(String title, String author, String description, boolean available) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.available = available;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate() {
        Timestamp timeStamp = Timestamp.valueOf(LocalDateTime.now());
        dueDate = timeStamp.toLocalDateTime().plusDays(14);      //for two weeks
        //dueDate = timeStamp.toLocalDateTime().plusSeconds(5);   //for 5 second to check if it works
    }

    public String getTitle() {
        return "Book title : "+title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return "Book description : \n{"+ description + "}";
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void bookInfo() {
        System.out.printf("Book title : %s, Author: %s, Available: %s \n", title, author, available);
    }
}
