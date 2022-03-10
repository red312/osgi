package ru.ifmo.dre.lenta;

import ru.ifmo.dre.service.AbstractNews;
import ru.ifmo.dre.service.NewsService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class LentaService extends AbstractNews implements NewsService  {

    public String getNewsName(){
        return "Lenta";
    }

    public List<String> getAllWords() {
        List<String> words = new ArrayList<>();
        try {
            URL url = new URL("https://api.lenta.ru/rss");
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains("<title>")) {
                    line = line.split("title>")[1];
                    line = line.substring(0, line.length()-2);

                    words.addAll(Stream.of(line.split("[^A-Za-zА-Яа-я]+"))
                            .map(String::toLowerCase).toList());
                }
            }
            words = words.subList(5, words.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
