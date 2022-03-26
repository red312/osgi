package ru.ifmo.dre.aif;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
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
@Designate(ocd = AifService.OSGIConfig.class)
public class AifService extends AbstractNews implements NewsService  {

    @ObjectClassDefinition(name="AIF", description="AIF service")
    public @interface OSGIConfig {
        @AttributeDefinition(
                name = "URL",
                description = "Enter URL",
                type = AttributeType.STRING)
        String serviceURL() default "https://www.aif.ru/rss/news.php";


        @AttributeDefinition(
                name = "Top words number",
                description = "Enter top words number",
                type = AttributeType.INTEGER)
        int wordsNumber() default 10;
    }

    private String serviceURL;
    private int wordsNumber;


    @Override
    public String getName() {
        return "AIF";
    }

    @Override
    public URL getUrl() throws MalformedURLException {
        return new URL(serviceURL);

    }

    @Override
    public int topWordsNumber() {
        return wordsNumber;
    }

    @Activate
    protected void activate(OSGIConfig serviceConfig){
        this.serviceURL = serviceConfig.serviceURL();
        this.wordsNumber = serviceConfig.wordsNumber();
    }

}
