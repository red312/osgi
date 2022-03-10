package ru.ifmo.dre.client;

import ru.ifmo.dre.service.Greetings;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Client implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference<?> ref =
                bundleContext.getServiceReference(Greetings.class.getName());
        if (ref != null) {
            ((Greetings) bundleContext.getService(ref)).sayHello();

        }
        else {
            System.out.println("Service not found");
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
    }

}
