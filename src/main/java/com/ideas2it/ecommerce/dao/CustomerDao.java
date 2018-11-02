package com.ideas2it.ecommerce.dao;


import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Customer;

public interface CustomerDao {


    /** 
     * <p>
     * Adding new customer details to e-commerce website.
     * 
     * @param customer
     *        Needed for inserting customer details
     *
     * @return Boolean
     *        If customer details added to database returns true, otherwise false
     * </p> 
     */
    public Boolean insertCustomer(Customer customer)  throws EcommerceException ;

    /**
     * <p>
     * Getting particular customer detail from dvd store based on mobile number.
     *
     * @param mobile
     *        Needed for check the customer details
     *
     * @param status
     *        needed for check active or inactive
     *
     * @return Dvd
     *        Returns particular customer details from database
     * </p>
     */
    public Customer getCustomerByMobile(String mobile, Boolean status)
        throws EcommerceException ; 

    /**
     * <p>
     * Getting particular customer detail from dvd store based on customer id.
     *
     * @param id
     *        Needed for check customer detail from store.
     *
     * @param status
     *        needed for check active or inactive.
     *
     * @return Dvd
     *        Returns particular customer details form database.
     * </p>
     */
    public Customer getCustomerById(Integer id, Boolean status) 
        throws EcommerceException ;

    /** 
     * <p>
     * Delete existing customer detail in the store, if available
     * 
     * @param customer
     *        Needed for deleting customer.
     *
     * @return Boolean
     *        If customer deleted in database returns true, otherwise false
     * </p> 
     */
    public Boolean deleteCustomer(Customer customer) throws EcommerceException;

    /** 
     * <p>
     * Update existing customer detail in database, based on updated values
     * 
     * @param customer
     *        Needed for updating customer
     *
     * @return Boolean
     *        If customer updated to store returns true, otherwise false
     * </p> 
     */
    public Boolean updateCustomer(Customer customer) throws EcommerceException;

    /**
     * <p>
     * Getting all active customer details in ecommerce
     *
     * @param status
     *        needed for check active or inactive customer
     *
     * @return List<Customer>
     *        Returns set of customer details form dvd store
     * </p>
     */
    public List<Customer> getCustomers(Boolean status) throws EcommerceException ; 

}
