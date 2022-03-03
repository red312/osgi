package hello.client;

import hello.service.Greetings;
import org.osgi.framework.*;
public class Client implements BundleActivator {

    @Override
    public void start(BundleContext ctx) {
        ServiceReference<?> ref =
                ctx.getServiceReference(Greetings.class.getName());
        ((Greetings) ctx.getService(ref)).sayHello();
    }

    @Override
    public void stop(BundleContext ctx) {}
}