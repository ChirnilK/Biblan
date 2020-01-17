package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Method {

    //find a user by name in userList, return User. InputName should be exactly same spell as userName. No partial string.
    public User getUser(String inputName, ArrayList<User> userList) {
        for (User user : userList) {
            if (user.getName().toLowerCase().equals(inputName.toLowerCase())) {
                return user;
            }
        }
        return null;
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

    //show all books in list. first save the list to a file named "books.ser" then load the file
    public void showAllBooks(ArrayList<Book> bookList) {
        FileUtils.saveObject("books.ser", bookList);
        List<Book> list = (List) FileUtils.loadObject("books.ser");
        for (Book book : list) {
            book.bookInfo();
        }
    }

    //show all users in list. first save the list to a file named "users.ser" then load the file
    public void showAllUsers(ArrayList<User> userList) {
        FileUtils.saveObject("users.ser", userList);
        List<User> list1 = (List) FileUtils.loadObject("users.ser");
        for (User user : list1) {
            user.userInfor();
        }
    }

    //show only available books in list
    public void onlyAvailableBooks(ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if ((book.isAvailable())) {
                book.bookInfo();
            }
        }
    }

    //find user by name in userList. return User. Here we use partial string.
    public User findUserByName(String searchName, ArrayList<User> userList) {
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(searchName.toLowerCase())) {
                user.userInfor();
                return user;
            }
        }
        System.out.println("Couldn't find a user with that word");
        return null;
    }

    //search book by book title or author. The loggin user can borrow the book after searching.
    public void searchBook(User user, ArrayList<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search by book title / author name?  Enter t / a ");
        String search = scanner.nextLine();
        if (search.equals("t")) {
            System.out.println("What is the book title?");
            String searchTitle = scanner.nextLine();
            Book book = findBookByTitle(searchTitle, bookList);
            if (book != null) {
                System.out.println("Would you like to borrow the book?  y/n");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    borrowBook(user, book);
                } else if (answer.equals("n")) {
                    System.out.println("See you!");
                }
            }
        } else if (search.equals("a")) {
            System.out.println("What is the Author name?");
            String searchAuthor = scanner.nextLine();
            Book book = findBookByAuthor(searchAuthor, bookList);
            if (book != null) {
                System.out.println("Would you like to borrow the book?  y/n");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    borrowBook(user, book);
                } else if (answer.equals("n")) {
                    System.out.println("See you!");
                }
            }
        } else {
            System.out.println("Enter 't' or 'a'");
        }
    }


    //borrow book by book title(partial string)
    public void borrowBook(User user, Book book) {
        Scanner sc = new Scanner(System.in);
        if (book.isAvailable()) {
            System.out.println("Do you want to borrow this book?  y/n");
            String answer = sc.nextLine();
            if (answer.equals("y")) {
                user.addLoan(book);
                System.out.println(user.getName() + " borrowed the book : " + book.getTitle());
                return;
            } else if (answer.equals("n")) {
                System.out.println("See you!");
                return;
            }
        } else {
            System.out.println("The book is not available");
            return;
        }
    }

    //show user's all loan. return true for there is loan. false for no loan.
    public boolean showUserLoans(User user) {
        System.out.printf("Information of %s's loan :\n", user.getName());
        boolean loan = user.isThereLoan();
        if (loan) {
            user.showBorrowedBooks();
            return true;
        }
        System.out.println("You haven't borrowed any book.");
        return false;
    }


    //find a book by book title(partial string ok). return Book
    public Book findBookByTitle(String name, ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(name.toLowerCase())) {
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find any");
        return null;
    }

    //find a book by author(partial string ok). return Book.
    public Book findBookByAuthor(String name, ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if (book.getAuthor().toLowerCase().contains(name.toLowerCase())) {
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find any");
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

    public void bookDescription(Book book){
        System.out.println(book.getDescription());
    }

}