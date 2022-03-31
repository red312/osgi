package ru.ifmo.dre.FifthTask.service;

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

public abstract class AbstractNewsService implements NewsService{
    private final int wordsLength = 2;

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
                .filter(word -> word.length() > wordsLength)
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
        } catch (FeedException e) {
            System.err.println("Bad rss feed");
        } catch (MalformedURLException e) {
            System.err.println("Something wrong with url");
        } catch (IOException e) {
            System.err.println("Bad connection");
        }
        return res;
    }
}
