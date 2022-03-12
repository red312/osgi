package ru.ifmo.dre.command.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import ru.ifmo.dre.command.Command;
import ru.ifmo.dre.service.NewsService;

import java.util.ArrayList;
import java.util.List;

@Component(
        service = Command.class,
        immediate = true,
        property = {
                "osgi.command.scope=news",
                "osgi.command.function=stats"
        }
)
public class NewsCommandImpl implements Command {
    private List<NewsService> newsList;
    @Reference(
            service = NewsService.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            bind = "bindService",
            unbind = "unbindService"
    )
    protected void bindService(NewsService newsService){
        if (newsList == null) {
            newsList = new ArrayList<>();
        }
        newsList.add(newsService);
    }
    protected void unbindService(NewsService newsService){
        newsList.remove(newsService);
    }
    public void stats(){
        if (newsList == null || newsList.isEmpty() ){
            System.out.println("News services not found!");
            return;
        }
        for (NewsService ns : newsList){
            System.out.println(ns.getNewsName());
        }
    }
    public void stats(String name){
        for (NewsService ns : newsList){
            if (ns.getNewsName().equals(name)){
                System.out.println(ns.getTopWords());
                return;
            }
        }
        System.out.println("Source not found!");
    }
}
