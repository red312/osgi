package ru.ifmo.dre.client;

import org.osgi.service.component.annotations.*;
import ru.ifmo.dre.service.Greetings;

@Component(
        immediate = true
)
public class Client{
    private Greetings greet;
    @Reference(
            service = Greetings.class,
            cardinality = ReferenceCardinality.MANDATORY,
            unbind = "unbindService"
    )
    protected void bindService(Greetings greet){
        this.greet = greet;
    }
    protected void unbindService(Greetings greet){
        this.greet = null;
    }
    @Activate
    protected void onActivate(){
        greet.sayHello();
    }

}
