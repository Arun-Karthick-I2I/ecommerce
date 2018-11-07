package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.OrderDao;
import com.ideas2it.ecommerce.dao.impl.OrderDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.service.OrderService;

public class OrderServiceImpl implements OrderService {
private OrderDao orderDao = new OrderDaoImpl();
    
    /**
     * {@inheritDoc}
     */
    public List<Order> getOrders() throws EcommerceException { 
        return orderDao.getOrders(); 
    }
    
    /**
     * {@inheritDoc}
     */
    public Order searchById(Integer id) throws EcommerceException {
        return orderDao.getById(id);
    }
    
}
