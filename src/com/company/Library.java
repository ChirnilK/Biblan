package com.company;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Library implements Serializable {
    Method method = new Method();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();

    public Library(){

        method.addBook("Discrete mathematics", "hhh", "ddd", true, books);
        method.addBook("Fourier transform", "fff", "here", true, books);
        method.addBook("Calculus 1", "ggg", "gewr", true, books);
        method.addBook("aa", "aaa", "gewreer", true, books);
        method.addBook("bb", "bbb", "gewaser", true, books);

        method.addLibrarian("Johan", "841123-8976", 1111, users);
        method.addLibrarian("Maria", "720304-2345", 2222, users);
        method.addLibrarian("Sari", "000405-1112", 3333, users);
        method.addCustomer("Tary", "011102-4785", 4444, users);
        method.addCustomer("Pontos", "811218-3911",5555, users);
        method.addCustomer("Kai", "880713-6840", 6666, users);
    }



    public void start() {
        User user = userLoggIn();
        boolean loop = true;
        while (loop) {

            meinMenu();
            Scanner scanner = new Scanner(System.in);
            String whoUseSystem = scanner.nextLine();
            switch (whoUseSystem) {
                case "1": //Librarian
                    boolean isUserLib = adminCheck(user);
                    if (isUserLib) {
                        librarianMenu();
                        librarian();
                    } else {
                        System.out.println("You don't have permission.");
                    }
                    break;

                case "2":
                    customerMenu();
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


    public void librarian() {

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
                System.out.println("Input the user name");
                String name = librarian.nextLine();
                System.out.println("Input the socialSecNumber and password");
                String secNumber = librarian.nextLine();
                int password = librarian.nextInt();
                System.out.println("Is the user Librarian/Customer? Anser with l/c");
                String category = librarian.nextLine();
                if (category.equals("l")) {
                    method.addLibrarian(name, secNumber, password, users);
                } else if (category.equals("c")) {
                    method.addCustomer(name, secNumber, password, users);
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
            case 9:  //Quit
                break;
        }
    }

    public void customer(User user){
        Scanner cust = new Scanner(System.in);
        int userChoice = Integer.parseInt(cust.nextLine());
        switch (userChoice) {
            case 1:    //Borrow book
                method.onlyAvailableBooks(books);
                System.out.println("Which book would you like to borrow?");
                String borrowB = cust.nextLine();
                method.borrowBook(user, borrowB, users, books);
                break;

            case 2:   //Return book
                method.showUserLoans(user, users);
                System.out.println("Which one would you like to return?");
                String returnItem = cust.nextLine();
                method.returnBook(user, returnItem, users);
                break;

            case 3:  //Show user's borrowed items
                method.showUserLoans(user, users);
                break;

            case 4:  //Show all available books
                method.onlyAvailableBooks(books);
                break;

            case 5:  //Show all library books
                method.showAllBooks(books);
                System.out.println("");
                System.out.println("Would you like sort these books by title/ author?  t / a");
                String sortAnswer = cust.nextLine();
                if (sortAnswer.equals("t")) {
                    Collections.sort(books, new SortByTitle());
                    method.showAllBooks(books);
                } else if (sortAnswer.equals("a")) {
                    Collections.sort(books, new SortByAuthor());
                    method.showAllBooks(books);
                } else {
                    System.out.println("Input t/a");
                }
                break;

            case 6:  //Search book
                System.out.println("Search by book title / author name?  Enter t / a ");
                String search = cust.nextLine();
                if (search.equals("t")) {
                    System.out.println("What is the book title?");
                    String searchTitle = cust.nextLine();
                    Book book = method.findBookByTitle(searchTitle, books);
                    if (book != null) {
                        System.out.println("Would you like to borrow the book?  y/n");
                        String answer = cust.nextLine();
                        if (answer.equals("y")) {
                            method.borrowBook(user, book.getTitle(), users, books);
                        } else if (answer.equals("n")) {
                            break;
                        }
                    }
                } else if (search.equals("a")) {
                    System.out.println("What is the Author name?");
                    String searchAuthor = cust.nextLine();
                    Book book = method.findBookByAuthor(searchAuthor, books);
                    if (book != null) {
                        System.out.println("Would you like to borrow the book?  y/n");
                        String answer = cust.nextLine();
                        if (answer.equals("y")) {
                            method.borrowBook(user, book.getTitle(), users, books);
                        } else if (answer.equals("n")) {
                            break;
                        }
                    } else {
                        System.out.println("Enter 't' or 'a'");
                    }
                }

            case 9: //Quit
                break;
        }
    }


    public void meinMenu () {
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("Librarian : Enter '1'");
        System.out.println("Customer  : Enter '2'");
        System.out.println("Quit      : Enter 11");
        System.out.println("--------------------");
    }

    public void librarianMenu () {
        System.out.println("--------------------");
        System.out.println("Enter");
        System.out.println("1 : Add book");
        System.out.println("2 : Add user");
        System.out.println("3 : Show all books");
        System.out.println("4 : Show all users");
        System.out.println("5 : Remove book");
        System.out.println("6 : Remove user");
        System.out.println("7 : Search book");
        System.out.println("8 : Search user");
        System.out.println("9 : Quit");
        System.out.println("--------------------");
    }

    public void customerMenu () {
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

    public User userLoggIn(){
        System.out.println("");
        System.out.println("---- Welcome to the Skåne library! ----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your name and password, please");
        String userInputName = scanner.nextLine();
        int userInputPassword = Integer.parseInt(scanner.nextLine());
        User user = method.getUser(userInputName, users);
        if(user.getPassword()==userInputPassword) {
            System.out.printf("You are successfully logged in as %s ", user.getName());
            return user;
        }
        return null;
    }

    public boolean adminCheck(User user) {
        if (user instanceof Librarian) {
            return true;
        }
        return false;
    }
}




  /*  public void loadHistory() {
        Library fileHistory = (Library) (FileUtils.readObject("SaveHistory.ser"));
        this.users = fileHistory.users;
        this.books = fileHistory.books;
        this.availableBooks = fileHistory.availableBooks;
        System.out.println("Game successfully loaded..");
    }*/
