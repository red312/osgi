package ru.ifmo.dre.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface NewsService {
    String getName();
    List<String> getTopWords();
    URL getUrl() throws MalformedURLException;
    int topWordsNumber();
}

