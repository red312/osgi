package ru.ifmo.dre.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractNews implements NewsService{
    public List<String> getTopWords(){
        List<String> words = this.getAllWords();
        HashMap<String, Integer> topWords = new HashMap<>();
        for (String word : words) {
            topWords.put(word, topWords.containsKey(word) ? topWords.get(word) + 1 : 1);
        }
        List<String> res = topWords.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(10).collect(Collectors.toList());
        return res;
    }
}
