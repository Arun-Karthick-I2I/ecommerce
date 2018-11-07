package com.ideas2it.ecommerce.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.dao.WarehouseProductDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.session.SessionManager;

public class WarehouseProductDaoImpl implements WarehouseProductDao {
    private static final String QUERY_GET_WAREHOUSE_PRODUCTS = "FROM WarehouseProduct";

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean addWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();

            session.save(warehouseProduct);
            transaction.commit();

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = Constants.MSG_ADD_WAREHOUSE_PRODUCT_FAIL
                    + Constants.SPACE + Constants.LABEL_SELLER
                    + Constants.COLON_SYMBOL
                    + warehouseProduct.getSeller().getId() + Constants.SPACE
                    + Constants.LABEL_PRODUCT_NAME + Constants.COLON_SYMBOL
                    + warehouseProduct.getProduct().getName();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(
                    Constants.MSG_ADD_WAREHOUSE_PRODUCT_FAIL);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean removeWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();

            session.delete(warehouseProduct);
            transaction.commit();

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = Constants.MSG_REMOVE_WAREHOUSE_PRODUCT_FAIL
                    + Constants.SPACE + Constants.LABEL_WAREHOUSE_PRODUCT_ID
                    + Constants.COLON_SYMBOL + warehouseProduct.getId();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(
                    Constants.MSG_REMOVE_WAREHOUSE_PRODUCT_FAIL);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean updateWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();

            session.update(warehouseProduct);
            transaction.commit();

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = Constants.MSG_UPDATE_WAREHOUSE_PRODUCT_FAIL
                    + Constants.SPACE + Constants.LABEL_WAREHOUSE_PRODUCT_ID
                    + Constants.COLON_SYMBOL + warehouseProduct.getId();
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
    public WarehouseProduct getWarehouseProductById(
            Integer warehouseProductId) throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<WarehouseProduct> criteriaQuery = criteriaBuilder
                    .createQuery(WarehouseProduct.class);
            Root<WarehouseProduct> root = criteriaQuery
                    .from(WarehouseProduct.class);
            criteriaQuery.select(root).where(criteriaBuilder
                    .equal(root.get(Constants.LABEL_ID), warehouseProductId));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SEARCH_WAREHOUSE_PRODUCT_FAIL
                    + Constants.SPACE + Constants.LABEL_WAREHOUSE_PRODUCT_ID
                    + Constants.COLON_SYMBOL + warehouseProductId;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<WarehouseProduct> getWarehouseProductByName(String name)
            throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<WarehouseProduct> criteriaQuery = criteriaBuilder
                    .createQuery(WarehouseProduct.class);
            Root<WarehouseProduct> root = criteriaQuery
                    .from(WarehouseProduct.class);
            criteriaQuery.select(root).where(criteriaBuilder.like(
                    root.get(Constants.LABEL_PRODUCT).get(Constants.LABEL_NAME),
                    name));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SEARCH_WAREHOUSE_PRODUCT_FAIL
                    + Constants.SPACE + Constants.LABEL_WAREHOUSE_PRODUCT_NAME
                    + Constants.COLON_SYMBOL + name;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<WarehouseProduct> getWarehouseProductsById(
            List<Integer> warehouseProductIds) throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<WarehouseProduct> criteriaQuery = criteriaBuilder
                    .createQuery(WarehouseProduct.class);
            Root<WarehouseProduct> root = criteriaQuery
                    .from(WarehouseProduct.class);
            criteriaQuery.select(root).where(
                    root.get(Constants.LABEL_ID).in(warehouseProductIds));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SEARCH_WAREHOUSE_PRODUCT_FAIL
                    + Constants.SPACE + Constants.LABEL_WAREHOUSE_PRODUCT_ID
                    + Constants.COLON_SYMBOL + warehouseProductIds;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(exceptionMessage);
        }
    }

    public List<WarehouseProduct> getWarehouseProductsBySeller(Seller seller) throws
            EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<WarehouseProduct> criteriaQuery = criteriaBuilder
                    .createQuery(WarehouseProduct.class);
            Root<WarehouseProduct> root = criteriaQuery
                    .from(WarehouseProduct.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                    root.get(Constants.LABEL_SELLER), seller));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_GET_WAREHOUSE_PRODUCTS_FAIL
                    + Constants.SPACE + Constants.LABEL_SELLER
                    + Constants.COLON_SYMBOL + seller.getId();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_GET_WAREHOUSE_PRODUCTS_FAIL);
        }
    }
    
    /**
     * @{inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<WarehouseProduct> getWarehouseProducts()
            throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            return session.createQuery(QUERY_GET_WAREHOUSE_PRODUCTS).list();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_GET_WAREHOUSE_PRODUCTS_FAIL, e);
            throw new EcommerceException(
                    Constants.MSG_GET_WAREHOUSE_PRODUCTS_FAIL);
        }
    }

}
