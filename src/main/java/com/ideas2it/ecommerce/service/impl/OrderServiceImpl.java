package com.ideas2it.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ecommerce.dao.OrderDao;
import com.ideas2it.ecommerce.dao.impl.OrderDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.service.OrderService;
import com.ideas2it.ecommerce.service.WarehouseProductService;

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
private WarehouseProductService warehouseProductService 
    = new WarehouseProductServiceImpl();
    
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
    @Override
    public Boolean addOrder(Order order) throws EcommerceException {
        if (warehouseProductService.reduceQuantity(order)) {
            try {
                return orderDao.addOrder(order);
            } catch (EcommerceException e) {
                warehouseProductService.increaseQuantity(order);
            }
        }
        return Boolean.FALSE;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteOrder(Order order) throws EcommerceException {
        if (orderDao.deleteOrder(order)) {
            warehouseProductService.increaseQuantity(order);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
