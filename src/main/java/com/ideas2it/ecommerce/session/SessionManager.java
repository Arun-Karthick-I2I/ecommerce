package com.ideas2it.ecommerce.session;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * <p>
 * The {@code SessionManager} creates an singleton session factory object 
 * and makes use of it for creating new session objects.
 * </p>
 * 
 * @author Arun Karthick.J
 */
public class SessionManager {
    private static SessionFactory sessionFactory;

    private SessionManager() {
    }

    /**
     * <p>
     * Returns the session object. It initialises the session factory object if
     * it is not initialised previously and then creates a session object.
     * </p>
     */
    public static Session getSession() throws EcommerceException {
        if (null == sessionFactory) {
            synchronized (SessionManager.class) {
                try {
                    sessionFactory = (new Configuration())
                            .configure("hibernate.cfg.xml")
                            .buildSessionFactory();
                } catch (Throwable cause) {
                    EcommerceLogger.error(
                            Constants.MESSAGE_SESSION_FACTORY_FAIL, cause);
                    throw new EcommerceException(
                            Constants.MESSAGE_SESSION_FACTORY_FAIL);
                }
            }
        }
        return sessionFactory.openSession();
    }

    /**
     * <p>
     * Closes the specified session object if it is not null.
     * </p>
     */
    public static void closeSession(Session session) {
        if (null != session) {
            session.close();
        }
    }
}