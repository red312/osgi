package ru.ifmo.dre.command;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception{
        Hashtable props = new Hashtable();
        props.put("osgi.command.scope", "news");
        props.put("osgi.command.function", "stats");
        context.registerService(NewsCommand.class.getName(), new NewsCommand(context), props);
    }

    public void stop(BundleContext context) throws Exception {
    }
}
