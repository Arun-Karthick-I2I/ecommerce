package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.OrderDao;
import com.ideas2it.ecommerce.dao.impl.OrderDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.service.OrderService;

/**
 * <p>
 * This class provides basic functionalities such as get all 
 * available Orders, add new Order, delete an existing Category and
 * fetch an Order by specified. 
 * </p>
 * 
 * @author Pavihra.S
 *
 */
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

    /**
     * {@inheritDoc}
     */
    public Boolean addOrders(List<Order> orders) throws EcommerceException {
        return orderDao.addOrders(orders);
    }
    
    /**
     * {@inheritDoc}
     */
    public Boolean deleteOrder(Order order) throws EcommerceException {
        return orderDao.deleteOrder(order);
    }
}
