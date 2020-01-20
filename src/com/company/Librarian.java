package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Librarian extends User {

    public Librarian(String name, String socialSecNumber, int password) {
        super(name, socialSecNumber, password);
    }

    public void librarian(ArrayList<Book> bookList, ArrayList<User> userList) {
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
                    addBook(title, author, description, true, bookList);
                    break;

                case "2":   //Add user
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
                            addLibrarian(name, secNumber, password, userList);
                        } else {
                            addCustomer(name, secNumber, password, userList);
                        }
                    } else {
                        System.out.println("Input l or c");
                    }
                    break;

                case "3":  //Show all books
                    showAllBooks(bookList);
                    break;

                case "4":  //Show all users
                    showAllUsers(userList);
                    break;

                case "5": //Remove book
                    System.out.println("Enter the title of the book you want to remove from the list");
                    showAllBooks(bookList);
                    String removeBook = librarian.nextLine();
                    Book book = findBookByTitle(removeBook, bookList);
                    if(book!=null) {
                        System.out.println("Whould you like to remove this book?  y/n");
                        String answer = librarian.nextLine();
                        if (answer.equals("y")) {
                            removeBook(book, bookList);
                        } else if (answer.equals("n")) {
                            break;
                        }
                    }
                    break;

                case "6": //Remove user
                    System.out.println("Which user do you want to remove from the list");
                    showAllUsers(userList);
                    String removeUser = librarian.nextLine();
                    User user = findUserByName(removeUser, userList);
                    System.out.println("Whould you like to remove this user?  y/n");
                    String answerU = librarian.nextLine();
                    if (answerU.equals("y")) {
                        removeUser(user, userList);
                    } else if (answerU.equals("n")) {
                        break;
                    }
                    break;

                case "7": //Search user
                    System.out.println("Search word?");
                    String searchWord = librarian.nextLine();
                    findUserByName(searchWord, userList);
                    break;

                case "8": //Show borrowed books by user
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
        System.out.println("3 : Show all books");
        System.out.println("4 : Show all users");
        System.out.println("5 : Remove book");
        System.out.println("6 : Remove user");
        System.out.println("7 : Search user");
        System.out.println("8 : Show borrowed books by user");
        System.out.println("9 : Quit");
        System.out.println("-----------------------");
    }


    //add a book to list
    public void addBook(String title, String author, String description, boolean available, ArrayList<Book> bookList) {
        bookList.add(new Book(title, author, description, true));
    }

    //add a librarian to userList
    public void addLibrarian(String name, String secNumber, int password, ArrayList<User> userList) {
        userList.add(new Librarian(name, secNumber, password));
    }

    //add a customer to userList
    public void addCustomer(String name, String secNumber, int password, ArrayList<User> userList) {
        userList.add(new Customer(name, secNumber, password));
    }

    //show all users in list. first save the list to a file named "users.ser" then load the file
    public void showAllUsers(ArrayList<User> userList) {
        FileUtils.saveObject("users.ser", userList);
        ArrayList<User> list1 = (ArrayList) FileUtils.loadObject("users.ser");
        for (User user : list1) {
            user.userInfor();
        }
    }

/*    //find user by name in userList. return User. Here we use partial string.
    ArrayList<User> findUserList = new ArrayList<>();
    public ArrayList<User> findUserListByName(String searchName, ArrayList<User> userList) {
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(searchName.toLowerCase())) {
                findUserList.add(user);
                return findUserList;
            }
        }
        System.out.println("Couldn't find a user with that word");
        return null;
    }*/
    //find user by name in userList. return User. Here we use partial string.
    public User findUserByName(String searchName, ArrayList<User> userList) {
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(searchName.toLowerCase())) {
                user.userInfor();
                return user;
            }
        }
        System.out.println("Couldn't find any user. Try it again");
        return null;
    }



    public void removeBook(Book book, ArrayList<Book> bookList) {
        bookList.remove(book);
        System.out.printf("The book : '%s' is removed", book.getTitle());
    }

    public void removeUser(User user, ArrayList<User> userList) {
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
