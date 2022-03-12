package lenta;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Lenta implements Service  {

    public void getAllWords() {
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
        } catch (MalformedURLException e) {
            System.err.println("Can't find url for aif: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Problems with input from aif: " + e.getMessage());
        } catch (FeedException e) {
            e.printStackTrace();
        }
        System.out.println(res);
    }
}
