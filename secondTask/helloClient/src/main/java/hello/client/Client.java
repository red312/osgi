package hello.client;

import hello.service.Greetings;
import org.osgi.framework.*;
public class Client implements BundleActivator {
    public void start(BundleContext ctx) {
        ServiceReference<?> ref =
                ctx.getServiceReference(Greetings.class.getName());
        ((Greetings) ctx.getService(ref)).sayHello();
    }
    public void stop(BundleContext ctx) {}
}