package com.company;

public class Librarian extends User {

    public Librarian(String name, String socialSecNumber, int password) {
        super(name, socialSecNumber, password);
    }

    @Override
    public void userInfor() {
        System.out.println("Librarian {" +
                "Name : '" + getName() + '\'' +
                ", SocialSecNumber : '" + getSocialSecNumber() + '\'' +
                '}');
    }
}
