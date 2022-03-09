package ru.ifmo.dre.command;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception{
        Hashtable props = new Hashtable();
        props.put("osgi.command.scope", "practice");
        props.put("osgi.command.function", "hello");
        context.registerService(HelloCommand.class.getName(), new HelloCommand(context), props);
    }

    public void stop(BundleContext context) throws Exception {
    }
}


