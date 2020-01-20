package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private String name;
    private String socialSecNumber;
    private int password;

    ArrayList<Book> borrowedBooks = new ArrayList<>();

    public User(String name, String socialSecNumber, int password) {
        this.name = name;
        this.socialSecNumber = socialSecNumber;
        this.password = password;
    }

    public int getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSocialSecNumber() {
        return socialSecNumber;
    }

    public abstract void userInfor();

    public void addLoan(Book book){
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }

    public boolean isThereLoan(){
        if(borrowedBooks.size()!=0){
            return true;
        }
        return false;
    }

    public void showBorrowedBooks() {
        for (Book borrowedbook : borrowedBooks) {
            borrowedbook.bookInfo();
            System.out.println("Duedate : " + borrowedbook.setDueDate().toLocalDate());
            }
        }

}
