package ru.ifmo.dre.aif;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.*;
import org.osgi.service.component.annotations.Component;
import ru.ifmo.dre.service.AbstractNews;
import ru.ifmo.dre.service.NewsService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component(
        service = NewsService.class,
        immediate = true
)
public class AifService extends AbstractNews implements NewsService  {
    public String getNewsName(){
        return "AIF";
    }
    public List<String> getAllWords() {
        List<String> res = new ArrayList<>();
        try {
            URL url = new URL("https://www.aif.ru/rss/news.php");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries) {
                res.addAll(Stream.of(entry.getTitle().split("[^A-Za-zА-Яа-я]+"))
                        .map(String::toLowerCase).toList());
            }
        } catch (IOException | FeedException e) {
            System.out.println("Something went wrong(");
        }
        return res;
    }
}
