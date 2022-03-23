package ru.ifmo.dre.aif;

import org.osgi.service.component.annotations.Component;
import ru.ifmo.dre.service.AbstractNews;
import ru.ifmo.dre.service.NewsService;

import java.net.MalformedURLException;
import java.net.URL;

@Component(
        service = NewsService.class
)
public class AifService extends AbstractNews implements NewsService  {

    @Override
    public String getName() {
        return "AIF";
    }

    @Override
    public URL getUrl() throws MalformedURLException {
        return new URL("https://www.aif.ru/rss/news.php");
    }
}
