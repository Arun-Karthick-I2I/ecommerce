package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.Customer;

public interface AdminService {
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    List<Order> getOrders() throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    Order searchByOrderId(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param status
     * @return
     */
    List<Customer> getCustomers(Boolean status) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @param status
     * @return
     */
    Customer searchByCustomerId(Integer id, Boolean status) 
        throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @param status
     * @return
     */
    List<Customer> searchByCustomerName(String name, Boolean status) 
            throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param customer
     * @return
     */
    Boolean deleteCustomer(Customer customer) throws EcommerceException;
    
    /**
     * 
     * @return
     */
    List<Seller> getSellers() throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    Seller searchBySellerId(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @return
     */
    List<Seller> searchBySellerName(String name) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param seller
     * @return
     */
    Boolean deleteSeller(Seller seller) throws EcommerceException;
}
