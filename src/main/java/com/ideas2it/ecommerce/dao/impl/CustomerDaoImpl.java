package com.ideas2it.ecommerce.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.Filter;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.session.SessionManager;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.common.Constants;

/**
 * <p>
 * This class is used to insert the new customer to ecommerce website, 
 * delete existing customer in the ecommerce website,
 *  retrieve all available customer details ecommerce website,
 * update existing customer detail in the ecommerce website.
 * </p>
 *
 * @author Anantharaj.S
 * 
 */
public class CustomerDaoImpl {

    private StringBuilder stringBuilder = new StringBuilder();

    /**
     * @(@inheritDoc)
     */
    public Boolean insertCustomer(Customer customer) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
        	session = SessionManager.getSession();
            transaction = session.beginTransaction();
            customer.setIsActive(Boolean.TRUE);
            session.save(customer);
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
    public Customer getCustomerByMobile(String mobile, Boolean status) throws EcommerceException {
        Transaction transaction = null;
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
                (Constants.LABEL_ISACTIVE), status)));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            for (Customer customer : customerCollection) {
                return customer;
            }
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.ERROR_SERACH_CUSTOMER, e);
            throw new EcommerceException(Constants.ERROR_SERACH_CUSTOMER);
        } finally {
            SessionManager.closeSession(session);
        }
        return null;
    }
    
    /** 
     * @(@inheritDoc)
     */
    public Customer getCustomerById(Integer id, Boolean status) throws EcommerceException {
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
                (Constants.LABEL_ISACTIVE), status)));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            for (Customer customer : customerCollection) {
                return customer;
            }
            return null;
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.ERROR_SERACH_CUSTOMER, e);
            throw new EcommerceException(Constants.MSG_SERVER_ERROR);
        } finally {
            SessionManager.closeSession(session);
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
    public List<Customer> getCustomers(Boolean status) throws EcommerceException {
        Session session = null;
        List<Customer> customers;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(Constants.LABEL_ISACTIVE),
                status));
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
