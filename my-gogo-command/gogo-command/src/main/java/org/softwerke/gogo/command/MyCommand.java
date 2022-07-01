package org.softwerke.gogo.command;
import org.apache.felix.scr.annotations.*;

@Component(name = "Command for Gogo Shell")
@Service(value = Object.class)
@Properties({
        @Property(name="osgi.command.scope",value="osgi-practice"),
        @Property(name="osgi.command.function",value="wel")
})
public class MyCommand {
    public void wel() {
        System.out.println("Welcome to OSGI world !");
    }

    public void wel(String nameParameter) {
        System.out.println("Welcome to OSGI world:" + nameParameter + "!");
    }
}
