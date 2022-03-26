package ru.ifmo.dre.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public abstract class AbstractNews implements NewsService{
    public abstract String getName();
    abstract public URL getUrl() throws MalformedURLException;
    abstract public int topWordsNumber();

    public List<String> getTopWords(){
        int wordsNumber = topWordsNumber();
        List<String> words = getAllWords();
        HashMap<String, Integer> topWords = new HashMap<>();
        for (String word : words) {
            topWords.put(word, topWords.containsKey(word) ? topWords.get(word) + 1 : 1);
        }
        List<String> res = topWords.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .filter(word -> word.length() > 2)
                .limit(wordsNumber).collect(Collectors.toList());
        return res;
    }
    protected List<String> getAllWords() {
        List<String> res = new ArrayList<>();
        try {
            URL url = getUrl();
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
