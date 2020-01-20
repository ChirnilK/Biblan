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

    public LocalDateTime setDueDate() {
        Timestamp timeStamp = Timestamp.valueOf(LocalDateTime.now());
        //dueDate = timeStamp.toLocalDateTime().plusDays(14);      //for two weeks
        dueDate = timeStamp.toLocalDateTime().plusSeconds(1);   //for one second
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return "The Book description {"+ description + "}";
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
