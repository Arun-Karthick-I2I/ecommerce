package com.ideas2it.ecommerce.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;

import com.ideas2it.ecommerce.session.SessionManager;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Category;

public class CategoryDaoImpl {
	
	/**
     * {@inheritDoc}
     */
    public List<Category> getCategories() throws EcommerceException {
        Session session = null;
        List<Category> categories;
        try {
            session = SessionManager.getSession();
            Query query = session.createQuery("from Category");
            categories = query.getResultList();
        } catch (HibernateException e) {       
        	EcommerceLogger.error("Unsuccessful display of Categories",e);
            throw new EcommerceException("Unsuccessful display of Categories");
        } finally {
        	SessionManager.closeSession(session);
        }
        return categories;    
    }
}
