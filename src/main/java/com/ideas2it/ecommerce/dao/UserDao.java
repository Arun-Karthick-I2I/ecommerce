package com.ideas2it.ecommerce.dao;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.User;

/**
 * <p>
 * The {@code UserDao} interface provides user related operations that can
 * be performed to a Dvd Store. It provides the basic operations such as add, 
 * remove and find users from the dvdstore.
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public interface UserDao {

    /**
     * <p>
     * Adds a User into the Database
     * </p>
     *
     * @param user
     *        User details which needs to be added to the Database
     *
     * @return true   If User details is successfully added to the database.
     *         false  If user details is not added to the database. 
     */    
     Boolean addUser(User user) throws EcommerceException;

    /**
     * <p>
     * Searches through the database based on userName and Role.
     * </p>
     * 
     * @param user
     *        user whose presence has to be found.
     *
     * @return user    If an user with the specified details is found.
     *         null    If no such user exists for that role.
     */
    User getUser(User user) throws EcommerceException;
}