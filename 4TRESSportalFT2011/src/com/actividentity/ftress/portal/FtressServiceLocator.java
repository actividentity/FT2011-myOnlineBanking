package com.actividentity.ftress.portal;
 

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBHome;
import java.rmi.RemoteException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.xml.XMLConstants;

/**
 * This class is an implementation of the Service Locator pattern, and is responsible for connecting to and caching
 * remote <tt>EJBHome</tt> references.
 * 
 * @author spark
 * @since 3.2
 */
final class FtressServiceLocator {

    private final static FtressServiceLocator instance; // class property

    private final static String initErrorMsg; // class property

    private final Map ejbHomeMap; // instance property

    private final Context context; // instance property
    
    private final static String INITIAL_CONTEXT_FACTORY="ftressInitialContextFactory";
    
    private final static String PROVIDER_URL="ftressProviderUrl";
    
    static {
        FtressServiceLocator service = null;
        String error = null;
        
        try {

            Context ctx = null;
            //see if we are to connect top remote 4TRESS
            String initialContextFactory = System.getProperty(INITIAL_CONTEXT_FACTORY); 
            String providerUrl = System.getProperty(PROVIDER_URL);
            
            if( (initialContextFactory == null) || (providerUrl == null)) {            
            	initialContextFactory = "org.jnp.interfaces.NamingContextFactory";
            	providerUrl = "jnp://4tress:2099";
            	System.out.println("[FtressServiceLocator] No providerURL defined in java system properties 4TRESS instance used is " + providerUrl);
            	System.out.println("[FtressServiceLocator] Add the following java options when starting the service: " + INITIAL_CONTEXT_FACTORY + " and " + PROVIDER_URL );
            	java.util.Properties p = new java.util.Properties();
            	p.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
	            p.put(Context.PROVIDER_URL, providerUrl);
            	ctx = new InitialContext(p);
            } else {
            	System.out.println("[FtressServiceLocator] 4TRESS instance used is "+providerUrl);
	            java.util.Properties p = new java.util.Properties();
	            p.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
	            p.put(Context.PROVIDER_URL, providerUrl);
	            ctx = new InitialContext(p);
            }
            /*
            String initialContextFactory = "org.jnp.interfaces.NamingContextFactory";
            String providerUrl = "jnp://localhost.localdomain:1099";
            
            if( (initialContextFactory == null) || (providerUrl == null)) {            
            	System.out.println("[FtressServiceLocator] 4TRESS instance used is localhost");
	            ctx = new InitialContext(System.getProperties());
            } else {
            	System.out.println("[FtressServiceLocator] 4TRESS instance used is "+providerUrl);
	            java.util.Properties p = new java.util.Properties();
	            p.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
	            p.put(Context.PROVIDER_URL, providerUrl);
	            ctx = new InitialContext(p);
            }
			*/
            Map map = Collections.synchronizedMap(new HashMap());
            service = new FtressServiceLocator(ctx, map);
            
            
        } catch (NamingException e) {
            service = null;
            error = e.getMessage();
        }
        instance = service;
        initErrorMsg = error;
    }

    /**
     * Not used.
     */
    private FtressServiceLocator() {

        this(null, null);
    }

    /**
     * Initialises this object.
     */
    private FtressServiceLocator(final Context context, final Map ejbHomeMap) {

        super();
        this.context = context;
        this.ejbHomeMap = ejbHomeMap;
    }

    /**
     * @return the Singleton reference to this object.
     */
    final static FtressServiceLocator getInstance() {

        if (instance == null) {
            throw new IllegalStateException("4TRESS service locator failed to initialise due to: " + initErrorMsg);
        }
        return instance;
    }

    /**
     * Gets the EJB remote home factory. Clients need to cast to the appropriate EJBHome implementation class.
     * 
     * @param jndiHomeName
     * @param ejbClass
     * @return the EJB Home corresponding to the jndiHomeName
     * @throws InternalException
     */
    final EJBHome lookupRemoteHome(final String jndiHomeName, final Class ejbClass) throws RemoteException {

        Object obj = this.ejbHomeMap.get(jndiHomeName);
        if (obj == null) {
            try {
                Object objref = this.context.lookup(jndiHomeName);
                obj = PortableRemoteObject.narrow(objref, ejbClass);
            } catch (ClassCastException e) {
                throw new RemoteException("4TRESS service locator unable to cast remote object reference to "
                        + ejbClass.getName());
            } catch (NamingException e) {
                throw new RemoteException("4TRESS service locator failed to lookup remote object named "
                        + jndiHomeName);
            }
            this.ejbHomeMap.put(jndiHomeName, obj);
        }
        return (EJBHome) obj;
    }
}
