package com.ideas2it.ecommerce.dao.impl;

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

public class CustomerDaoImpl {

    private StringBuilder stringBuilder = new StringBuilder();

    /**
     * @(inheritdoc)
     */
    public Boolean insertCustomer(Customer customer) throws EcommerceException {
    	SessionManager factory = SessionManager.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            customer.setStatus(Boolean.TRUE);
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
        	SessionManager.closeSession();
        }
    }
    

    /** 
     * @(inheritdoc)
     */
    public Customer getCustomerByMobile(String mobile, Boolean status) 
            throws DvdException {
        SessionFactoryManager factory = SessionFactoryManager.getInstance();
        SessionFactory sessionFactory = factory.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = 
                builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.and(builder.equal(root.get
                (DvdConstants.MOBILE), mobile), builder.equal(root.get
                (DvdConstants.STATUS), status)));
            Query query = session.createQuery(criteriaQuery);
            List<Customer> customerCollection = query.getResultList();
            for (Customer customer : customerCollection) {
                return customer;
            }
        } catch (HibernateException e) {
            logger.error(DvdConstants.ERROR_SERACH_CUSTOMER, e);
            throw new DvdException(DvdConstants.ERROR_SERACH_CUSTOMER);
        } finally {
            session.close();
        }
        return null;
    }
    
    
}
