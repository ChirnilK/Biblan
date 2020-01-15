package com.company;

public class Librarian extends User {
    public Librarian(String name, String socialSecNumber) {
        super(name, socialSecNumber);
    }

    @Override
    public void userInfor() {
        System.out.println("Librarian {" +
                "Name : '" + getName() + '\'' +
                ", SocialSecNumber : '" + getSocialSecNumber() + '\'' +
                '}');
    }
}
