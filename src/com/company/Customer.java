package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Customer extends User {
    public Customer(String name, String socialSecNumber, int password) {
        super(name, socialSecNumber, password);
    }

    public void customer(ArrayList<Book> bookList, ArrayList<User> userList, User user) {
        isOverdue(user);
        boolean cus = true;
        while (cus) {
            customerMenu();
            Scanner cust = new Scanner(System.in);
            String userChoice = cust.nextLine();
            switch (userChoice) {
                case "1":    //Borrow book
                    System.out.println("Input the index number of the book you want to borrow");
                    System.out.println("");
                    showAllBooks(bookList);
                    try {
                        int index = Integer.parseInt(cust.nextLine());
                        Book book = bookList.get(index - 1);
                        book.bookInfo();
                        borrowBook(user, book);
                    }catch(Exception e){
                        System.out.println("Choose an index number of the book. Try again");
                    }
                    break;

                case "2":   //Return book
                    boolean loan = showUserLoans(user);
                    if(loan) {
                        user.returnBook(user);
                    }
                    break;

                case "3":  //Show user's borrowed items
                    showUserLoans(user);
                    break;

                case "4":  //Show all available books
                    onlyAvailableBooks(bookList);
                    break;

                case "5":  //Show all library books
                    showAllBooks(bookList);
                    System.out.println("");
                    bookInforMenu();
                    boolean on = true;
                    while(on) {
                        String bookInfoChoice = cust.nextLine();
                        switch (bookInfoChoice) {
                            case "1":
                                Collections.sort(bookList, new SortByTitle());
                                showAllBooks(bookList);
                                on = false;
                                break;

                            case "2":
                                Collections.sort(bookList, new SortByAuthor());
                                showAllBooks(bookList);
                                on = false;
                                break;

                            case "3":
                                System.out.println("Input the index of the book you want to read the description");
                                showAllBooks(bookList);
                                try {
                                    int index = Integer.parseInt(cust.nextLine());
                                    Book book = bookList.get(index - 1);
                                    System.out.println(book.getTitle());
                                    System.out.println(book.getDescription());
                                } catch (Exception e) {
                                    System.out.println("Choose index number. Try again");
                                }
                                on = false;
                                break;

                            case "4":
                                on = false;
                                break;

                            default:
                                System.out.println("Input a number 1-4");
                                break;
                        }
                    }
                    break;


                case "6":  //Search book
                    searchBook(user, bookList);
                    break;

                case "9": //Quit
                    FileUtils.saveObject("users.ser", userList);
                    FileUtils.saveObject("books.ser", bookList);
                    cus = false;
                    break;

                default:
                    System.out.println("Enter a number");
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
            }else if (answer.equals("n")) {
                System.out.println("See you!");
                return;
            }else{
                System.out.println("Input y or n");
            }
        } else {
            System.out.println("The book is not available right now");
            return;
        }
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
                borrowBook(user, book);
            }
        } else if (search.equals("a")) {
            System.out.println("What is the Author name?");
            String searchAuthor = scanner.nextLine();
            Book book = findBookByAuthor(searchAuthor, bookList);
            if (book != null) {
                borrowBook(user, book);
                }
        } else {
            System.out.println("Enter 't' or 'a'");
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
