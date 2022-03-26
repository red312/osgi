package ru.ifmo.dre.command.impl;

import org.osgi.service.component.annotations.*;
import ru.ifmo.dre.command.Command;
import ru.ifmo.dre.service.NewsService;

import java.util.HashMap;
import java.util.Map;

@Component(
        service = Command.class,
        immediate = true,
        property = {
                "osgi.command.scope=news",
                "osgi.command.function=stats"
        }
)

public class NewsCommandImpl implements Command {
    private HashMap<String, NewsService> newsMap = new HashMap<>();
    @Reference(
            service = NewsService.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            bind = "bindService",
            unbind = "unbindService"
    )
    protected void bindService(NewsService newsService){
        if (newsMap == null) {
            newsMap = new HashMap<>();
        }
        newsMap.put(newsService.getName(), newsService);
    }
    protected void unbindService(NewsService newsService){
        newsMap.remove(newsService.getName());
    }

    @Override
    public String stats(){
        if (newsMap.isEmpty() ){
            return "News services not found!";
        }
        StringBuilder news = new StringBuilder();
        for (Map.Entry<String, NewsService> ns : newsMap.entrySet()){
            news.append(ns.getKey()).append(" ");
        }
        return news.toString();
    }

    @Override
    public String stats(String name){
        if (newsMap.containsKey(name))
            return String.valueOf(newsMap.get(name).getTopWords());
        return "Source not found!";
    }

    public String stats(String... names){
        return "Bad usage - enter only one news name";
    }
}
