package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    private List<User> users = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> availableBooks = new ArrayList<>();

    public Library() {
        users.add(new User("Johan", "070-3343333"));
        users.add(new User("Maria", "070-3348877"));
        users.add(new User("Sari", "070-3378393"));
        FileUtils.saveObject("users.ser", users);

        books.add(new Book("Discrete mathematics", "hhh", "ddd", true));
        books.add(new Book("Fourier transform", "fff", "here", true));
        books.add(new Book("Calculus 1", "ggg", "gewr", true));
        FileUtils.saveObject("books.ser", books);

    }


    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("--------------------");
            System.out.println("Press...");
            System.out.println("1 for librarian");
            System.out.println("2 for user");
            System.out.println("11 to quit");
            System.out.println("--------------------");

            Scanner scanner = new Scanner(System.in);
            String whoUseSystem = scanner.nextLine();
            switch (whoUseSystem) {
                case "1":
                    System.out.println("--------------------");
                    System.out.println("Press...");
                    System.out.println("1 to add book");
                    System.out.println("2 to add user information");
                    System.out.println("11 to quit");
                    System.out.println("--------------------");
                    Scanner librarian = new Scanner(System.in);
                    int librarianChoice = Integer.parseInt(librarian.nextLine());
                    switch (librarianChoice) {
                        case 1:
                            break;

                        case 2:
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
                    System.out.println("11 to quit...");
                    System.out.println("--------------------");
                    Scanner user = new Scanner(System.in);
                    int userChoice = Integer.parseInt(user.nextLine());
                    switch (userChoice) {
                        case 1:
                            break;

                        case 2:
                            break;

                        case 3:
                            break;

                        case 4:
                            break;

                        case 5:
                           /* List<Book> books = (List) FileUtils.loadObject("books.ser");
                            for (Book book : books) {
                                System.out.println(book);
                                //System.out.println(person.dog.name);
                            }*/

                            break;

                        case 6:
                            break;


                        case 11:
                            running = false;
                            break;

                    }
                }
            }
        }
    }
