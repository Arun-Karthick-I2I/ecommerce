package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;

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
}
