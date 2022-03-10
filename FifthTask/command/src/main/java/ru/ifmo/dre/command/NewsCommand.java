package ru.ifmo.dre.command;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import ru.ifmo.dre.service.NewsService;

import java.util.HashMap;

public class NewsCommand {
    private BundleContext bundleContext;
    private HashMap<String, NewsService> news = new HashMap<>();


    public NewsCommand(BundleContext bundleContext) throws InvalidSyntaxException {
        this.bundleContext = bundleContext;
        ServiceReference<?>[] refs =
                bundleContext.getServiceReferences(NewsService.class.getName(), null);
        for (ServiceReference<?> ref: refs){
            NewsService ns = (NewsService) bundleContext.getService(ref);
            news.put(ns.getNewsName(), ns);
        }
    }

    public void stats(){
        for (String key : news.keySet()){
            System.out.println(key);
        }
    }

    public void stats(String newsName) {
        if (news.containsKey(newsName)){
            System.out.println(news.get(newsName).getTopWords());
        }
        else{
            System.out.println("Source not found");
        }
    }
}
