package org.service.client;

import org.service.printer.IPrinter;

public class RunnablePrinter implements Runnable
{
    private final   int TWO_SECS = 3000;
    private static  int counter  = 0;
    private boolean stop;

    private IPrinter service;

    void setPrinterService(IPrinter service)
    {
        this.service = service;
    }

    void start()
    {
        stop = false;
        new Thread(this).start();
    }
    void stop()
    {
        stop = true;
    }

    public void run() {
        while (!stop) {
            service.print("hello [" +
                    String.valueOf(++counter) + "] ...");
            try {
                Thread.sleep(TWO_SECS);
                if (counter == 5) {
                    counter = 0;
                    stop = true;
                }
            } catch (InterruptedException e) {
                stop = true;
            }
        }
    }
}