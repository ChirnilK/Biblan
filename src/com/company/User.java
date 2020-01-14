package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
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

    public String getSocialSecNumber() {
        return socialSecNumber;
    }

    public abstract void userInfor();

    public void borrowBook(Book book){
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public Boolean returnBook(String bookName) {
        for (Book book : borrowedBooks) {
            if (book.getTitle().equals(bookName)) {
                borrowedBooks.remove(book);
                book.setAvailable(true);
                return true;
            } else {
                System.out.println("We don't have such book");
            }
        }
        return false;
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.size() == 0) {
        } else {
            for (Book borrowedbook : borrowedBooks) {
                System.out.println(borrowedbook);
            }
        }
    }

}