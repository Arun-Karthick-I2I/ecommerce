package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.model.Customer;

/**
 * <p>
 * This interface provides the basic functionalities of the Admin such as
 * get all the Orders placed by the Customer, fetch a specific Order by ID, 
 * get all the active Customers and Sellers, get a specific Customer or Seller
 * by their respective ID or by Name. 
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public interface AdminService {
    
    /**
     * <p>
     * Used to fetch the details of all the Orders placed by several
     * Customers.
     * </p>
     * 
     * @return  Returns the list of Orders placed by the Customers.
     *          Otherwise, returns an empty Object.
     */
    List<Order> getOrders() throws EcommerceException;
    
    /**
     * <p>
     * Used to get the details of the Order placed for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Order whose details are to retrieved 
     * @return      Returns the Order for the ID specified. Otherwise, 
     *              returns an empty Object.
     */
    Order searchByOrderId(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * Used to fetch the details of all the active Customers.
     * </p>
     * 
     * @param   status  Status of the Customers checking whether it has been 
     *                  deleted or not
     * @return          Returns the list of Customers. Otherwise, returns an empty 
     *                  Object.
     */
    List<Customer> getCustomers(Boolean status) throws EcommerceException;
    
    /**
     * <p>
     * Used to retrieve the details of the Customer for the ID specified.
     * </p>
     * 
     * @param   id      ID of the Customer whose details are to fetched. 
     * @param   status  Status of the Customer checking whether it has been 
     *                  deleted or not
     * @return          Returns the Customer for the ID specified. Otherwise, 
     *                  returns an empty Object.
     */
    Customer searchByCustomerId(Integer id, Boolean status) 
        throws EcommerceException;
    
    /**
     * <p>
     * Used to retrieve the details of the Customer based on the name specified.
     * </p>
     * 
     * @param   name    Name of the Customer whose details are to fetched.
     * @param   status  Status of the Customer checking whether it has been 
     *                  deleted or not
     * @return          Returns the list of Customers for the name specified. 
     *                  Otherwise, returns an empty Object.
     */
    List<Customer> searchByCustomerName(String name, Boolean status) 
            throws EcommerceException;
    
    /**
     * <p>
     * Used to delete the Customer Account based on the ID specified.
     * </p>
     * 
     * @param   customer  Customer to be deleted 
     * @return            Returns true, if the customer Account has been 
     *                    deactivated successfully. Otherwise, returns false,
     *                    if deletion is unsuccessful.
     */
    Boolean deleteCustomer(Customer customer) throws EcommerceException;
    
    /**
     * <p>
     * Used to get the details of all the Sellers who would sell products
     * belonging to various Categories. 
     * </p>
     * 
     * @return  Returns the list of Sellers. Otherwise, returns an empty object.
     */
    List<Seller> getSellers() throws EcommerceException;
    
    /**
     * <p>
     * Used to get the details of the Seller for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Seller whose details are to retrieved.
     * @return  Returns the Seller for the ID specified. Otherwise, returns
     *          an empty object.
     */
    Seller searchBySellerId(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * Used to retrieve the details of the Seller based on the name specified.
     * </p>
     * 
     * @param   name  Name of the Seller whose details are to retrieved.
     * @return        Returns the list of Sellers for the name specified. 
     *                Otherwise, returns an empty object.
     */
    List<Seller> searchBySellerName(String name) throws EcommerceException;
    
    /**
     * <p>
     * Used to delete the Seller based on the ID specified.
     * </p>
     * 
     * @param   seller  Seller to be deleted. 
     * @return          Returns true, if the Seller has been deleted
     *                  successfully. Otherwise, returns false, if deletion is 
     *                  unsuccessful.
     */
    Boolean deleteSeller(Seller seller) throws EcommerceException;
    
    /**
     * <p>
     * Used to get the Products sold by the Seller for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Seller whose Products are to retrieved.
     * @return      Returns the Products sold by the Seller for the ID 
     *              specified. Otherwise, returns an empty object.
     */
    List<WarehouseProduct> getProductsBySeller(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    List<Integer> getWarehouseProductIds(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param warehouseProductIds
     * @return
     */
    List<OrderItem> searchOrderItemsByWarehouseProductIds(List<Integer> 
        warehouseProductIds) throws EcommerceException;
}
