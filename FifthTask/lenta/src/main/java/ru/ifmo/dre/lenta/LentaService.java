package ru.ifmo.dre.lenta;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.*;
import ru.ifmo.dre.service.AbstractNews;
import ru.ifmo.dre.service.NewsService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class LentaService extends AbstractNews implements NewsService  {

    public String getNewsName(){
        return "Lenta";
    }

    public List<String> getAllWords() {
        List<String> res = new ArrayList<>();
        try {
            URL url = new URL("https://api.lenta.ru/rss");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                res.addAll(Stream.of(entry.getTitle().split("[^A-Za-zА-Яа-я]+"))
                        .map(String::toLowerCase).toList());
            }
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
        return res;
    }
}