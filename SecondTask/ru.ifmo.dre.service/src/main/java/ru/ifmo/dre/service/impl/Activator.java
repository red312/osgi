package ru.ifmo.dre.service.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import ru.ifmo.dre.service.Greetings;

public class Activator implements BundleActivator{

    @Override
    public void start(BundleContext ctx) {
        ctx.registerService(Greetings.class.getName(),
                new GreetingsImpl(), null);
    }

    @Override
    public void stop(BundleContext ctx) {}
}
