package com.ideas2it.ecommerce.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.session.SessionManager;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.dao.CustomerDao;

/**
 * <p>
 * This class is used to insert the new customer to e-commerce web-site, 
 * delete existing customer in the e-commerce web-site,
 * retrieve all available customer details e-commerce web-site,
 * update existing customer detail in the e-commerce web-site.
 * </p>
 *
 * @author Anantharaj.S
 * 
 */
public class CustomerDaoImpl implements CustomerDao {

    /**
     * @(@inheritDoc)
     */
    public Boolean insertCustomer(Customer customer) throws EcommerceException{
        Session session = null;
        Transaction transaction = null;
        try {
        	session = SessionManager.getSession();
            transaction = session.beginTransaction();
            customer.setIsActive(Boolean.TRUE);
            session.save(customer);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            EcommerceLogger.error
                (Constants.ERROR_INSERT_CUSTOMER + customer.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new EcommerceException
                (Constants.ERROR_INSERT_CUSTOMER + customer.getName());
        } finally {
        	SessionManager.closeSession(session);
        }
    }

    /** 
     * @(@inheritDoc)
     */
    public Customer getCustomerByMobile(String mobile, Boolean isActive) throws EcommerceException {
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = 
                builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (Constants.LABEL_MOBILE_NUMBER), mobile), builder.equal(root.get
                (Constants.LABEL_ISACTIVE), isActive)));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            for (Customer customer : customerCollection) {
                return customer;
            }
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.ERROR_SEARCH_CUSTOMER, e);
            throw new EcommerceException(Constants.ERROR_SEARCH_CUSTOMER);
        } finally {
            SessionManager.closeSession(session);
        }
        return null;
    }
    
    /** 
     * @(@inheritDoc)
     */
    public Customer getCustomerById(Integer id, Boolean isActive) throws EcommerceException {
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = 
                builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (Constants.LABEL_ID), id), builder.equal(root.get
                (Constants.LABEL_ISACTIVE), isActive)));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            for (Customer customer : customerCollection) {
                return customer;
            }
            return null;
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.ERROR_SEARCH_CUSTOMER, e);
            throw new EcommerceException(Constants.MSG_SERVER_ERROR);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * @{inheritDoc}
     */
    public Customer getCustomerByUserId(Integer userId, Boolean isActive) 
            throws EcommerceException {
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(
                Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get(Constants.LABEL_USER),
                userId), criteriaBuilder.equal(root.get(Constants.LABEL_ISACTIVE),
                isActive)));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH_CUSTOMER).
                append(Constants.SPACE).append(Constants.LABEL_USER_ID).
                append(Constants.COLON_SYMBOL).
                append(userId).toString();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    public List<Customer> getCustomerByName(String name, Boolean isActive) 
            throws EcommerceException {
        List<Customer> customers = null;
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(
                Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get(Constants.LABEL_NAME),
                name), criteriaBuilder.equal(root.get(Constants.LABEL_ISACTIVE),
                isActive)));
            Query query = session.createQuery(criteriaQuery);
            customers = query.getResultList();
            return customers;
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH_CUSTOMER).
                append(Constants.SPACE).append(Constants.LABEL_NAME).
                append(Constants.COLON_SYMBOL).
                append(name).toString();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(exceptionMessage);
        }
    }
    
    /** 
     * @(@inheritDoc)
     */
    public Boolean deleteCustomer(Customer customer) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();
            customer.setIsActive(Boolean.FALSE);
            session.update(customer);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            EcommerceLogger.error
                (Constants.ERROR_DELETE_CUSTOMER + customer.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new EcommerceException
                (Constants.ERROR_DELETE_CUSTOMER + customer.getName());
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /** 
     * @(@inheritDoc)
     */
    public Boolean updateCustomer(Customer customer) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            EcommerceLogger.error
                (Constants.ERROR_UPDATE_CUSTOMER + customer.getId(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new EcommerceException
                (Constants.ERROR_UPDATE_CUSTOMER + customer.getName());
        } finally {
            SessionManager.closeSession(session);
        }
    }
    
    /**
     * @(@inheritDoc)
     */
    public List<Customer> getCustomers(Boolean isActive) throws EcommerceException {
        Session session = null;
        List<Customer> customers = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(Constants.LABEL_ISACTIVE),
                isActive));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            customers = new ArrayList<Customer>(customerCollection);
            return customers;
        } catch (HibernateException e) {
            EcommerceLogger.error(e);
            throw new EcommerceException(Constants.ERROR_GET_CUSTOMERS);
        } finally {
            SessionManager.closeSession(session);
        }
    }
    
}
