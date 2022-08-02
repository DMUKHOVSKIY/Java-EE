package org.example;

import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String read(){
        //Мы хотим добавить сюда логирование
        return scanner.nextLine().trim();
    }
}
