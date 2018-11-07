package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Seller;

/**
 * <p>
 * The {@code SellerService} interface provides the operations
 * that can be done by a seller. It provides the basic functions needed such
 * as register, find, view and update seller details.
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public interface SellerService {
    
    /**
     * <p>
     * Fetches the details from user and creates an entry or account for the
     * seller if no such account exists
     * </p>
     *
     * @param  seller
     *         Seller Object with details of a new seller for whom an 
     *         entry has to be created
     *
     * @return true  If a new account is created for the seller successfully.
     *         false If there is a problem in creating the account.
     */ 
    Boolean registerSeller(Seller seller) throws EcommerceException;

    /**
     * <p>
     * Checks for the presence of sellers with the given mobile number or 
     * email id
     * <p>
     *
     * @param  seller
     *         Seller with mobile number and email id whose presence needs
     *         to be checked
     *
     * @return Seller  If a seller with either same email or mobile number
     *                   exists
     *         null  If no such seller exists
     */
    Seller checkSellerExistence(Seller seller) throws EcommerceException;

    /**
     * <p>
     * Searches for the seller with the given mobile number
     * </p>
     * 
     * @param sellerId
     *        ID of the seller whose details are being searched for
     *
     * @return seller
     *         Returns the seller with the desired mobile number if it exist
     *         Returns null if no such seller exist.
     */
    Seller searchSeller(Integer sellerId) throws EcommerceException;

    /**
     * <p>
     * Fetches the details from user and checks for the correctness from the 
     * database and returns message accordingly.
     * </p>
     *
     * @param  userId
     *         User ID of the Seller who has to be logged in.
     *
     * @return seller  If the seller details are correct.
     *         null      If no such seller exists.
     */ 
    Seller searchSellerByUserId(Integer userId) throws EcommerceException;

    /**
     * <p>
     * Deletes the corresponding Seller details
     * </p>
     *
     * @param seller
     *        Seller whose details needs to be deleted.
     *
     * @return true  If the seller object is deleted successfully.
     *         false If the seller object is not deleted successfully.
     */
    Boolean deleteSeller(Seller seller) throws EcommerceException;

    /**
     * <p>
     * Updates the contents of the corresponding Seller.
     * </p>
     *
     * @param seller
     *        Seller whose details needs to be updated.
     *
     * @return true  If the details are updated successfully.
     *         false If the details are not updated successfully.
     */
    Boolean updateSeller(Seller seller) throws EcommerceException;

    /**
     * <p>
     * It fetches the list of dvdCategories available.
     * </p>
     *
     * @return categories
     *         Returns the list of available categories
     */
    List<Category> getCategories() throws EcommerceException;

    /**
     * <p>
     * Requests the findCategory function to find category with the specific ID.
     * </p>
     * 
     * @param categoryID
     *        Category ID whose details have to be found.
     *
     * @return Category    
     *         Returns the Category with the desired ID if it exist
     *         Returns null if no such category exist.
     */
    Category searchCategory(Integer categoryId) throws EcommerceException;

    /**
     * <p>
     * Provides the  list of all sellers with the specified name from the
     * DVDStore along with their order details.
     * </p>
     * 
     * @param sellerName
     *        name of the sellers whose details have to be found.
     *
     * @return sellers    
     *         Returns the list of sellers with their order details. 
     */
    List<Seller> getSellersByName(String sellerName) throws 
        EcommerceException;

    /**
     * <p>
     * Provides the total list of all sellers of the DVDStore along with 
     * their order details.
     * </p>
     * 
     * @return sellers    
     *         Returns the list of sellers with their order details. 
     */
    List<Seller> getAllSellers() throws EcommerceException;
}
