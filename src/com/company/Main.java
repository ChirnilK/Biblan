package com.company;

public class Main {

    public static void main(String[] args) {
	    Library library = new Library();

        library.method.addBook("Discrete mathematics", "hhh", "ddd", true, library.getBooks());
        library.method.addBook("Fourier transform", "fff", "here", true, library.getBooks());
        library.method.addBook("Calculus 1", "ggg", "gewr", true, library.getBooks());
        library.method.addBook("aa", "aaa", "gewreer", true, library.getBooks());
        library.method.addBook("bb", "bbb", "gewaser", true, library.getBooks());

        library.method.addLibrarian("Johan", "841123-8976", 1111, library.getUsers());
        library.method.addLibrarian("Maria", "720304-2345", 2222, library.getUsers());
        library.method.addLibrarian("Sari", "000405-1112", 3333, library.getUsers());
        library.method.addCustomer("Tary", "011102-4785", 4444, library.getUsers());
        library.method.addCustomer("Pontos", "811218-3911",5555, library.getUsers());
        library.method.addCustomer("Kai", "880713-6840", 6666, library.getUsers());

        library.start();

    }
}
