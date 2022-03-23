package ru.ifmo.dre.ria;

import org.osgi.service.component.annotations.Component;
import ru.ifmo.dre.service.AbstractNews;
import ru.ifmo.dre.service.NewsService;


import java.net.MalformedURLException;
import java.net.URL;

@Component(
        service = NewsService.class
)
public class RiaService extends AbstractNews implements NewsService  {

    @Override
    public String getName() {
        return "RIA";
    }

    @Override
    public URL getUrl() throws MalformedURLException {
        return new URL("https://api.lenta.ru/rss");
    }
}
