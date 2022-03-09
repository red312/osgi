package ru.ifmo.dre.service.impl;

import org.osgi.service.component.annotations.Component;
import ru.ifmo.dre.service.Greetings;

@Component(
        service = Greetings.class,
        immediate = true
)
public class GreetingsImpl implements Greetings {
    public void sayHello() {
        System.out.println("Hello world");
    }
}
