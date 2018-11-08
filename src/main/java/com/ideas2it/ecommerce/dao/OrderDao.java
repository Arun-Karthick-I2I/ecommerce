package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;

public interface OrderDao {
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     * @throws EcommerceException
     */
    List<Order> getOrders() throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     * @throws EcommerceException
     */
    Order getById(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param order
     * @return
     */
    Boolean addOrder(Order order) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param order
     * @return
     */
    Boolean deleteOrder(Order order) throws EcommerceException;
}
