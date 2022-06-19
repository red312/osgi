package ru.ifmo.dre.FifthTask.service;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AbstractNewsServiceTest{
    @Test
    public void getAllWordsTest() throws MalformedURLException {
        AbstractNewsService absCls = Mockito.mock(
                AbstractNewsService.class,
                Mockito.CALLS_REAL_METHODS);
        Mockito.when(absCls.getUrl()).thenReturn(new URL("https://www.feedforall.com/sample-feed.xml"));

        List<String> test = new ArrayList<>(Arrays.asList("rss", "resources", "recommended",
                "desktop", "feed", "reader", "software",
                "recommended", "web", "based", "feed", "reader", "software"));
        assertEquals(test, absCls.getAllWords());
    }

    @Test
    public void getTopWordsTest() throws MalformedURLException {
        AbstractNewsService absCls = Mockito.mock(
                AbstractNewsService.class,
                Mockito.CALLS_REAL_METHODS);
        Mockito.when(absCls.getUrl()).thenReturn(new URL("https://www.feedforall.com/sample-feed.xml"));
        Mockito.when(absCls.topWordsNumber()).thenReturn(4);

        List<String> test = new ArrayList<>(Arrays.asList("feed", "software", "reader", "recommended"));

        assertEquals(test, absCls.getTopWords());
    }
}

