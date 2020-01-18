package com.company;


import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Library implements Serializable {
    Method method = new Method();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();


    public void start() throws IOException {

        loading("books", "users");
        try {
            User user = userLoggIn();
            if (user != null) {
                boolean loop = true;
                while (loop) {

                    meinMenu();
                    Scanner scanner = new Scanner(System.in);
                    String whoUseSystem = scanner.nextLine();
                    switch (whoUseSystem) {
                        case "1": //Librarian
                            boolean isUserLib = adminCheck(user);
                            if (isUserLib) {
                                librarian();
                            } else {
                                System.out.println("You don't have permission.");
                            }
                            break;

                        case "2":
                            customer(user);
                            break;

                        case "11":
                            loop = false;
                            break;

                        default:
                            System.out.println("To choose, enter either '1' or '2'");

                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Wrong username or password. Please contact to librarian");
        }
    }


    private void librarian() {
        boolean lib = true;
        while (lib) {
            librarianMenu();
            Scanner librarian = new Scanner(System.in);
            int librarianChoice = Integer.parseInt(librarian.nextLine());
            switch (librarianChoice) {
                case 1:   //Add book
                    System.out.println("Input the book title");
                    String title = librarian.nextLine();
                    System.out.println("Input the book author");
                    String author = librarian.nextLine();
                    System.out.println("Input the description of the book");
                    String description = librarian.nextLine();
                    method.addBook(title, author, description, true, books);
                    break;

                case 2:   //Add user
                    System.out.println("Is the user Librarian/Customer? Input l/c");
                    String category = librarian.nextLine();
                    if (category.equals("l") || category.equals("c")) {
                        System.out.println("Input the user name");
                        String name = librarian.nextLine();
                        System.out.println("Input the socialSecNumber");
                        String secNumber = librarian.nextLine();
                        System.out.println("Input password");
                        int password = librarian.nextInt();

                        if (category.equals("l")) {
                            method.addLibrarian(name, secNumber, password, users);
                        } else {
                            method.addCustomer(name, secNumber, password, users);
                        }
                    } else {
                        System.out.println("Input l or c");
                    }
                    break;

                case 3:  //Show all books
                    method.showAllBooks(books);
                    break;

                case 4:  //Show all users
                    method.showAllUsers(users);
                    break;

                case 5: //Remove book
                    System.out.println("Which book do you want to remove from the list");
                    method.showAllBooks(books);
                    String removeBook = librarian.nextLine();
                    Book book = method.findBookByTitle(removeBook, books);
                    System.out.println("Whould you like to remove this book?  y/n");
                    String answer = librarian.nextLine();
                    if (answer.equals("y")) {
                        method.removeBook(book, books);
                    } else if (answer.equals("n")) {
                        break;
                    }
                    break;

                case 6: //Remove user
                    System.out.println("Which user do you want to remove from the list");
                    method.showAllUsers(users);
                    String removeUser = librarian.nextLine();
                    User user = method.findUserByName(removeUser, users);
                    System.out.println("Whould you like to remove this book?  y/n");
                    String answerU = librarian.nextLine();
                    if (answerU.equals("y")) {
                        method.removeUser(user, users);
                    } else if (answerU.equals("n")) {
                        break;
                    }
                    break;

                case 7: //Search user
                    System.out.println("Search word?");
                    String searchWord = librarian.nextLine();
                    method.findUserByName(searchWord, users);
                    break;

                case 8: //Show borrowed books by user
                    System.out.println("---List of borrowed books---");
                    for (User libraryUser : users) {
                        System.out.printf("User : %s \n", libraryUser.getName());
                        libraryUser.showBorrowedBooks();
                        System.out.println("");
                    }
                    break;

                case 9:  //Quit
                    FileUtils.saveObject("users.ser", users);
                    FileUtils.saveObject("books.ser", books);
                    lib = false;
                    break;
            }
        }
    }

    private void customer(User user) {
        boolean cus = true;
        while (cus) {
            customerMenu();
            Scanner cust = new Scanner(System.in);
            int userChoice = Integer.parseInt(cust.nextLine());
            switch (userChoice) {
                case 1:    //Borrow book
                    method.showAllBooks(books);
                    System.out.println("Which book would you like to borrow?");
                    String borrowB = cust.nextLine();
                    Book bok = method.findBookByTitle(borrowB, books);
                    if (bok != null) {
                        method.borrowBook(user, bok);
                    }
                    break;

                case 2:   //Return book
                    boolean loan = method.showUserLoans(user);
                    if (loan) {
                        System.out.println("Which book would you like to return?");
                        String returnItem = cust.nextLine();
                        Book book = method.findBookByTitle(returnItem, books);
                        if (book != null) {
                            user.returnBook(book);
                            System.out.printf("%s successfully returned the book : %s", user.getName(), book.getTitle());
                        }
                    }
                    break;

                case 3:  //Show user's borrowed items
                    method.showUserLoans(user);
                    break;

                case 4:  //Show all available books
                    method.onlyAvailableBooks(books);
                    break;

                case 5:  //Show all library books
                    method.showAllBooks(books);
                    System.out.println("");
                    bookInforMenu();
                    int bookInfoChoice = Integer.parseInt(cust.nextLine());
                    if (bookInfoChoice == 1) {
                        Collections.sort(books, new SortByTitle());
                        method.showAllBooks(books);
                        //break;
                    } else if (bookInfoChoice == 2) {
                        Collections.sort(books, new SortByAuthor());
                        method.showAllBooks(books);
                        //break;
                    } else if (bookInfoChoice == 3) {
                        System.out.println("Input the title of the book");
                        String NameOfBook = cust.nextLine();
                        Book book = method.findBookByTitle(NameOfBook, books);
                        method.bookDescription(book);
                        //break;
                    } else if (bookInfoChoice == 4) {
                        //break;
                    } else {
                        System.out.println("Input a number 1-4");
                        //break;
                    }
                    break;

                case 6:  //Search book
                    method.searchBook(user, books);
                    break;

                case 9: //Quit
                    FileUtils.saveObject("users.ser", users);
                    FileUtils.saveObject("books.ser", books);
                    cus = false;
                    break;
            }
        }
    }

    private void originalData() {

        method.addBook("Discrete mathematics", "hhh", "Discrete mathematics is the study of mathematical structures that are fundamentally discrete rather than continuous. In contrast to real numbers that have the property of varying \"smoothly\", the objects studied in discrete mathematics – such as integers, graphs, and statements in logic[1] – do not vary smoothly in this way, but have distinct, separated values.", true, books);
        method.addBook("Fourier transform", "fff", "The Fourier transform (FT) decomposes a function of time (a signal) into its constituent frequencies. ", true, books);
        method.addBook("Calculus 1", "ggg", "gewr", true, books);
        method.addBook("Complex Analysis", "Theodore W. Gamelin", "gewreer", true, books);
        method.addBook("aa", "aaaaa", "ddfdsdf", true, books);
        method.addBook("bb", "bbb", "gewaser", true, books);

        method.addLibrarian("Johan", "841123-8976", 1111, users);
        method.addLibrarian("Maria", "720304-2345", 2222, users);
        method.addLibrarian("Sari", "000405-1112", 3333, users);
        method.addCustomer("Tary", "011102-4785", 4444, users);
        method.addCustomer("Pontos", "811218-3911", 5555, users);
        method.addCustomer("Kai", "880713-6840", 6666, users);
    }


    private void meinMenu() {
        System.out.println("");
        System.out.println("------------------------------------------");
        System.out.println("Go to");
        System.out.println("Librarian menu : Enter '1' (admin)");
        System.out.println("Customer  menu : Enter '2'");
        System.out.println("     Quit      : Enter 11");
        System.out.println("------------------------------------------");
    }

    private void librarianMenu() {
        System.out.println("");
        System.out.println("-----Librarian menu-----");
        System.out.println("Enter");
        System.out.println("1 : Add book");
        System.out.println("2 : Add user");
        System.out.println("3 : Show all books");
        System.out.println("4 : Show all users");
        System.out.println("5 : Remove book");
        System.out.println("6 : Remove user");
        System.out.println("7 : Search user");
        System.out.println("8 : Show borrowed books by user");
        System.out.println("9 : Quit");
        System.out.println("-----------------------");
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

    private User userLoggIn() {
        System.out.println("");
        System.out.println("---- Welcome to the Skåne library! ----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your name");
        String userInputName = scanner.nextLine();
        System.out.println("Input password");
        int userInputPassword = Integer.parseInt(scanner.nextLine());
        User user = method.getUser(userInputName, users);
        if (user.getPassword() == userInputPassword) {
            System.out.printf("You are successfully logged in as %s ", user.getName());
            return user;
        }
        System.out.println("Wrong password. Try it again");
        return null;
    }

    private boolean adminCheck(User user) {
        if (user instanceof Librarian) {
            return true;
        }
        return false;
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

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    private void loading(String fileName1, String fileName2) throws IOException {
        fileName1 = fileName1 + ".ser";
        Path path1 = Paths.get(fileName1);
        fileName2 = fileName2 + ".ser";
        Path path2 = Paths.get(fileName2);
        if (Files.exists(path1) && Files.exists(path2)) {
            ArrayList<Book> bookList = (ArrayList) FileUtils.loadObject("books.ser");
            setBooks(bookList);
            ArrayList<User> userList = (ArrayList) FileUtils.loadObject("users.ser");
            setUsers(userList);
        } else {
            Files.createFile(path1);
            Files.createFile(path2);
            originalData();
            FileUtils.saveObject("books.ser", books);
            FileUtils.saveObject("users.ser", users);
        }
    }
}


