package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Customer extends User {
    public Customer(String name, String socialSecNumber, int password) {
        super(name, socialSecNumber, password);
    }

    public void customer(ArrayList<Book> bookList, ArrayList<User> userList, User user) {
        boolean cus = true;
        while (cus) {
            customerMenu();
            Scanner cust = new Scanner(System.in);
            int userChoice = Integer.parseInt(cust.nextLine());
            switch (userChoice) {
                case 1:    //Borrow book
                    showAllBooks(bookList);
                    System.out.println("Which book would you like to borrow?");
                    String borrowB = cust.nextLine();
                    Book bok = findBookByTitle(borrowB, bookList);
                    if (bok != null) {
                        borrowBook(user, bok);
                    }
                    break;

                case 2:   //Return book
                    boolean loan = showUserLoans(user);
                    if (loan) {
                        System.out.println("Which book would you like to return?");
                        String returnItem = cust.nextLine();
                        Book book = findBookByTitle(returnItem, bookList);
                        if (book != null) {
                            user.returnBook(book);
                            System.out.printf("%s successfully returned the book : %s", user.getName(), book.getTitle());
                        }
                    }
                    break;

                case 3:  //Show user's borrowed items
                    showUserLoans(user);
                    break;

                case 4:  //Show all available books
                    onlyAvailableBooks(bookList);
                    break;

                case 5:  //Show all library books
                    showAllBooks(bookList);
                    System.out.println("");
                    bookInforMenu();
                    int bookInfoChoice = Integer.parseInt(cust.nextLine());
                    if (bookInfoChoice == 1) {
                        Collections.sort(bookList, new SortByTitle());
                        showAllBooks(bookList);
                        //break;
                    } else if (bookInfoChoice == 2) {
                        Collections.sort(bookList, new SortByAuthor());
                        showAllBooks(bookList);
                        //break;
                    } else if (bookInfoChoice == 3) {
                        System.out.println("Input the title of the book");
                        String NameOfBook = cust.nextLine();
                        Book book = findBookByTitle(NameOfBook, bookList);
                        bookDescription(book);
                        //break;
                    } else if (bookInfoChoice == 4) {
                        //break;
                    } else {
                        System.out.println("Input a number 1-4");
                        //break;
                    }
                    break;

                case 6:  //Search book
                    searchBook(user, bookList);
                    break;

                case 9: //Quit
                    FileUtils.saveObject("users.ser", userList);
                    FileUtils.saveObject("books.ser", bookList);
                    cus = false;
                    break;
            }
        }
    }

    private void customerMenu() {
        System.out.println("");
        System.out.println("-----Customer menu-----");
        System.out.println("Enter");
        System.out.println("1 : Borrow book");
        System.out.println("2 : Return book");
        System.out.println("3 : Show your borrowed books");
        System.out.println("4 : Show all available books");
        System.out.println("5 : Show all library books");
        System.out.println("6 : Search book");
        System.out.println("9 : Quit");
        System.out.println("-----------------------");
    }

    private void bookInforMenu() {
        System.out.println("More over, would you like to ");
        System.out.println("");
        System.out.println("1 : Sort the book list by title");
        System.out.println("2 : Sort the book list by author");
        System.out.println("3 : Show more details of a book");
        System.out.println("4 : Quit");
        System.out.println("Enter a number");
    }


    //show only available books in list
    public void onlyAvailableBooks(ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if ((book.isAvailable())) {
                book.bookInfo();
            }
        }
    }


    //borrow book by book title(partial string)
    public void borrowBook(User user, Book book) {
        Scanner sc = new Scanner(System.in);
        if (book.isAvailable()) {
            System.out.println("Do you want to borrow this book?  y/n");
            String answer = sc.nextLine();
            if (answer.equals("y")) {
                user.addLoan(book);
                System.out.println(user.getName() + " borrowed the book : " + book.getTitle());
                book.setDueDate();
                System.out.println("Duedate of the book : "+book.getDueDate().toLocalDate());
                return;
            } else if (answer.equals("n")) {
                System.out.println("See you!");
                return;
            }
        } else {
            System.out.println("The book is not available");
            return;
        }
    }



    @Override
    public void userInfor() {
        System.out.println("Customer  {" +
                "Name : '" + getName() + '\'' +
                ", SocialSecNumber : '" + getSocialSecNumber() + '\'' +
                '}');
    }
}
