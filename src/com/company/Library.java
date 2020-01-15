package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Library implements Serializable {
    Method method = new Method();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> availableBooks = new ArrayList<>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    public void start() {

        boolean running = true;

        while (running) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("---- Welcome to the Skåne library! ----");
            Path path = Paths.get("SaveHistory.ser");
           /* if (!Files.exists(path)) {
                Files.createFile(path);
                FileUtils.saveObject("SaveHistory.ser", users);
                FileUtils.saveObject("SaveHistory.ser", books);
                FileUtils.saveObject("SaveHistory.ser", availableBooks);
                loadHistory();
            }
            else{
                loadHistory();
            }*/
            System.out.println("Press...");
            System.out.println("1 for librarian");
            System.out.println("2 for user");
            System.out.println("11 to quit");
            System.out.println("--------------------");

            String whoUseSystem = scanner.nextLine();
            switch (whoUseSystem) {
                case "1":
                    System.out.println("--------------------");
                    System.out.println("Press...");
                    System.out.println("1 to add book");
                    System.out.println("2 to add user information");
                    System.out.println("3 to show all library books");
                    System.out.println("4 to show all users");
                    System.out.println("11 to quit");
                    System.out.println("--------------------");
                    Scanner librarian = new Scanner(System.in);
                    int librarianChoice = Integer.parseInt(librarian.nextLine());
                    switch (librarianChoice) {
                        case 1:
                            System.out.println("Input the book title");
                            String title = librarian.nextLine();
                            System.out.println("Input the book author");
                            String author = librarian.nextLine();
                            System.out.println("Input the description of the book");
                            String description = librarian.nextLine();
                            method.addBook(title, author, description, true, books);
                            break;

                        case 2:
                            System.out.println("Input the user name");
                            String name = librarian.nextLine();
                            System.out.println("Input the socialSecNumber");
                            String secNumber = librarian.nextLine();
                            System.out.println("Is the user Librarian/Customer? Anser with l/c");
                            String category = librarian.nextLine();
                            if (category.equals("l")){
                                method.addLibrarian(name, secNumber,users);
                            }
                            else if (category.equals("c")){
                                method.addCustomer(name,secNumber,users);
                            }
                            else{
                                System.out.println("Input l or c");
                            }
                            break;

                        case 3:
                            method.showAllBooks(books);
                            break;

                        case 4:
                            method.showAllUsers(users);
                            break;

                        case 11:
                            System.exit(0);
                            running = false;
                            break;

                    }
                case "2":
                    System.out.println("--------------------");
                    System.out.println("Press...");
                    System.out.println("1 to borrow book");
                    System.out.println("2 to return book");
                    System.out.println("3 to show your borrowed items");
                    System.out.println("4 to show all available books");
                    System.out.println("5 to show all library books");
                    System.out.println("6 to search book");
                    System.out.println("11 to quit...");
                    System.out.println("--------------------");
                    Scanner user = new Scanner(System.in);
                    int userChoice = Integer.parseInt(user.nextLine());
                    switch (userChoice) {
                        case 1:
                            System.out.println("What is your name?");
                            String userName1 = scanner.nextLine();
                            method.onlyAvailableBooks(books, availableBooks);
                            method.showAllBooks(availableBooks);
                            System.out.println("What would you like to borrow?");
                            String borrow = scanner.nextLine();
                            method.borrowBook(userName1, borrow, users, books);
                            break;

                        case 2:
                            System.out.println("What is your name?");
                            String userName2 = scanner.nextLine();
                            method.showUserLoans(userName2,users);
                            System.out.println("Which one would you like to return?");
                            String returnItem = scanner.nextLine();
                            method.returnBook(userName2, returnItem, users);
                            break;

                        case 3:
                            System.out.println("What is your name?");
                            String userName3 = scanner.nextLine();
                            method.showUserLoans(userName3,users);
                            break;

                        case 4:
                            method.onlyAvailableBooks(books, availableBooks);
                            break;

/*
                        case 5:
                            method.showAllBooks("books");
                            break;

                        case 6:
                            break;


                        case 11:
                            running = false;
                            break;*/
                    }
                    }
            }
        }


  /*  public void loadHistory() {
        Library fileHistory = (Library) (FileUtils.readObject("SaveHistory.ser"));
        this.users = fileHistory.users;
        this.books = fileHistory.books;
        this.availableBooks = fileHistory.availableBooks;
        System.out.println("Game successfully loaded..");
    }*/
}