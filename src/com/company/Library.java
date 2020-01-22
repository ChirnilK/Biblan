package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Library implements Serializable {

    private Librarian currentLibrarian;
    private Customer currentCustomer;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();


    public void start() throws IOException {
        boolean on = true;
        loadingFiles("books", "users");
        while(on) {
            startMenu();                                    // start menu : 1.Login,  2.Quit
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": //log in
                    User user = userLogIn();                // insert user name and password
                    if (user != null) {
                        libraryStart(user);                 // if username and password input correctly, start library menu
                    }
                    break;

                case "2":
                    System.out.println("See you!");
                    on = false;
                    break;

                default:
                    System.out.println("Enter 1 or 2");
                    break;
            }
        }
    }

    private void libraryStart(User user) {

        boolean isUserLib = adminCheck(user);  // return true == librarian, false == customer
        boolean logIn = true;
        while (logIn) {
            mainMenu();
            Scanner scanner = new Scanner(System.in);
            String whoUseSystem = scanner.nextLine();
            switch (whoUseSystem) {
                case "1":                    // the user is librarian && chose librarian menu
                    if(isUserLib){
                        currentLibrarian = (Librarian) user;
                        currentLibrarian.librarian(books, users, user);
                    }
                    else{                    // the user is customer && chose librarian menu
                        System.out.println("You don't have a permission");
                    }
                    break;

                case "2":
                    if(!isUserLib) {           // the user is customer && chose customer menu
                        currentCustomer = (Customer) user;
                        currentCustomer.customer(books, users, user);
                    }
                    else{                     // the user is librarian && chose customer menu
                        System.out.println("Log in as a customer");
                    }
                    break;

                case "11":
                    logIn = false;
                    break;

                default:
                    System.out.println("To choose, enter either '1' or '2'");
                    break;

            }
        }
    }


    private void originalData() {

        books.add(new Book("Discrete and Combinatorial Mathematics", "Ralph P. Grimaldi", "The book is both complete and careful.\r\n " +
                "It continues to maintain its emphasis on algorithms and applications.\r\n" +
                " Excellent exercise sets allow students to perfect skills as they practice.", true));
        books.add(new Book("Who Is Fourier?", "Transnational College of Lex", "This book is about Fourier Analysis.\r\n " +
                "It as an exceptional piece of work in that it starts to explain the techniques of Fourier Analysis \r\n" +
                " from almost ground zero right up to complex variables and groups.", true));
        books.add(new Book("Calculus", "James Stewart", "Success in your calculus course starts here! \r\n" +
                " This book is a world-wide best-sellers for a reason: they are clear, accurate, \r\n " +
                "and filled with relevant, real-world examples.", true));
        books.add(new Book("Complex Analysis", "Theodore W. Gamelin", "An introduction to complex analysis \r\n" +
                " for students with some knowledge of complex numbers from high school.", true));
        books.add(new Book("Linear Algebra Done Right", "Sheldon Axler", "This best-selling textbook for a second course in linear algebra is aimed at undergrad math majors \r\n" +
                " and graduate students.", true));
        books.add(new Book("aa", "bbb", "Test,test", true));

        users.add(new Librarian("Johan", "841123-8976", 1111));
        users.add(new Librarian("Maria", "720304-2345", 2222));
        users.add(new Librarian("Sari", "000405-1112", 3333));
        users.add(new Customer("Pontos", "811218-3911", 4444));
        users.add(new Customer("Johan", "841123-8976", 1222));
        users.add(new Customer("Johannes", "641123-0276", 5555));
    }



    private void startMenu() {
        System.out.println("");
        System.out.println("  Welcome to the Skåne library!");
        System.out.println("----------------------------------");
        System.out.println("    Log in     : Enter '1'");
        System.out.println("  Log out/Quit : Enter '2'");
        System.out.println("----------------------------------");
    }

    private void mainMenu() {
        System.out.println("");
        System.out.println("------------------------------------------");
        System.out.println("Go to");
        System.out.println("Librarian menu : Enter '1' (admin)");
        System.out.println("Customer  menu : Enter '2'");
        System.out.println("    Log out    : Enter 11");
        System.out.println("------------------------------------------");
    }


    //return User if inputName and inputPassword are insert correctly and match one in the user list. No partial string.
    private User getUser(String inputName, int inputPassword) {
        for (User user : users) {
            if (user.getName().toLowerCase().equals(inputName.toLowerCase())&&user.getPassword()==inputPassword) {
                return user;
            }
        }
        return null;
    }

    private User userLogIn() {
        System.out.println("");
        System.out.println(" ---  Log in  --- ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your name :");
        try {
            String userInputName = scanner.nextLine();
            System.out.println("Input password :");
            int userInputPassword = Integer.parseInt(scanner.nextLine());
            User user = getUser(userInputName, userInputPassword);
            if (user != null) {
                System.out.printf("You are successfully logged in as %s, %s ", user.getClass().getName().replace("com.company.", ""), user.getName());
                return user;
            }
        }catch (NumberFormatException e){
            System.out.println("");
        }
        System.out.println("Wrong username or password. Try it again");
        return null;
    }

    // return true if the user is librarian
    private boolean adminCheck(User user) {
        return user instanceof Librarian;
    }


    private void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    private void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    //creat file and save the information(originalData()) if these files don't exist in the path.
    //otherwise load the files and set to the arrayLists, books, users
    private void loadingFiles(String fileName1, String fileName2) throws IOException {
        fileName1 = fileName1 + ".ser";
        Path path1 = Paths.get(fileName1);
        fileName2 = fileName2 + ".ser";
        Path path2 = Paths.get(fileName2);
        if (!Files.exists(path1) && !Files.exists(path2)) {
            Files.createFile(path1);
            Files.createFile(path2);
            originalData();
            FileUtils.saveObject("books.ser", books);
            FileUtils.saveObject("users.ser", users);
        }
        else{
            ArrayList<Book> bookList = (ArrayList) FileUtils.loadObject("books.ser");
            setBooks(bookList);
            ArrayList<User> userList = (ArrayList) FileUtils.loadObject("users.ser");
            setUsers(userList);
        }
    }
}


