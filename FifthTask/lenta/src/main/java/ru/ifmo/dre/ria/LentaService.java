package ru.ifmo.dre.ria;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import ru.ifmo.dre.service.AbstractNews;
import ru.ifmo.dre.service.NewsService;


import java.net.MalformedURLException;
import java.net.URL;

@Component(
        service = NewsService.class
)
@Designate(ocd = LentaService.OSGIConfig.class)
public class LentaService extends AbstractNews implements NewsService  {
    private String serviceURL;
    private int wordsNumber;

    @ObjectClassDefinition(name="Lenta", description="Lenta service")
    public @interface OSGIConfig {
        @AttributeDefinition(
                name = "URL",
                description = "Enter URL",
                type = AttributeType.STRING)
        String serviceURL() default "https://api.lenta.ru/rss";


        @AttributeDefinition(
                name = "Top words number",
                description = "Enter top words number",
                type = AttributeType.INTEGER)
        int wordsNumber() default 10;
    }

    @Override
    public String getName() {
        return "Lenta";
    }

    @Override
    public URL getUrl() throws MalformedURLException {
        return new URL(serviceURL);
    }

    @Override
    public int topWordsNumber(){
        return wordsNumber;
    }
    @Activate
    protected void activate(OSGIConfig serviceConfig){
        this.serviceURL = serviceConfig.serviceURL();
        this.wordsNumber = serviceConfig.wordsNumber();
    }

}
