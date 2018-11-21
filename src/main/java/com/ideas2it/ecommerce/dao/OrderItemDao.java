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

    /**
     * <p>
     * Updates the list of order items and return true or false.
     * </p>
     *
     * @param orderItems List of order items which needs to be updated.
     * @return true If the order item is successfully updated. Returns false If
     *         the order items is not updated successfully.
     */
    Boolean update(List<OrderItem> orderItems) throws EcommerceException;

    /**
     * <p>
     * Returns the list of order items for the ids specified.
     * </p>
     *
     * @param orderItemIds List of ids whose corresponding order items needs to
     *                     be fetched
     * @return orderItems Returns the list of order items corresponding to the
     *         order item ids.
     */
    List<OrderItem> getByIds(List<Integer> orderItemIds)
            throws EcommerceException;

    /**
     * <p>
     * Returns the list of order items for the warehouse product ids specified.
     * </p>
     *
     * @param warehouseProductIds List of warehouse product ids whose
     *                            corresponding order items needs to be fetched
     * @return orderItems Returns the list of order items corresponding to the
     *         warehouse product ids.
     */
    List<OrderItem> getByWarehouseProductIds(
            List<Integer> warehouseProductIds) throws EcommerceException;

    /**
     * <p>
     * Returns the list of order items for the warehouse product ids and status
     * specified
     * </p>
     *
     * @param warehouseProductIds List of warehouse product ids whose
     *                            corresponding order items needs to be fetched
     * @param status              Status of the Order Item (i.e Dispatched or
     *                            Delivered or Cancelled or...)
     * @return orderItems Returns the list of order items corresponding to the
     *         warehouse product ids and status.
     */
    List<OrderItem> getByStatus(
            List<Integer> warehouseProductIds, ORDER_STATUS status) throws EcommerceException;

}
