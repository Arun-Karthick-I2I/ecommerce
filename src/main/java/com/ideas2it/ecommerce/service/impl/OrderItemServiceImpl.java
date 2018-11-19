package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.common.enums.ORDER_STATUS;
import com.ideas2it.ecommerce.dao.OrderItemDao;
import com.ideas2it.ecommerce.dao.impl.OrderItemDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.service.OrderItemService;

/**
 * <p>
 * The {@code OrderItemServiceImpl} implements OrderItemService interface.It
 * provides options to find and update order items.
 * </p>
 *
 * @author Arun Karthick.J
 */

public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean updateOrderItems(List<OrderItem> orderItems)
            throws EcommerceException {
        return orderItemDao.update(orderItems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderItem> searchByIds(List<Integer> orderItemIds)
            throws EcommerceException {
        return orderItemDao.getByIds(orderItemIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderItem> searchByWarehouseProductIds(
            List<Integer> warehouseProductIds) throws EcommerceException {
        return orderItemDao
                .getByWarehouseProductIds(warehouseProductIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderItem> searchByStatus(
            List<Integer> warehouseProductIds, ORDER_STATUS status) throws EcommerceException {
        return orderItemDao
                .getByStatus(warehouseProductIds, status);
    }

}
