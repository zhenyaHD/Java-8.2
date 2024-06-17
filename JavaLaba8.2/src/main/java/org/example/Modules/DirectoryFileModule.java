package org.example.Modules;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryFileModule implements FileModule {
    String basePath = new File("").getAbsolutePath()  + "\\" + "folder";

    @Override
    public boolean isCorrectFormat(String fileName) {
        File fileSystemElement = new File(basePath + "\\" + fileName);
        return fileSystemElement.isDirectory();
    }

    @Override
    public String[] getDescriptions() {
        String[] descriptions = {
                "1) Вывод списка файлов в каталоге",
                "2) Вывод суммарного размера всех файлов в каталоге",
                "3) Вывод самого большого по размеру файла в каталоге"
        };
        return descriptions;
    }

    @Override
    public void function1(String fileName) {
        File directory = new File(basePath + "\\" + fileName);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if(file.isFile()){
                    System.out.println(file.getName());
                }
            }
        }
    }

    @Override
    public void function2(String fileName) {
        File directory = new File(basePath + "\\" + fileName);
        File[] files = directory.listFiles();

        long size = 0;
        if (files != null) {
            for (File file : files) {
                if(file.isFile()){
                    size+=file.length();
                }
            }
        }
        System.out.println(size + " байт");
    }

    @Override
    public void function3(String fileName) {
        File directory = new File(basePath + "\\" + fileName);
        File[] files = directory.listFiles();

        File max = null;

        if (files != null) {
            max = files[0];
            for (File file : files) {
                if (file.length() > max.length()) {
                    max = file;
                }
            }
            System.out.println(max.getName() + ": " + max.length() + " байт");
        }
        else {
            System.out.println("Файлов в каталоге нет");
        }
    }
}
