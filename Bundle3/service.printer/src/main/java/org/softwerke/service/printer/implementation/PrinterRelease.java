package org.softwerke.service.printer.implementation;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import org.softwerke.service.printer.IPrinter;

@Component(immediate = true)
@Service(value = IPrinter.class)

public class PrinterRelease implements IPrinter{
    @Override
    public void print(String message)
    {
        System.out.println(message);
    }

    @Activate
    public void activate(){
        System.out.println("start : Hello OSGi World");
    }

    @Deactivate
    public void deactivate(){
        System.out.println("stop : Bye OSGi World");
    }
}
