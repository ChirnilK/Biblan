package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {


    public static void saveObject(String filename, Object object, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, option))) {
            out.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(String filename) {
        Path path = Paths.get(filename);
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            return in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    //Read from file...
    public static void readAllLines(String fileName) {

        List<String> lines = null;
        fileName = fileName + ".txt";

        try {
            lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Write some lines to a file
    public static void writeAllLines(ArrayList<String> data, String fileName) {
        List<String> lines = data;
        fileName = fileName + ".txt";
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                System.out.println("there is no such file");
                System.out.println("Do you want to make a new file with the name? y/n");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    Files.createFile(path);
                    Files.write(path, lines, StandardCharsets.UTF_8);  //StandardCharsets.UTF_8 är recommenderad att skrivas !
                } else if (answer.equals("n")) {
                    System.exit(0);
                }
            } else {
                System.out.println("The file exists");
                Files.write(path, lines, StandardCharsets.UTF_8);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
