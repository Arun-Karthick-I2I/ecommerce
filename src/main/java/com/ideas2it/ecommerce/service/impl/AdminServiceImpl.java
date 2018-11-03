package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.OrderDao;
import com.ideas2it.ecommerce.dao.impl.OrderDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.service.AdminService;

public class AdminServiceImpl implements AdminService{
    private OrderDao orderDao = new OrderDaoImpl();

    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    public List<Order> getOrders() throws EcommerceException {
        return orderDao.getOrders();
    }

    /**
     * <p>
     * 
     * </p>
     * 
     * @param id 
     * @return 
     */
    public Order searchByOrderId(Integer id) throws EcommerceException {
        return orderDao.getById(id);
    }
}
