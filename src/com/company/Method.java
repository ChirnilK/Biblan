package com.company;

import java.util.ArrayList;
import java.util.List;


public class Method {


    public void addBook(String title, String author, String description, boolean available, ArrayList<Book> list) {
        list.add(new Book(title, author, description, true));
    }


    public void addLibrarian(String name, String secNumber, ArrayList<User> userList) {
        userList.add(new Librarian(name, secNumber));
    }

    public void addCustomer(String name, String secNumber, ArrayList<User> userList) {
        userList.add(new Customer(name, secNumber));
    }

    public void showAllBooks(ArrayList<Book> list) {
        List<Book> list1 = (List) FileUtils.loadObject("books.ser");
        for (Book book : list1) {
            book.bookInfo();
        }
    }

    public void showAllUsers(ArrayList<User> list) {
        for (User user : list) {
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


    public void borrowBook(String memberName, String itemName, ArrayList<User> userList, ArrayList<Book> bookList) {
        User user = getUser(memberName, userList);
        for (Book book : bookList) {
            if (book.getTitle().equals(itemName) && book.isAvailable()) {
                user.addLoan(book);
                System.out.println(user.getName() + " borrowed the book : " + itemName);
                return;
            }
        }
        System.out.println("We don't have such item");
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
        for (Book book: bookList) {
            if(book.getTitle().toLowerCase().contains(name.toLowerCase())){
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find");
        return null;
    }

    public Book findBookByAuthor(String name, ArrayList<Book> bookList) {
        for (Book book: bookList) {
            if(book.getAuthor().toLowerCase().contains(name.toLowerCase())){
                book.bookInfo();
                return book;
            }
        }
        System.out.println("can't find");
        return null;
    }

}






/*



    public void showAllBooks(String fileName) {
        fileName = fileName+".ser";
        List<Book> books = (List) FileUtils.loadObject(fileName);
        for (Book book : books) {
            book.bookInfo();
        }
    }




    public void showAllUsers() {
        List<User> users = (List) FileUtils.loadObject("users.ser");
        for (User user : users) {
            user.userInfor();
        }
    }


}
}
/*
    public void showMembers() {
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public void addItems(LibraryItem item) {
        libraryItems.add(item);
    }



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

    public ArrayList<LibraryItem> onlyAvailableItems() {
        for (LibraryItem item : libraryItems) {
            if ((item.getAvailable()) == true) {
                availableItems.add(item);
                System.out.println(item);
            }
        }
        return availableItems;
    }

    public void showAvailablePS3() {
        for (LibraryItem item : libraryItems) {
            if ((item.getAvailable() && item instanceof Game)) {
                if (((Game) item).getConsoleType().equals(ConsoleType.PS3)) {
                    System.out.println(item);
                }
            }
        }
    }

    public void showAvailableXBox360() {
        for (LibraryItem item : libraryItems) {
            if ((item.getAvailable() && item instanceof Game)) {
                if (((Game) item).getConsoleType().equals(ConsoleType.XBOX360)) {
                    System.out.println(item);
                }
            }
        }
    }

    public void showAvailableWii() {
        for (LibraryItem item : libraryItems) {
            if ((item.getAvailable() && item instanceof Game)) {
                if (((Game) item).getConsoleType().equals(ConsoleType.WII)) {
                    System.out.println(item);
                }
            }
        }
    }

    public void showAllBooks(){
        for (LibraryItem item : libraryItems) {
            if (item instanceof Book) {
                System.out.println(item);
            }
        }
    }

}
*/
