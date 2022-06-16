package osgi.service.client;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import osgi.service.printer.IPrinter;

public class ClientActivator implements BundleActivator, ServiceListener
{
    private RunnablePrinter             runnablePrinter  = null;
    private BundleContext               bundleContext    = null;
    private ServiceReference<IPrinter>  serviceReference = null;
    //---------------------------------------------------------------
    @Override
    public void start(BundleContext context) throws Exception
    {
        runnablePrinter = new RunnablePrinter();

        this.bundleContext = context;
        serviceReference = (ServiceReference<IPrinter>)
                context.getServiceReference(IPrinter.class.getName());
        if (serviceReference != null) {
            IPrinter printer = (IPrinter)
                    context.getService(serviceReference);
            if (printer != null) {
                runnablePrinter.setPrinterService(printer);
                runnablePrinter.start();
            }
        }
        context.addServiceListener(this, "(objectClass=" +
                IPrinter.class.getName() + ")");
        System.out.println("start : service-client");
    }
    //---------------------------------------------------------------
    @Override
    public void stop(BundleContext context) throws Exception
    {
        if (serviceReference != null)
            context.ungetService(serviceReference);

        context.removeServiceListener(this);
        System.out.println("stop : service-client");
    }
    //---------------------------------------------------------------
    @Override
    public void serviceChanged(ServiceEvent serviceEvent)
    {
        switch (serviceEvent.getType()) {
            case ServiceEvent.UNREGISTERING :
                runnablePrinter.stop();
                bundleContext.ungetService(
                        serviceEvent.getServiceReference());
                break;
            case ServiceEvent.REGISTERED :
                IPrinter printer = (IPrinter) bundleContext.getService(
                        serviceEvent.getServiceReference());
                if (printer != null) {
                    runnablePrinter.setPrinterService(printer);
                    runnablePrinter.start();
                }
                break;
        }
    }
    //---------------------------------------------------------------
}