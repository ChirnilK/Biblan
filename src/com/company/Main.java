package com.company;

public class Main {

    public static void main(String[] args) {
	    Library library = new Library();

        library.method.addBook("Discrete mathematics", "hhh", "ddd", true, library.getBooks());
        library.method.addBook("Fourier transform", "fff", "here", true, library.getBooks());
        library.method.addBook("Calculus 1", "ggg", "gewr", true, library.getBooks());
        library.method.addBook("aa", "aaa", "gewreer", true, library.getBooks());
        library.method.addBook("bb", "bbb", "gewaser", true, library.getBooks());
        FileUtils.saveObject("books.ser", library.getBooks());

        library.method.addLibrarian("Johan", "070-3343333", library.getUsers());
        library.method.addLibrarian("Maria", "070-3348877", library.getUsers());
        library.method.addLibrarian("Sari", "070-3378393", library.getUsers());
        library.method.addCustomer("Tary", "070-3343333", library.getUsers());
        library.method.addCustomer("Pontos", "070-3348877", library.getUsers());
        library.method.addCustomer("Kai", "070-3378393", library.getUsers());

        library.start();
    }
}
