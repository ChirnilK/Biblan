package com.company;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

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


    public void returnBook(User user) {
        System.out.println("");
        System.out.println("Input the index of the book which you want to return");
        Scanner scanner = new Scanner(System.in);
        try {
            int returnIndex = Integer.parseInt(scanner.nextLine());
            Book book = borrowedBooks.get(returnIndex - 1);
            borrowedBooks.remove(book);
            book.setAvailable(true);
            System.out.printf("%s successfully returned the book : %s", user.getName(), book.getTitle());
        }catch(Exception e){
            System.out.println("Choose index number. Try again");
        }
    }

    public boolean isThereLoan(){
        if(borrowedBooks.size()!=0){
            return true;
        }
        return false;
    }


    //show user's all loan and also return true for there is loan. false for no loan.
    public boolean showUserLoans(User user) {
        System.out.printf("Information of %s's loan :\n", user.getName());
        boolean loan = user.isThereLoan();
        if (loan) {
            user.showBorrowedBooks();
            return true;
        }
        System.out.println("You haven't borrowed any book.");
        return false;
    }

    //find a book by book title(partial string). return Book/null
    public Book findBookByTitle(String name, ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(name.toLowerCase())) {
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find any book. Try it again");
        return null;
    }


    //find a book by author(partial string). return Book/null
    public Book findBookByAuthor(String name, ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if (book.getAuthor().toLowerCase().contains(name.toLowerCase())) {
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find any book. Try it again");
        return null;
    }


    //show all books in bookList with index.
    public void showAllBooks(ArrayList<Book> bookList) {
        FileUtils.saveObject("books.ser", bookList);
        ArrayList<Book> list = (ArrayList) FileUtils.loadObject("books.ser");
        int howMany = list.size();
        for (int i = 0; i<howMany; i++){
            System.out.printf("[%s]", i+1);
            list.get(i).bookInfo();
        }
    }

    //if user has overdue book then get remainder.
    public void isOverdue(User user){
        LocalDateTime now = LocalDateTime.now();
        int howMany = user.borrowedBooks.size();
        if (howMany!=0) {
            System.out.println("=====INFORMATION====");
            for (Book borrowedbook : borrowedBooks) {
                if (now.isAfter(borrowedbook.getDueDate())) {
                    System.out.println("Overdue book : "+ borrowedbook.getTitle()+",  Duedate : "+borrowedbook.getDueDate().toLocalDate());
                }
            }
        }
    }


    //show only available books in list
    public void onlyAvailableBooks(ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if ((book.isAvailable())) {
                book.bookInfo();
            }
        }
    }

    //show user's all loan, book title and due date
    public void showBorrowedBooks() {
        int howMany = borrowedBooks.size();
        for (int i = 0; i < howMany; i++) {
            System.out.printf("[%s]", i + 1);
            System.out.println("Book Title : " + borrowedBooks.get(i).getTitle() + ",    Duedate : " + borrowedBooks.get(i).getDueDate().toLocalDate());
        }
    }
}
