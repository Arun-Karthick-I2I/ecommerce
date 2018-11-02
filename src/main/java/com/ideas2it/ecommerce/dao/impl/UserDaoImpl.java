package com.ideas2it.ecommerce.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.dao.UserDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.User;
import com.ideas2it.ecommerce.session.SessionManager;

/**
 * <p>
 * The {@code UserDaoImpl} class implements UserDao interface. It provides 
 * the user related operations that can be performed in a e-commerce Store. 
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class UserDaoImpl implements UserDao {

    /**
     * @{inheritDoc}
     */
    public Boolean addUser(User user) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MSG_EXCEPTION_REGISTER).
                append(Constants.SPACE).append(Constants.LABEL_USERNAME).
                append(Constants.COLON_SYMBOL).append(user.getUserName()).
                toString();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(exceptionMessage);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * @{inheritDoc}
     */
    public User getUser(String userName) throws EcommerceException {
        try (Session session = SessionManager.getSession();) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(
                User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_USERNAME), userName));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MSG_EXCEPTION_SEARCH_USER).
                append(Constants.SPACE).append(Constants.LABEL_USERNAME).
                append(Constants.COLON_SYMBOL).append(userName).
                toString();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(exceptionMessage);
        }
    }
}