package com.company;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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

        boolean loop = true;
        while (loop) {
            meinMenu();
            Scanner scanner = new Scanner(System.in);
            String whoUseSystem = scanner.nextLine();
            switch (whoUseSystem) {
                case "1": //Librarian
                    librarianMenu();
                    Scanner librarian = new Scanner(System.in);
                    int librarianChoice = Integer.parseInt(librarian.nextLine());
                    switch (librarianChoice) {
                        case 1:   //Add book
                            boolean admin1 = method.adminKonto(users);
                            if(admin1) {
                                System.out.println("Input the book title");
                                String title = librarian.nextLine();
                                System.out.println("Input the book author");
                                String author = librarian.nextLine();
                                System.out.println("Input the description of the book");
                                String description = librarian.nextLine();
                                method.addBook(title, author, description, true, books);
                            }
                            else{
                                System.out.println("You don't have permission");
                            }
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
                            boolean admin2 = method.adminKonto(users);
                            if(admin2) {
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
                            }
                            else{
                                System.out.println("You don't have permission");
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
                            }
                            else if (answerU.equals("n")) {
                                break;
                            }
                            break;

                        case 9:  //Quit
                            break;
                    }
                    break;

                case "2":  //Customer
                    Scanner user = new Scanner(System.in);
                    System.out.println("Input your name and password, please");
                    String userInputName = user.nextLine();
                    int userInputPassword = Integer.parseInt(user.nextLine());
                    User user1 = method.getUser(userInputName, users);
                    if (user1!=null && user1.getPassword()==userInputPassword) {
                        customerMenu();
                        int userChoice = Integer.parseInt(user.nextLine());
                        switch (userChoice) {
                            case 1:    //Borrow book
                                method.onlyAvailableBooks(books);
                                System.out.println("What would you like to borrow?");
                                String borrow = user.nextLine();
                                method.borrowBook(userInputName, borrow, users, books);
                                break;

                            case 2:   //Return book
                                method.showUserLoans(userInputName, users);
                                System.out.println("Which one would you like to return?");
                                String returnItem = user.nextLine();
                                method.returnBook(userInputName, returnItem, users);
                                break;

                            case 3:  //Show user's borrowed items
                                method.showUserLoans(userInputName, users);
                                break;

                            case 4:  //Show all available books
                                method.onlyAvailableBooks(books);
                                break;


                            case 5:  //Show all library books
                                method.showAllBooks(books);
                                System.out.println("");
                                System.out.println("Would you like sort these books by title/ author?  t / a");
                                String sortAnswer = user.nextLine();
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
                                String search = user.nextLine();
                                if (search.equals("t")) {
                                    System.out.println("What is the book title?");
                                    String searchTitle = user.nextLine();
                                    Book book = method.findBookByTitle(searchTitle, books);
                                    if (book != null) {
                                        System.out.println("Would you like to borrow the book?  y/n");
                                        String answer = user.nextLine();
                                        if (answer.equals("y")) {
                                            method.borrowBook(userInputName, book.getTitle(), users, books);
                                        } else if (answer.equals("n")) {
                                            break;
                                        }
                                    }
                                } else if (search.equals("a")) {
                                    System.out.println("What is the Author name?");
                                    String searchAuthor = user.nextLine();
                                    Book book = method.findBookByAuthor(searchAuthor, books);
                                    if (book != null) {
                                        System.out.println("Would you like to borrow the book?  y/n");
                                        String answer = user.nextLine();
                                        if (answer.equals("y")) {
                                            method.borrowBook(userInputName, book.getTitle(), users, books);
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
                    }else{
                        System.out.println("Loggin information is not correct. Please contact librarian");
                    }
                            break;

                        case "11":
                            loop = false;
                            break;
                        default:
                            System.out.println("To choose, enter either '1' or '2'");

                    }
            }
        }

        public void meinMenu () {
            System.out.println("");
            System.out.println("---- Welcome to the Skåne library! ----");
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
    }


  /*  public void loadHistory() {
        Library fileHistory = (Library) (FileUtils.readObject("SaveHistory.ser"));
        this.users = fileHistory.users;
        this.books = fileHistory.books;
        this.availableBooks = fileHistory.availableBooks;
        System.out.println("Game successfully loaded..");
    }*/
