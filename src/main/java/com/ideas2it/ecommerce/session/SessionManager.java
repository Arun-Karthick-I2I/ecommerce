package com.ideas2it.ecommerce.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.logger.EcommerceLogger;

/**
 * <p>
 * The {@code SessionManager} Class is used to a create a singleton object for
 * the session factory and provides access to it.
 * </p>
 */
public class SessionManager {
    private static SessionManager sessionManager;
    private static SessionFactory sessionFactory;

    private SessionManager() {
        try {
            sessionFactory = (new Configuration())
                    .configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable cause) {
            EcommerceLogger.error(Constants.MESSAGE_SESSION_FACTORY_FAIL,
                    cause);
        }
    }

    /**
     * <p>
     * Returns the session manager object that restricts the session factory
     * object to be a singleton object for the entire e-commerce application
     * </p>
     *
     * @return sessionManager Returns the sessionManager object that can access
     *         the intialised session factory.
     */
    public static synchronized SessionManager getInstance() {
        if (null == sessionManager) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    /**
     * <p>
     * Returns the session factory object intialised with the specified
     * hibernate configuration.
     * </p>
     *
     * @return sessionFactory Returns the session factory.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}