package hello.service.impl;

import hello.service.Greetings;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
public class Activator implements BundleActivator{
    public void start(BundleContext ctx) {
        ctx.registerService(Greetings.class.getName(),
                new GreetingsImpl(), null);
    }
    public void stop(BundleContext ctx) {}
}
