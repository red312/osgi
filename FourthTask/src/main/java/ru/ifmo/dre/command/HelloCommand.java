package ru.ifmo.dre.command;

import org.osgi.framework.BundleContext;

public class HelloCommand {

    private BundleContext bundleContext;

    public HelloCommand(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    public void hello(){
        System.out.println("Enter param");
    }

    public void hello(String param){
        System.out.println("Hello, " + param);
    }
}
