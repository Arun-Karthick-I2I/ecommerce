package com.ideas2it.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.dao.OrderDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.session.SessionManager;

public class OrderDaoImpl implements OrderDao {
public static final String QUERY_GET_ORDER = "from Order";
    
    /**
     * {@inheritDoc}
     */
    public List<Order> getOrders() throws EcommerceException {
        Session session = null;
        List<Order> orders;
        try {
            session = SessionManager.getSession();
            Query query = session.createQuery(QUERY_GET_ORDER);
            orders = query.getResultList();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_ORDERS_DISPLAY_FAILURE, e);
            throw new EcommerceException(
                Constants.MSG_ORDERS_DISPLAY_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
        return orders;
    }

    /**
     * {@inheritDoc}
     */
    public Order getById(Integer id) throws EcommerceException {
        Order order;
        Session session = null;
        try {
            session = SessionManager.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteria = builder
                .createQuery(Order.class);
            Root<Order> root = criteria.from(Order.class);
            criteria.select(root).where(builder
                .equal(root.get(Constants.LABEL_ID), id));
            order = session.createQuery(criteria).uniqueResult();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_ORDER_ID + id + "\n"
                + Constants.MSG_ORDER_SEARCH_FAILURE, e);
            throw new EcommerceException(Constants.MSG_ORDER_SEARCH_FAILURE);
        } finally {
            SessionManager.closeSession(session);
        }
        return order;
    }
}
