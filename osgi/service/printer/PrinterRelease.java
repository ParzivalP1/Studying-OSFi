package com.company.osgi.service.printer;

public class PrinterRelease implements IPrinter
{
    public void print(String message)
    {
        System.out.println(message);
    }
}