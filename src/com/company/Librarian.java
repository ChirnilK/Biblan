package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Librarian extends User {

    public Librarian(String name, String socialSecNumber, int password) {
        super(name, socialSecNumber, password);
    }

    public void librarian(ArrayList<Book> bookList, ArrayList<User> userList, User user) {
        isOverdue(user);                              //if the user has overdue book, gets remainder
        boolean lib = true;
        while (lib) {
            librarianMenu();
            Scanner librarian = new Scanner(System.in);
            String librarianChoice = librarian.nextLine();
            switch (librarianChoice) {
                case "1":   //Add book
                    System.out.println("Input the book title");
                    String title = librarian.nextLine();
                    System.out.println("Input the book author");
                    String author = librarian.nextLine();
                    System.out.println("Input the description of the book");
                    String description = librarian.nextLine();
                    addBook(title, author, description, bookList);
                    break;

                case "2":   //Add user
                    System.out.println("Is the user Librarian/Customer? Input l/c");
                    String category = librarian.nextLine();
                    if (category.equals("l") || category.equals("c")) {
                        System.out.println("Input the user name");
                        String name = librarian.nextLine();
                        System.out.println("Input the socialSecNumber");
                        String secNumber = librarian.nextLine();
                        try {
                            System.out.println("Input password");
                            int password = librarian.nextInt();
                            if (category.equals("l")) {
                                addLibrarian(name, secNumber, password, userList);
                            } else {
                                addCustomer(name, secNumber, password, userList);
                            }
                        } catch (Exception e) {
                            System.out.println("Password should be numbers");
                        }
                    } else {
                        System.out.println("Input l or c");
                    }
                    break;

                case "3":  //Show all books
                    showAllBooks(bookList);
                    System.out.println("");
                    System.out.println("Do you want to see only available books? y/n");
                    String ans = librarian.nextLine();
                    if(ans.equals("y")) {
                        onlyAvailableBooks(bookList);
                    }else{
                        System.out.println("See you!");
                    }
                    break;

                case "4":  //Show all users
                    showAllUsers(userList);
                    break;

                case "5": //Remove book
                    System.out.println("Enter the index number of the book you want to remove from the list");
                    System.out.println("");
                    showAllBooks(bookList);
                    try {
                        int index = Integer.parseInt(librarian.nextLine());
                        Book book = bookList.get(index - 1);
                        book.bookInfo();
                        System.out.println("Would you like to remove this book?  y/n");
                        String answer = librarian.nextLine();
                        if (answer.equals("y")) {
                            removeBook(book, bookList);
                        } else if (answer.equals("n")) {
                            break;
                        } else {
                            System.out.println("Choose y or n");
                        }
                    } catch (Exception e) {
                        System.out.println("Choose index number. Try again");
                    }
                    break;

                case "6": //Remove user
                    System.out.println("Enter the index number of the user you want to remove from the list");
                    System.out.println("");
                    showAllUsers(userList);
                    try {
                        int index = Integer.parseInt(librarian.nextLine());
                        User inputUser = userList.get(index - 1);
                        inputUser.userInfor();
                        System.out.println("Whould you like to remove this user?  y/n");
                        String answerU = librarian.nextLine();
                        if (answerU.equals("y")) {
                            removeUser(user, userList);
                        } else if (answerU.equals("n")) {
                            break;
                        } else {
                            System.out.println("Choose y or n");
                        }
                    }catch(Exception e){
                        System.out.println("Choose index number. Try again");
                    }
                    break;

                case "7": //Search user
                    System.out.println("Insert an user name that you are searching for?");
                    String searchWord = librarian.nextLine();
                    findUserByName(searchWord, userList);
                    break;

                case "8": //Show borrowed books by all user
                    System.out.println("---List of borrowed books---");
                    for (User libraryUser : userList) {
                        System.out.printf("User : %s \n", libraryUser.getName());
                        libraryUser.showBorrowedBooks();
                        System.out.println("");
                    }
                    break;

                case "9":  //Quit
                    FileUtils.saveObject("users.ser", userList);
                    FileUtils.saveObject("books.ser", bookList);
                    lib = false;
                    break;

                default:
                    System.out.println("To choose, input 1 to 9");

            }
        }
    }

    private void librarianMenu() {
        System.out.println("");
        System.out.println("-----Librarian menu-----");
        System.out.println("Enter");
        System.out.println("1 : Add book");
        System.out.println("2 : Add user");
        System.out.println("3 : Show all books / Available books");
        System.out.println("4 : Show all users");
        System.out.println("5 : Remove book");
        System.out.println("6 : Remove user");
        System.out.println("7 : Search user");
        System.out.println("8 : Show borrowed books by all user");
        System.out.println("9 : Quit");
        System.out.println("-----------------------");
    }


    //add a book to bookList
    private void addBook(String title, String author, String description, ArrayList<Book> bookList) {
        bookList.add(new Book(title, author, description, true));
    }

    //add a librarian to userList
    private void addLibrarian(String name, String secNumber, int password, ArrayList<User> userList) {
        userList.add(new Librarian(name, secNumber, password));
    }

    //add a customer to userList
    private void addCustomer(String name, String secNumber, int password, ArrayList<User> userList) {
        userList.add(new Customer(name, secNumber, password));
    }

    //show all users in list. first save the list to a file named "users.ser" then load the file
    private void showAllUsers(ArrayList<User> userList) {
        FileUtils.saveObject("users.ser", userList);
        ArrayList<User> list = (ArrayList) FileUtils.loadObject("users.ser");
        assert list != null;
        int howMany = list.size();
        for (int i = 0; i<howMany; i++){
            System.out.printf("[%s]", i+1);
            list.get(i).userInfor();
        }
    }


    //find user by name in userList. Here we use partial string.
    private void findUserByName(String searchName, ArrayList<User> userList) {
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(searchName.toLowerCase())) {
                user.userInfor();
            }
        }
        System.out.println("Couldn't find any user? Try it again");
    }


    //remove a book from bookList
    private void removeBook(Book book, ArrayList<Book> bookList) {
        bookList.remove(book);
        System.out.printf("The book : '%s' is removed", book.getTitle());
    }

    //remove a user from userList
    private void removeUser(User user, ArrayList<User> userList) {
        userList.remove(user);
        System.out.printf("The user : '%s' is removed", user.getName());
    }


    @Override
    public void userInfor() {
        System.out.println("Librarian {" +
                "Name : '" + getName() + '\'' +
                ", SocialSecNumber : '" + getSocialSecNumber() + '\'' +
                '}');
    }
}
