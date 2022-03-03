package hello.service.impl;

import hello.service.Greetings;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
public class Activator implements BundleActivator{

    @Override
    public void start(BundleContext ctx) {
        ctx.registerService(Greetings.class.getName(),
                new GreetingsImpl(), null);
    }

    @Override
    public void stop(BundleContext ctx) {}
}
