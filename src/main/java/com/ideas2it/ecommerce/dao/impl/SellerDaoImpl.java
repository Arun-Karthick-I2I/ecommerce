package com.ideas2it.ecommerce.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.dao.SellerDao;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.session.SessionManager;

public class SellerDaoImpl implements SellerDao {

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
    public Boolean updateSeller(Seller seller) throws EcommerceException {
        return null;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller checkSellerPresence(Seller seller) throws EcommerceException {
        return null;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller getSeller(Integer userId) throws EcommerceException {
        return null;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller searchSeller(Integer sellerId) throws EcommerceException {
        return null;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Seller> getSellersByName(String sellerName)
            throws EcommerceException {
        return null;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Seller> getAllSellers() throws EcommerceException {
        return null;
    }
}
