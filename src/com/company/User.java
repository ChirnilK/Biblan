package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String socialSecNumber;
    ArrayList<Book> borrowedBooks = new ArrayList<>();

    public User(String name, String socialSecNumber) {
        this.name = name;
        this.socialSecNumber = socialSecNumber;
    }

    public String getName() {
        return name;
    }
}
