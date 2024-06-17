package org.example;

import org.example.Modules.FileModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "org.example")
public class App 
{
    private static List<FileModule> fileModules;

    @Autowired
    public App(List<FileModule> fileModules) {
        App.fileModules = fileModules;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(App.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла");

        String fileName = scanner.nextLine();
        FileModule fileModule = getFileModule(fileName);

        if(fileModule == null) {
            System.out.println("Файл не найден или данный формат не поддерживается");
        }
        else {
            chooseFunction(fileModule, scanner, fileName);
        }
    }

    private static FileModule getFileModule(String fileName) {
        for (FileModule fileModule : fileModules) {
            if (fileModule.isCorrectFormat(fileName)) {
                return fileModule;
            }
        }
        return null;
    }

    private static void chooseFunction(FileModule fileModule, Scanner scanner, String fileName) {
        writeDescriptions(fileModule);
        System.out.print("Введите номер функции ");
        String funcNum = scanner.nextLine();

        if (funcNum.equals("1")) {
            fileModule.function1(fileName);
        } else if (funcNum.equals("2")) {
            fileModule.function2(fileName);
        } else if (funcNum.equals("3")) {
            fileModule.function3(fileName);
        } else System.out.println("Функция не выбрана");
    }

    private static void writeDescriptions(FileModule fileModule) {
        String[] descriptions = fileModule.getDescriptions();
        for (String description : descriptions) {
            System.out.println(description);
        }
    }
}
