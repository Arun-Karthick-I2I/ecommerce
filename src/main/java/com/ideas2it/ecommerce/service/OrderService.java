package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.OrderItem;

/**
 * <p>
 * This interface provides basic functionalities such as get all 
 * available Orders, add new Order, delete an existing Category and
 * fetch an Order by specified. 
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public interface OrderService {

    /**
     * <p>
     * Used to fetch the details of all the available Orders.
     * </p>
     * 
     * @return  Returns the list of Orders available. Otherwise,
     *          returns an empty Object.
     */
    List<Order> getOrders() throws EcommerceException;
    
    /**
     * <p>
     * Used to fetch the details of the Order for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Order whose details are to fetched.
     * @return      Returns the Order for the ID specified. Otherwise,
     *              returns an empty object.
     */
    Order searchById(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * Used to add new Order to the list using the inputs obtained from Customer.
     * It returns the Order Items, if the quantity available is less than the 
     * quantity ordered by the Customer. Otherwise, places the Order after 
     * deducting the Product quantity. If the Order has been successfully 
     * placed, returns an empty Object. Or else, increases the deducted Product
     * Quantity and then returns an empty Object.
     * </p>
     * 
     * @param   order   New Order to be placed.
     * @return          Returns an empty object, if the Order has been inserted 
     *                  successfully. Otherwise returns the list of Order Items, 
     *                  whose Quantity is less than the required quantity by the
     *                  Customer. 
     */
    List<OrderItem> addOrder(Order order) throws EcommerceException;
    
    /**
     * <p>
     * Used to delete the Order based on the ID specified.
     * </p>
     * 
     * @param   order  Order to be deleted.
     * @return         Returns true, if the Order has been deleted
     *                 successfully. Otherwise returns false, if the 
     *                 deletion is unsuccessful. 
     */
    Boolean deleteOrder(Order order) throws EcommerceException;
}
