package com.ideas2it.ecommerce.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.dao.CategoryDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.session.SessionManager;

public class CategoryDaoImpl implements CategoryDao {
	
	/**
     * {@inheritDoc}
     */
    public List<Category> getCategories() throws EcommerceException {
        Session session = null;
        List<Category> categories;
        try {
            session = SessionManager.getSession();
            Query query = session.createQuery(Constants.QUERY_GET_CATEGORY);
            categories = query.getResultList();
        } catch (HibernateException e) {       
        	EcommerceLogger.error(Constants.MESSAGE_CATEGORIES_DISPLAY_FAILURE,e);
            throw new EcommerceException(Constants.MESSAGE_CATEGORIES_DISPLAY_FAILURE);
        } finally {
        	SessionManager.closeSession(session);
        }
        return categories;    
    }
}
