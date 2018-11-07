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
import com.ideas2it.ecommerce.dao.SellerDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.session.SessionManager;

/**
 * <p>
 * The {@code SellerDaoImpl} class implements SellerDao interface. It provides 
 * the seller related operations that can be performed in a e-commerce Store. 
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class SellerDaoImpl implements SellerDao {
    private static final String QUERY_GET_SELLERS = "FROM Seller";

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean addSeller(Seller seller) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();

            session.save(seller);
            transaction.commit();

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = Constants.MSG_EXCEPTION_REGISTER
                    + Constants.SPACE + Constants.LABEL_USER_ID
                    + Constants.COLON_SYMBOL + seller.getUser().getId()
                    + Constants.SPACE + Constants.LABEL_SELLER
                    + Constants.COLON_SYMBOL + seller.getName()
                    + Constants.SPACE + Constants.LABEL_MOBILE_NUMBER
                    + Constants.COLON_SYMBOL + seller.getMobileNumber()
                    + Constants.SPACE + Constants.LABEL_EMAIL_ID
                    + Constants.COLON_SYMBOL + seller.getEmailId();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_EXCEPTION_REGISTER);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean deleteSeller(Seller seller) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();

            session.delete(seller);
            transaction.commit();

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = Constants.MSG_SELLER_DELETE_FAIL
                + Constants.SPACE + Constants.LABEL_SELLER_ID
                + Constants.COLON_SYMBOL + seller.getId()
                + Constants.SPACE + Constants.LABEL_NAME
                + Constants.COLON_SYMBOL + seller.getName()
                + Constants.SPACE + Constants.LABEL_MOBILE_NUMBER
                + Constants.COLON_SYMBOL + seller.getMobileNumber()
                + Constants.SPACE + Constants.LABEL_EMAIL_ID
                + Constants.COLON_SYMBOL + seller.getEmailId();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_SELLER_DELETE_FAIL);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean updateSeller(Seller seller) throws EcommerceException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionManager.getSession();
            transaction = session.beginTransaction();

            session.update(seller);
            transaction.commit();

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = Constants.MSG_SELLER_UPDATE_FAIL
                + Constants.SPACE + Constants.LABEL_SELLER_ID
                + Constants.COLON_SYMBOL + seller.getId()
                + Constants.SPACE + Constants.LABEL_NAME
                + Constants.COLON_SYMBOL + seller.getName()
                + Constants.SPACE + Constants.LABEL_MOBILE_NUMBER
                + Constants.COLON_SYMBOL + seller.getMobileNumber()
                + Constants.SPACE + Constants.LABEL_EMAIL_ID
                + Constants.COLON_SYMBOL + seller.getEmailId();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_SELLER_UPDATE_FAIL);
        } finally {
            SessionManager.closeSession(session);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller checkSellerPresence(Seller seller) throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Seller> criteriaQuery = 
                criteriaBuilder.createQuery(Seller.class);
            Root<Seller> root = criteriaQuery.from(Seller.class);
            Predicate mobilePredicate = criteriaBuilder.equal(
                root.get(Constants.LABEL_MOBILE_NUMBER), 
                    seller.getMobileNumber());
            Predicate emailIdPredicate = criteriaBuilder.equal( 
                root.get(Constants.LABEL_EMAIL_ID), seller.getEmailId());
            criteriaQuery.select(root).where(criteriaBuilder.or(mobilePredicate,
                emailIdPredicate));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_EXCEPTION_REGISTER
                + Constants.SPACE + Constants.LABEL_NAME
                + Constants.COLON_SYMBOL + seller.getName()
                + Constants.SPACE + Constants.LABEL_MOBILE_NUMBER
                + Constants.COLON_SYMBOL + seller.getMobileNumber()
                + Constants.SPACE + Constants.LABEL_EMAIL_ID
                + Constants.COLON_SYMBOL + seller.getEmailId();
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_EXCEPTION_REGISTER);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller getSeller(Integer sellerId) throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Seller> criteriaQuery = criteriaBuilder.createQuery(
                Seller.class);
            Root<Seller> root = criteriaQuery.from(Seller.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_ID), sellerId));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SELLER_SEARCH_FAIL
                    + Constants.SPACE + Constants.LABEL_SELLER_ID
                    + Constants.COLON_SYMBOL + sellerId;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_SELLER_SEARCH_FAIL);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller getSellerByUserId(Integer userId) throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Seller> criteriaQuery = criteriaBuilder.createQuery(
                Seller.class);
            Root<Seller> root = criteriaQuery.from(Seller.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_USER), userId));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SELLER_SEARCH_FAIL
                    + Constants.SPACE + Constants.LABEL_USER_ID
                    + Constants.COLON_SYMBOL + userId;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_SELLER_SEARCH_FAIL);
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Seller> getSellersByName(String sellerName)
            throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Seller> criteriaQuery = criteriaBuilder.createQuery(
                Seller.class);
            Root<Seller> root = criteriaQuery.from(Seller.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_NAME), sellerName));
            return session.createQuery(criteriaQuery).list();
        } catch (HibernateException e) {
            String exceptionMessage = Constants.MSG_SELLER_SEARCH_FAIL
                    + Constants.SPACE + Constants.LABEL_NAME
                    + Constants.COLON_SYMBOL + sellerName;
            EcommerceLogger.error(exceptionMessage, e);
            throw new EcommerceException(Constants.MSG_SELLER_SEARCH_FAIL);
        }
    }

    /**
     * @{inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Seller> getAllSellers() throws EcommerceException {
        try (Session session = SessionManager.getSession()) {
            return session.createQuery(QUERY_GET_SELLERS).list();
        } catch (HibernateException e) {
            EcommerceLogger.error(Constants.MSG_EXCEPTION_GET_SELLERS, e);
            throw new EcommerceException(
                Constants.MSG_EXCEPTION_GET_SELLERS);
        }
    }
}
