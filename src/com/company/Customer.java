package com.company;

public class Customer extends User {
    public Customer(String name, String socialSecNumber) {
        super(name, socialSecNumber);
    }

    @Override
    public void userInfor() {
        System.out.println("Customer{" +
                "Name : '" + getName() + '\'' +
                ", SocialSecNumber : '" + getSocialSecNumber() + '\'' +
                '}');
    }
}
