package com.company;

import java.util.ArrayList;
import java.util.List;


public class Method {



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

    public void showItems() {
        for (LibraryItem item : libraryItems) {
            System.out.println(item);
        }
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
