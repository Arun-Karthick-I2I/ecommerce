package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.User;

/**
 * <p>
 * The {@code UserService} provides the operations that can be done by a user.
 * It provides the basic functions needed such as register, login and update
 * user details.
 * </p>
 *
 * @author Arun Karthick.J
 */
public interface UserService {

    /**
     * <p>
     * Creates an entry for the customer and provides a corresponding message.
     * </p>
     *
     * @param customer Customer details along with login credentials which needs
     *                 to be added to the Database
     * @return message Returns true if User details is successfully added to the
     *         database else Returns false.
     */
    Boolean registerCustomer(Customer customer) throws EcommerceException;

    /**
     * <p>
     * Creates an entry for the seller and provides a corresponding message.
     * </p>
     *
     * @param seller Seller details along with login credentials which needs to
     *               be added to the Database
     * @return message Returns true if User details is successfully added to the
     *         database else Returns false.
     */
    Boolean registerSeller(Seller seller) throws EcommerceException;

    /**
     * <p>
     * Fetches the customer details based on the userId.
     * </p>
     *
     * @param userId User ID of the Customer who has to be logged in.
     * @return customer If the customer details are correct. null If no such
     *         customer exists.
     */
    Customer searchCustomer(Integer userId) throws EcommerceException;

    /**
     * <p>
     * Fetches the seller details based on the userId.
     * </p>
     *
     * @param userId User ID of the Seller who has to be logged in.
     * @return seller If the seller details are correct. null If no such seller
     *         exists.
     */
    Seller searchSeller(Integer userId) throws EcommerceException;

    /**
     * <p>
     * Searches for the presence of a particular userName and returns message
     * accordingly.
     * </p>
     * 
     * @param customer Customer who presence has to be searched.
     * @return true If userName is available false If userName is already taken
     *         by another user.
     */
    Boolean checkUserNameAvailability(String userName)
            throws EcommerceException;

    /**
     * <p>
     * Fetches the user details and validates it with the one present in the
     * database.
     * </p>
     * 
     * @param user User object which needs to be validated.
     * @return true If username and password matches. false If password doesn't
     *         match null If no such user exists for that role.
     */
    Boolean validateUser(User user) throws EcommerceException;
    
    /**
     * <p>
     * Fetches the entire list of products available in the store.
     * </p>
     * 
     * @return  Returns the list of Products available. Otherwise,
     *          returns an empty Object.
     */
    List<Product> getAllProducts() throws EcommerceException;

    /**
     * <p>
     * Fetches the list of categories in which products are sold in the store.
     * </p>
     * 
     * @return  Returns the list of categories available. Otherwise,
     *          returns an empty Object.
     */
    List<Category> getAllCategories() throws EcommerceException;

}