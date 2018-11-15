package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.common.enums.ORDER_STATUS;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.OrderItem;

/**
 * <p>
 * The {@code OrderItemDao} interface provides warehouse products related
 * operations that can be performed to a e-commerce Store. It provides options
 * to find and update orderItems from the store.
 * </p>
 *
 * @author Arun Karthick.J
 */
public interface OrderItemDao {
    Boolean update(List<OrderItem> orderItems) throws EcommerceException;

    List<OrderItem> getByIds(List<Integer> orderItemIds)
            throws EcommerceException;

    List<OrderItem> getByWarehouseProductIds(
            List<Integer> warehouseProductIds) throws EcommerceException;

    List<OrderItem> getByStatus(
            List<Integer> warehouseProductIds, ORDER_STATUS status) throws EcommerceException;

}
