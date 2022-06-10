package com.company.osgi.service.printer;

import com.company.org.osgi.framework.BundleContext;
import com.company.org.osgi.framework.BundleActivator;
import com.company.org.osgi.framework.ServiceRegistration;

public class PrinterActivator implements BundleActivator
{
    private ServiceRegistration<IPrinter> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception
    {
        serviceRegistration = (ServiceRegistration<IPrinter>)
                context.registerService (IPrinter.class.getName(),
                        new PrinterRelease(), null);
        System.out.println("start : Hello OSGi World");
    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        serviceRegistration.unregister();
        System.out.println("stop : Bye OSGi World");
    }

}