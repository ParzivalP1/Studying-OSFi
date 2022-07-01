package org.softwerke.service.client;

import org.softwerke.service.printer.IPrinter;
import org.apache.felix.scr.annotations.*;

@Component(name = "Service client", immediate = true)
@Service(value = Object.class)
public class RunnablePrinter {
    @Reference
    IPrinter service;

    @Activate
    void activate()
    {
        System.out.println("Hello-client activated");
        for (int i = 1; i < 6; i++) {
            service.print("hello [" +
                    String.valueOf(++i) + "] ...");
        }
        service.print("hello");
    }
    @Deactivate
    void deactivate()
    {
        System.out.println("Hello-client deactivate");
    }
}
