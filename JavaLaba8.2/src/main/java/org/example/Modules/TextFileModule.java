package org.example.Modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TextFileModule implements FileModule{
    String basePath = new File("").getAbsolutePath()  + "\\" + "folder";
    @Override
    public boolean isCorrectFormat(String path) {
        return path.endsWith(".txt");
    }

    @Override
    public String[] getDescriptions() {
        String[] descriptions = {
                "1) Вывод кол-ва строк",
                "2) Вывод частоты вхождения каждого символа",
                "3) Вывод кол-ва слов в файле"
        };
        return descriptions;
    }

    @Override
    public void function1(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            long count = reader.lines().count();
            System.out.println("Количество строк в файле: " + count);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void function2(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        Map<Character, Integer> mapLetterToCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                for (char letter : line.toCharArray()){
                    mapLetterToCount.put(letter,mapLetterToCount.getOrDefault(letter,0)+1);
                }
            }

            for (Map.Entry<Character, Integer> entry : mapLetterToCount.entrySet()){
                System.out.printf("%c встречается - %d раз%n", entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void function3(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            int count = 0;

            while ((line = reader.readLine()) != null) {
                count += line.split(" ").length;
            }

            System.out.println("В файле " + count + " слов");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
