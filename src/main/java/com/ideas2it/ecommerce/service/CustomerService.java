package com.ideas2it.ecommerce.service;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.WarehouseProduct;

import java.util.List;

/**
 * <p>
 * CustomerDao interface class is used to insert the new customer, soft delete
 * on existing customer details from database, update existing customer detail
 * in the e-commerce web-site.
 * </p>
 * 
 * @author Anantharaj.S
 */
public interface CustomerService {

    /**
     * <p>
     * Adding new customer details to e-commerce website.
     * 
     * @param customer Needed for inserting customer details
     * @return Boolean If customer details added to database returns true,
     *         otherwise false
     *         </p>
     */
    public Boolean addCustomer(Customer customer) throws EcommerceException;

    /**
     * <p>
     * Getting particular customer detail from database based on mobile number.
     *
     * @param mobile Needed for check the customer details
     * @param status needed for check active or inactive
     * @return Customer Returns particular customer details from database
     *         </p>
     */
    public Customer getCustomerByMobile(String mobile, Boolean isActive)
            throws EcommerceException;

    /**
     * <p>
     * Getting particular customer detail from database based on customer id.
     *
     * @param id     Needed for check customer detail.
     * @param status needed for check active or inactive.
     * @return Customer Returns particular customer details form database.
     *         </p>
     */
    public Customer getCustomerById(Integer id, Boolean isActive)
            throws EcommerceException;

    /**
     * <p>
     * Getting particular customer detail from database based on customer id.
     * </p>
     * 
     * @param id     Needed for check customer detail.
     * @param status needed for check active or inactive.
     * @return Customer Returns particular customer details form database.
     */
    public Customer getCustomerByUserId(Integer userId)
            throws EcommerceException;

    /**
     * <p>
     * Getting particular customer detail from database based on customer id.
     * </p>
     * 
     * @param id     Needed for check customer detail.
     * @param status needed for check active or inactive.
     * @return List<Customer> Returns list of customer details from database.
     */
    public List<Customer> getCustomerByName(String name, Boolean isActive)
            throws EcommerceException;

    /**
     * <p>
     * Delete existing customer detail in the store, if available
     * </p>
     * 
     * @param customer Needed for deleting customer.
     * @param status   needed for check active or inactive.
     * @return Boolean If customer deleted in database returns true, otherwise
     *         false
     */
    public Boolean deleteCustomer(Customer customer) throws EcommerceException;

    /**
     * <p>
     * Update existing customer detail to database, using updated values
     * </p>
     * 
     * @param customer Needed for updating customer
     * @return Boolean If customer updated to store returns true, otherwise
     *         false
     */
    public Boolean updateCustomer(Customer customer) throws EcommerceException;

    /**
     * <p>
     * Getting all active customer details in e-commerce
     * </p>
     * 
     * @param status needed for check active or inactive customer
     * @return List<Customer> Returns list of customer details form database
     */
    public List<Customer> getCustomers(Boolean status)
            throws EcommerceException;

    /**
     * <p>
     * This method is used to place one order to customer in e-commerce web-site
     * for a particular product.
     * </p>
     * 
     * @param Order Needed for inserting order details
     * @return Boolean If order added to database returns true, otherwise false
     */
    public Boolean addOrder(Order order) throws EcommerceException;

    /**
     * <p>
     * 
     * This method is used to place multiple orders to customer in e-commerce
     * web-site for different products with different order.
     * </p>
     * 
     * @param List<Order> Needed for inserting order details
     * @return Boolean If orders added to database returns true, otherwise false
     */
    public Boolean addOrders(List<Order> order) throws EcommerceException;

    /**
     * <p>
     * Getting detail of the warehouse product from e-commerce web-site based on
     * id.
     * </p>
     *
     * @param id needed for get particular warehouse product
     * @return WarehouseProduct Return the warehouse product.
     */
    public WarehouseProduct getWarehouseProduct(Integer id)
            throws EcommerceException;

    /**
     * <p>
     * Getting list of warehouse products from e-commerce web-site based on
     * warehouse product id's.
     * </p>
     * 
     * @param warehouseProductIds needed for list of warehouse products
     * @return List<WarehouseProduct> Return the list of warehouse products.
     */
    public List<WarehouseProduct> getWarehouseProductsById(
            List<Integer> warehouseProductIds) throws EcommerceException;

}
