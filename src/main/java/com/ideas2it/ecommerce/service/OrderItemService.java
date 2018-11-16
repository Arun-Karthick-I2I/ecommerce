package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.common.enums.ORDER_STATUS;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.OrderItem;

/**
 * <p>
 * The {@code OrderItemService} interface provides the operations that can be
 * done on order items. It provides the basic functions related to
 * the order items.
 * </p>
 *
 * @author Arun Karthick.J
 */
public interface OrderItemService {
    Boolean updateOrderItems(List<OrderItem> orderItems) throws EcommerceException;

    List<OrderItem> searchByIds(List<Integer> orderItemIds)
            throws EcommerceException;

    List<OrderItem> searchByWarehouseProductIds(List<Integer> warehouseProductIds)
            throws EcommerceException;

    List<OrderItem> searchByStatus(List<Integer> warehouseProductIds, ORDER_STATUS status)
            throws EcommerceException;
}
