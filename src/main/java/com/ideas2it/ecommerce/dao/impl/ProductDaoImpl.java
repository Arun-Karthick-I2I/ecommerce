package com.ideas2it.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.dao.ProductDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.session.SessionManager;

public class ProductDaoImpl implements ProductDao {
    public static final String QUERY_GET_PRODUCT = "from Product";
    
    /**
     * {@inheritDoc}
     */
    public List<Product> getProducts() throws EcommerceException {
        Session session = null;
        List<Product> products;
        try {
            session = SessionManager.getSession();
            Query query = session.createQuery(QUERY_GET_PRODUCT);
            products = query.getResultList();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_PRODUCTS_DISPLAY_FAILURE, e);
            throw new EcommerceException(
                Constants.MSG_PRODUCTS_DISPLAY_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
        return products;
    }

    /**
     * {@inheritDoc}
     */
    public Product getById(Integer id) throws EcommerceException {
        Product product;
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = builder
                .createQuery(Product.class);
            Root<Product> root = criteria.from(Product.class);
            criteria.select(root).where(builder
                .equal(root.get(Constants.LABEL_ID), id));
            product = session.createQuery(criteria).uniqueResult();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_PRODUCT_ID + id + "\n"
                + Constants.MSG_PRODUCT_SEARCH_FAILURE, e);
            throw new EcommerceException(Constants.MSG_PRODUCT_SEARCH_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
        return product;
    }

    /**
     * {@inheritDoc}
     */
    public Product getByName(String name) throws EcommerceException {
        Product product;
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = builder
                .createQuery(Product.class);
            Root<Product> root = criteria.from(Product.class);
            criteria.select(root).where(builder
                .equal(root.get(Constants.LABEL_NAME), name));
            product = session.createQuery(criteria).uniqueResult();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_PRODUCT_NAME + name + "\n"
                + Constants.MSG_PRODUCT_SEARCH_FAILURE, e);
            throw new EcommerceException(Constants.MSG_PRODUCT_SEARCH_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
        return product;
    }
}
