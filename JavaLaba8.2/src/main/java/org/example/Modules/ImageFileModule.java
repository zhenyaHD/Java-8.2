package org.example.Modules;

import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Iterator;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;

@Component
public class ImageFileModule implements FileModule{
    String basePath = new File("").getAbsolutePath()  + "\\" + "folder";

    @Override
    public boolean isCorrectFormat(String path) {
        return path.endsWith(".jpg");
    }

    @Override
    public String[] getDescriptions() {
        String[] descriptions = {
                "1) Вывод размера изображения",
                "2) Вывод информации exif",
                "3) Вывод даты"
        };
        return descriptions;
    }

    @Override
    public void function1(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        try {
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();

            System.out.println("Ширина: " + width + " px");
            System.out.println("Высота: " + height + " px");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void function2(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag.getTagName() + " : " + tag.getDescription());
                }
            }else {
                System.out.println("В данном файле нет EXIF информации");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void function3(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                if (date != null) {
                    System.out.println("Дата: " + date);
                } else {
                    System.out.println("Дата не найдена в метаданных Exif.");
                }
            }else {
                System.out.println("В данном файле нет EXIF информации");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
