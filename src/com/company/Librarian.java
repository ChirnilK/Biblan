package com.company;

public class Librarian extends User {
    private int password;

    public Librarian(String name, String socialSecNumber, int password) {
        super(name, socialSecNumber);
        this.password = password;
    }

    public int getPassword() {
        return password;
    }

    @Override
    public void userInfor() {
        System.out.println("Librarian {" +
                "Name : '" + getName() + '\'' +
                ", SocialSecNumber : '" + getSocialSecNumber() + '\'' +
                '}');
    }
}
