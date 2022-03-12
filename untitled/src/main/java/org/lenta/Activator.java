package lenta;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext ctx) {
        ctx.registerService(Service.class.getName(),
                new Lenta(), null);
        ServiceReference<?> ref =
                ctx.getServiceReference(Service.class.getName());
        ((Service) ctx.getService(ref)).getAllWords();
    }

    @Override
    public void stop(BundleContext ctx) {}
}

