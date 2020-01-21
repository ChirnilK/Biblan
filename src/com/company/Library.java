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
            startMenu();                                    // login or quit
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": //log in
                    User user = userLoggIn();
                    if (user != null) {
                        libraryStart(user);
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

        boolean isUserLib = adminCheck(user);  //check if the user is librarian or not
        boolean logIn = true;
        while (logIn) {
            mainMenu();
            Scanner scanner = new Scanner(System.in);
            String whoUseSystem = scanner.nextLine();
            switch (whoUseSystem) {
                case "1": //Librarian
                    if(isUserLib){
                        currentLibrarian = (Librarian) user;
                        currentLibrarian.librarian(books, users);
                    }
                    else{
                        System.out.println("You don't have a permission");
                    }
                    break;

                case "2":
                    if(!isUserLib) {
                        currentCustomer = (Customer) user;
                        currentCustomer.customer(books, users, user);
                    }
                    else{
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

        books.add(new Book("Discrete mathematics", "hhh", "Discrete mathematics is the study of mathematical structures that are fundamentally discrete rather than continuous. In contrast to real numbers that have the property of varying \"smoothly\", the objects studied in discrete mathematics – such as integers, graphs, and statements in logic[1] – do not vary smoothly in this way, but have distinct, separated values.", true));
        books.add(new Book("Fourier transform", "fff", "The Fourier transform (FT) decomposes a function of time (a signal) into its constituent frequencies. ", true));
        books.add(new Book("Calculus 1", "ggg", "gewr", true));
        books.add(new Book("Complex Analysis", "Theodore W. Gamelin", "gewreer", true));
        books.add(new Book("aa", "aaaaa", "ddfdsdf", true));
        books.add(new Book("bb", "bbb", "gewaser", true));

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


    //find a user by name in userList, return User. InputName should be exactly same spell as userName. No partial string.
    public User getUser(String inputName, int inputPassword) {
        for (User user : users) {
            if (user.getName().toLowerCase().equals(inputName.toLowerCase())&&user.getPassword()==inputPassword) {
                return user;
            }
        }
        return null;
    }

    private User userLoggIn() {
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

    private boolean adminCheck(User user) {
        if (user instanceof Librarian) {
            return true;
        }
        return false;
    }


    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

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


