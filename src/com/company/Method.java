package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Method {


    public void addBook(String title, String author, String description, boolean available, ArrayList<Book> list) {
        list.add(new Book(title, author, description, true));
    }


    public void addLibrarian(String name, String secNumber, int password, ArrayList<User> userList) {
        userList.add(new Librarian(name, secNumber, password));
    }

    public void addCustomer(String name, String secNumber, int password, ArrayList<User> userList) {
        userList.add(new Customer(name, secNumber, password));
    }

    public void showAllBooks(ArrayList<Book> list) {
        FileUtils.saveObject("books.ser", list);
        List<Book> list1 = (List) FileUtils.loadObject("books.ser");
        for (Book book : list1) {
            book.bookInfo();
        }
    }

    public void showAllUsers(ArrayList<User> list) {
        FileUtils.saveObject("users.ser", list);
        List<User> list1 = (List) FileUtils.loadObject("users.ser");
        for (User user : list1) {
            user.userInfor();
        }
    }

    public void onlyAvailableBooks(ArrayList<Book> list) {
        for (Book book : list) {
            if ((book.isAvailable())) {
                book.bookInfo();
            }
        }
    }


    public User getUser(String memberName, ArrayList<User> userList) {
        for (User user : userList) {
            if (user.getName().equals(memberName)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByName(String name, ArrayList<User> userList) {
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                user.userInfor();
                return user;
            }
        }
        return null;
    }


    public void borrowBook(String memberName, String itemName, ArrayList<User> userList, ArrayList<Book> bookList) {
        User user = getUser(memberName, userList);
        if (user != null) {
            for (Book book : bookList) {
                if (book.getTitle().toLowerCase().contains(itemName) && book.isAvailable()) {
                    user.addLoan(book);
                    System.out.println(user.getName() + " borrowed the book : " + book.getTitle());
                    return;
                } else if (book.getTitle().toLowerCase().contains(itemName) && !book.isAvailable()) {
                    System.out.println("The book is not available");
                }
            }
            System.out.println("Couldn't find the book. Please try it again.");
        }
    }

    public void showUserLoans(String userName, ArrayList<User> userList) {
        User user = getUser(userName, userList);
        if (user != null) {
            user.showBorrowedBooks();
        }
    }

    public void returnBook(String userName, String itemName, ArrayList<User> userList) {
        User user = getUser(userName, userList);
        if (user != null) {
            if (user.returnBook(itemName)) {
                System.out.println(user.getName() + " returned the item with title " + itemName);
            }
        }
    }

    public Book findBookByTitle(String name, ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(name.toLowerCase())) {
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find");
        return null;
    }

    public Book findBookByAuthor(String name, ArrayList<Book> bookList) {
        for (Book book : bookList) {
            if (book.getAuthor().toLowerCase().contains(name.toLowerCase())) {
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find");
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

    public boolean adminKonto(ArrayList<User> userList) {
        Scanner admin = new Scanner(System.in);
        System.out.println("Please input your name and password");
        String name = admin.nextLine();
        int pass = admin.nextInt();
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(name.toLowerCase()) && user instanceof Librarian) {
                if ((((Librarian) user).getPassword()) == pass) {
                    return true;
                }
            }
        }
        return false;
    }
}






/*



    public Member getMember(String memberName) {
        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        return null;
    }


    public void borrowLibraryItem(String memberName, String itemName) {
        Member member = getMember(memberName);
        for (LibraryItem item : libraryItems) {
            if (item.getTitle().equals(itemName) && item.getAvailable()) {
                member.addLoan(item);
                System.out.println(member.getName() + " borrowed the item with title " + itemName);
                return;
            }
        }
        System.out.println("We don't have such item");
    }

    public void returnLibraryItem(String memberName, String itemName) {
        Member member = getMember(memberName);
        if (member != null) {
            if (member.returnLoan(itemName)) {
                System.out.println(member.getName() + " returned the item with title " + itemName);
            }
        }
    }

    public void showMemberLoans(String memberName) {
        Member member = getMember(memberName);
        if (member != null) {
            member.showBorrowedLibraryItems();
        }
    }

    private void showAllLibraryLoans() {
        for (Member member : members) {
            System.out.println(member.getName() + ":");
            showMemberLoans(member.getName());

        }
    }

    public void showAvailableGames() {
        for (LibraryItem item : libraryItems) {
            if ((item.getAvailable() && item instanceof Game)) {
                System.out.println(item);
            }
        }
    }


    }
*/