package ru.ifmo.dre.command.impl;
import org.osgi.service.component.annotations.Component;
import ru.ifmo.dre.command.HelloCommand;

@Component(
        service = HelloCommand.class,
        immediate = true,
        property = {
                "osgi.command.scope=practice",
                "osgi.command.function=hello"
        }
)
public class HelloCommandImpl implements HelloCommand {
        public void hello(){
                System.out.println("Enter param");
        }
        public void hello(String... param){
                System.out.println("Should be only 1 param");
        }
        @Override
        public void hello(String param){
                System.out.println("Hello, " + param);
        }
}
