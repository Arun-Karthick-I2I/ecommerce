package com.ideas2it.ecommerce.dao.impl;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.dao.CategoryDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.session.SessionManager;

/**
 * <p>
 * This class provides basic functionalities such as get all 
 * Categories, add new Category if the Category doesn't already exist, 
 * update or delete an existing Category, fetch Category using ID or Name
 * specified. 
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class CategoryDaoImpl implements CategoryDao {
    public static final String QUERY_GET_CATEGORY = "from Category";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Category> getCategories() throws EcommerceException {
        Session session = null;
        List<Category> categories;
        try {
            session = SessionManager.getSession();
            Query query = session.createQuery(QUERY_GET_CATEGORY);
            categories = query.getResultList();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_CATEGORIES_DISPLAY_FAILURE, e);
            throw new EcommerceException(
                Constants.MSG_CATEGORIES_DISPLAY_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
        return categories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean insertCategory(Category category) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            EcommerceLogger.error(Constants.MSG_CATEGORY_NAME + category.getName()
                + "\n" + Constants.MSG_CATEGORY_INSERT_FAILURE, e);
            throw new EcommerceException(Constants.MSG_CATEGORY_INSERT_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteCategory(Integer id) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();
            Category category = (Category) session.get(Category.class, id);
            session.delete(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            EcommerceLogger.error(Constants.MSG_CATEGORY_ID + id + "\n"
                + Constants.MSG_CATEGORY_DELETE_FAILURE, e);
            throw new EcommerceException(Constants.MSG_CATEGORY_DELETE_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean updateCategory(Category newCategory)
            throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();
            Category category = (Category) session.get
                (Category.class,newCategory.getId());
            category.setName(newCategory.getName());
            session.update(category);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            EcommerceLogger.error(Constants.MSG_CATEGORY_ID + newCategory.getId()
                + "\n" + Constants.MSG_CATEGORY_UPDATE_FAILURE, e);
            throw new EcommerceException(
                Constants.MSG_CATEGORY_UPDATE_FAILURE + e.getMessage());
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category getById(Integer id) throws EcommerceException {
        Category category;
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteria = builder
                .createQuery(Category.class);
            Root<Category> root = criteria.from(Category.class);
            criteria.select(root).where(builder
                .equal(root.get(Constants.LABEL_ID), id));
            category = session.createQuery(criteria).uniqueResult();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_CATEGORY_ID + id + "\n"
                + Constants.MSG_CATEGORY_SEARCH_FAILURE, e);
            throw new EcommerceException(Constants.MSG_CATEGORY_SEARCH_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
        return category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category getByName(String name) throws EcommerceException {
        Category category;
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteria = builder
                .createQuery(Category.class);
            Root<Category> root = criteria.from(Category.class);
            criteria.select(root).where(builder
                .equal(root.get(Constants.LABEL_NAME), name));
            category = session.createQuery(criteria).uniqueResult();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_CATEGORY_NAME + name + "\n"
                + Constants.MSG_CATEGORY_SEARCH_FAILURE, e);
            throw new EcommerceException(Constants.MSG_CATEGORY_SEARCH_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
        return category;
    }
}
