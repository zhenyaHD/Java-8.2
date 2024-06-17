package org.example.Modules;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class AudioFileModule implements FileModule {
    String basePath = new File("").getAbsolutePath()  + "\\" + "folder";

    @Override
    public boolean isCorrectFormat (String path) {
        return path.endsWith(".mp3");
    }

    @Override
    public String[] getDescriptions() {
        String[] descriptions = {
                "1) Вывод названия аудио файла",
                "2) Вывод длительности в секундах",
                "3) Выводит альбома трека из тегов"
        };
        return descriptions;
    }

    @Override
    public void function1(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            String title = audioFile.getTag().getFirst(FieldKey.TITLE);;
            System.out.println("Название трека: " + title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void function2(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            int durationSeconds = audioFile.getAudioHeader().getTrackLength();
            System.out.println("Длительность трека в секундах: " + durationSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void function3(String fileName) {
        File file = new File(basePath+"\\"+fileName);
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            String album = audioFile.getTag().getFirst(FieldKey.ALBUM);;
            System.out.println("Альбом: " + album);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
