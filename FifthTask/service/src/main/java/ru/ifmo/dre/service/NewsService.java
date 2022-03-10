package ru.ifmo.dre.service;

import java.util.List;

public interface NewsService {
    String getNewsName();

    List<String> getTopWords();

    List<String> getAllWords();
}
