package com.ideas2it.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.common.enums.ORDER_STATUS;
import com.ideas2it.ecommerce.dao.OrderItemDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.session.SessionManager;

/**
 * <p>
 * The {@code OrderItemDaoImpl} class implements OrderItemDao interface. It
 * provides order items related operations that can be performed to a e-commerce
 * Store.
 * </p>
 *
 * @author Arun Karthick.J
 */
public class OrderItemDaoImpl implements OrderItemDao {

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean update(List<OrderItem> orderItems)
            throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();

            for (OrderItem orderItem : orderItems) {
                session.update(orderItem);
            }
            transaction.commit();

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = Constants.MSG_UPDATE_ORDER_ITEMS_FAIL
                    + Constants.SPACE + Constants.LABEL_ORDER_ITEM_ID
                    + Constants.COLON_SYMBOL + orderItems;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(
                    Constants.MSG_UPDATE_WAREHOUSE_PRODUCT_FAIL);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<OrderItem> getByIds(
            List<Integer> orderItemIds) throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder
                    .createQuery(OrderItem.class);
            Root<OrderItem> root = criteriaQuery.from(OrderItem.class);
            criteriaQuery.select(root)
                    .where(root.get(Constants.LABEL_ID).in(orderItemIds));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SEARCH_ORDER_ITEMS_FAIL
                    + Constants.SPACE + Constants.LABEL_ORDER_ITEM_ID
                    + Constants.COLON_SYMBOL + orderItemIds;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_SEARCH_ORDER_ITEMS_FAIL);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<OrderItem> getByWarehouseProductIds(
            List<Integer> warehouseProductIds) throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder
                    .createQuery(OrderItem.class);
            Root<OrderItem> root = criteriaQuery.from(OrderItem.class);
            criteriaQuery.select(root)
                    .where(root.get(Constants.LABEL_WAREHOUSE_PRODUCT)
                            .in(warehouseProductIds));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SEARCH_ORDER_ITEMS_FAIL
                    + Constants.SPACE + Constants.LABEL_WAREHOUSE_PRODUCT
                    + Constants.COLON_SYMBOL + warehouseProductIds;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_SEARCH_ORDER_ITEMS_FAIL);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<OrderItem> getByStatus(List<Integer> warehouseProductIds,
            ORDER_STATUS status) throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder
                    .createQuery(OrderItem.class);
            Root<OrderItem> root = criteriaQuery.from(OrderItem.class);
            Predicate[] predicates = new Predicate[2];
            predicates[0] = root.get(Constants.LABEL_WAREHOUSE_PRODUCT)
                    .in(warehouseProductIds);
            predicates[1] = criteriaBuilder
                    .equal(root.get(Constants.LABEL_STATUS), status);
            criteriaQuery.select(root).where(predicates);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SEARCH_ORDER_ITEMS_FAIL
                    + Constants.SPACE + Constants.LABEL_WAREHOUSE_PRODUCT
                    + Constants.COLON_SYMBOL + warehouseProductIds
                    + Constants.SPACE + Constants.LABEL_STATUS
                    + Constants.COLON_SYMBOL + status;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_SEARCH_ORDER_ITEMS_FAIL);
        }
    }
}
