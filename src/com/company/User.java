package com.company;

import java.io.Serializable;
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

    //search book by book title or author. The loggin user can borrow the book after searching.
    public void searchBook(User user, ArrayList<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search by book title / author name?  Enter t / a ");
        String search = scanner.nextLine();
        if (search.equals("t")) {
            System.out.println("What is the book title?");
            String searchTitle = scanner.nextLine();
            Book book = findBookByTitle(searchTitle, bookList);
            if (book != null) {
                System.out.println("Would you like to borrow the book?  y/n");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    //customerClass.borrowBook(user, book);
                } else if (answer.equals("n")) {
                    System.out.println("See you!");
                }
            }
        } else if (search.equals("a")) {
            System.out.println("What is the Author name?");
            String searchAuthor = scanner.nextLine();
            Book book = findBookByAuthor(searchAuthor, bookList);
            if (book != null) {
                System.out.println("Would you like to borrow the book?  y/n");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    //borrowBook(user, book);
                } else if (answer.equals("n")) {
                    System.out.println("See you!");
                }
            }
        } else {
            System.out.println("Enter 't' or 'a'");
        }
    }


    //show user's all loan. return true for there is loan. false for no loan.
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

    //find a book by book title(partial string ok). return Book
    public Book findBookByTitle(String name, ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(name.toLowerCase())) {
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find anything. Try it again");
        return null;
    }


    //find a book by author(partial string ok). return Book.
    public Book findBookByAuthor(String name, ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if (book.getAuthor().toLowerCase().contains(name.toLowerCase())) {
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find any");
        return null;
    }



    public void bookDescription(Book book){
        System.out.println(book.getDescription());
    }


    //show all books in list. first save the list to a file named "books.ser" then load the file
    public void showAllBooks(ArrayList<Book> bookList) {
        FileUtils.saveObject("books.ser", bookList);
        ArrayList<Book> list = (ArrayList) FileUtils.loadObject("books.ser");
        for (Book book : list) {
            book.bookInfo();
        }
    }


 /*   public void isOverdue(){
        LocalDateTime now = LocalDateTime.now();
        for (Book borrowedbook : borrowedBooks) {
            if(now.isAfter(borrowedbook.getDueDate())){
                System.out.println("=====REMINDER====");
                System.out.printf("The book : %s is overdue!! ", borrowedbook);
            }
        }
    }*/

    public void showBorrowedBooks() {
        for (Book borrowedbook : borrowedBooks) {
            System.out.println("Book Title : "+ borrowedbook.getTitle()+ ",    Duedate : "+borrowedbook.getDueDate().toLocalDate());
            }
        }

}
