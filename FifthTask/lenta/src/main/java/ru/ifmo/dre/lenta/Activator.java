package ru.ifmo.dre.lenta;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import ru.ifmo.dre.service.NewsService;

public class Activator implements BundleActivator{

    @Override
    public void start(BundleContext ctx) {
        ctx.registerService(NewsService.class.getName(),
                new LentaService(), null);
    }

    @Override
    public void stop(BundleContext ctx) {}
}