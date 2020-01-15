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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }



    public void start() {

        boolean running = true;
        while (running) {
            meinMenu();
            Scanner scanner = new Scanner(System.in);
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

            String whoUseSystem = scanner.nextLine();
            switch (whoUseSystem) {
                case "1":
                    librarianMenu();
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
                                if (category.equals("l")) {
                                    method.addLibrarian(name, secNumber, users);
                                } else if (category.equals("c")) {
                                    method.addCustomer(name, secNumber, users);
                                } else {
                                    System.out.println("Input l or c");
                                }
                                break;

                            case 3:
                                method.showAllBooks(books);
                                break;

                            case 4:
                                method.showAllUsers(users);
                                break;

                            case 9:
                                break;
                        }
                        break;

                case "2":
                    customerMenu();
                    Scanner user = new Scanner(System.in);
                    int userChoice = Integer.parseInt(user.nextLine());
                    switch (userChoice) {
                        case 1:
                            System.out.println("What is your name?");
                            String userName1 = scanner.nextLine();
                            method.onlyAvailableBooks(books);
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
                            method.onlyAvailableBooks(books);
                            break;


                        case 5:
                            method.showAllBooks(books);
                            break;

                        case 6:
                            break;


                        case 9:
                            break;
                    }
                    break;
                }
            }
        }

    public void meinMenu() {
        System.out.println("");
        System.out.println("---- Welcome to the Skåne library! ----");
        System.out.println("Librarian : Enter '1'");
        System.out.println("Customer  : Enter '2'");
        System.out.println("Quit      : Enter 11");
        System.out.println("--------------------");
    }

    public void librarianMenu() {
        System.out.println("--------------------");
        System.out.println("Enter");
        System.out.println("1 : Add book");
        System.out.println("2 : Add user");
        System.out.println("3 : Show all books");
        System.out.println("4 : Show all users");
        System.out.println("5 : Remove book");
        System.out.println("6 : Remove user");
        System.out.println("9 : Quit");
        System.out.println("--------------------");
    }

    public void customerMenu(){
        System.out.println("--------------------");
        System.out.println("Enter");
        System.out.println("1 : Borrow book");
        System.out.println("2 : Return book");
        System.out.println("3 : Show your borrowed items");
        System.out.println("4 : Show all available books");
        System.out.println("5 : Show all library books");
        System.out.println("6 : Search book");
        System.out.println("9 : Quit");
        System.out.println("--------------------");
    }


  /*  public void loadHistory() {
        Library fileHistory = (Library) (FileUtils.readObject("SaveHistory.ser"));
        this.users = fileHistory.users;
        this.books = fileHistory.books;
        this.availableBooks = fileHistory.availableBooks;
        System.out.println("Game successfully loaded..");
    }*/
}