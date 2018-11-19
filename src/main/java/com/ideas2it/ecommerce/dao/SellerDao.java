package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Seller;

/**
 * <p>
 * The {@code SellerDao} interface provides seller related operations that can
 * be performed to a e-commerce Store. It provides the basic operations such as
 * add, remove and find sellers from the store.
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public interface SellerDao {

    /**
     * <p>
     * Adds a Seller into the Database
     * </p>
     *
     * @param seller
     *        Seller who has to be added to the Database
     *
     * @return message
     *         Returns true if Seller details is successfully created  
     *         and added to the database else Returns false. 
     */    
    Boolean addSeller(Seller seller) throws EcommerceException;
    
    /**
     * <p>
     * Deletes a Seller from the Database
     * </p>
     *
     * @param seller
     *        Seller who has to be deleted from the Database
     *
     * @return message
     *         Returns true if Seller details is successfully created  
     *         and added to the database else Returns false. 
     */    
    Boolean deleteSeller(Seller seller) throws EcommerceException;
    
    /**
     * <p>
     * Updates the Seller details in the Database
     * </p>
     *
     * @param seller
     *        Seller which has to be updated in the Database
     *
     * @return message
     *         Returns true if Seller is successfully updated  
     *         in the database else Returns false. 
     */
    Boolean updateSeller(Seller seller) throws EcommerceException;
    
    /**
     * <p>
     * Searches through the database based on mobile number and email id
     * </p>
     * 
     * @param seller
     *        Seller whose presence has to be searched.
     *
     * @return seller    
     *         Returns the Seller with the either the same mobile number or
     *         email id exists
     *         Returns null if no such Seller exist.
     */
    Seller checkSellerPresence(Seller seller) throws EcommerceException;
    
    /**
     * <p>
     * Searches through the database based on Seller ID.
     * </p>
     * 
     * @param sellerId
     *        ID of the Seller whose details have to be found.
     *
     * @return seller    
     *         Returns the Seller with the desired id if it exist
     *         Returns null if no such Seller exist.
     */
    Seller getSeller(Integer sellerId) throws EcommerceException;
    
    /**
     * <p>
     * Searches through the database based on user ID.
     * </p>
     * 
     * @param userId
     *        User ID of the Seller whose details have to be found.
     *
     * @return Seller    
     *         Returns the Seller with the desired user id if it exist
     *         Returns null if no such Seller exist.
     */
    Seller getSellerByUserId(Integer userId) throws EcommerceException;
    
    /**
     * <p>
     * Provides the list of Sellers present in the database.
     * </p>
     *
     * @param sellerName
     *        name of Sellers whose details have to be found.
     *
     * @return sellers
     *         Returns the list of all Sellers of the DVD Store.
     */
    List<Seller> getSellersByName(String sellerName) throws EcommerceException;
    
    /**
     * <p>
     * Provides the list of Sellers present in the database.
     * </p>
     * 
     * @return sellers    
     *         Returns the list of all Sellers of the DVD Store.
     */
    List<Seller> getAllSellers() throws EcommerceException;
    
}
