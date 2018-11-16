package com.ideas2it.ecommerce.service;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.model.Product;
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
     * Fetches the list of categories in which products are sold in the store.
     * </p>
     * 
     * @return Returns the list of categories available. Otherwise, returns an
     *         empty Object.
     */
    public List<Category> getAllCategories() throws EcommerceException;

    /**
     * <p>
     * Getting list of products from e-commerce web-site based on category id
     * and product name.
     * </p>
     * 
     * @param categoryId  Needed for which category products wants to search.
     * @param productName Needed for search product.
     * @return List<Product> Returns list of products.
     */
    public List<Product> searchProduct(Integer categoryId, String productName)
            throws EcommerceException;

    /**
     * <p>
     * Getting list of products from e-commerce web-site based on category id
     * and product name.
     * </p>
     * 
     * @param productName Needed for search product.
     * @return List<Product> Returns list of products.
     */
    public List<Product> searchProduct(String productName)
            throws EcommerceException;

    /**
     * <p>
     * Fetches the entire list of products available in the store.
     * </p>
     * 
     * @return Returns the list of Products available. Otherwise, returns an
     *         empty Object.
     */
    public List<Product> getAllProducts() throws EcommerceException;

    /**
     * <p>
     * Getting list of products from e-commerce web-site based on category id
     * and product name.
     * </p>
     * 
     * @param productId Needed for search product.
     * @return Product Returns list of products.
     */
    public Product searchProduct(Integer productId) throws EcommerceException;

    /**
     * <p>
     * This method is used to place multiple orders to customer in e-commerce
     * web-site for different products with different order.
     * </p>
     * 
     * @param Order Needed for inserting order details
     * @return List<OrderItem> If orders added to database returns null,
     *         otherwise returns list of orderItems which ran out of stock.
     */
    public List<OrderItem> addOrder(Order order) throws EcommerceException;

    /**
     * <p>
     * This method is used to cancel placed particular order from e-commerce web-site.
     * </p>
     * 
     * @param orderItems Needed for cancel the particular order.
     * @returns Boolean it returns true, if order cancelled from e-commerce,
     *          otherwise false.
     */
    public Boolean cancelOrder(List<OrderItem> orderItems)
            throws EcommerceException;

    /**
     * <p>
     * Getting list of order item detail from e-commerce web-site based on
     * list of order item id's.
     * </p>
     *
     * @param orderItemIds needed for get list order item id's.
     * @return List<OrderItem> Return the list of order items.
     */
    public List<OrderItem> getOrderItemsByIds(List<Integer> orderItemIds)
            throws EcommerceException;
    
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
    public List<WarehouseProduct> getWarehouseProductsByIds(
            List<Integer> warehouseProductIds) throws EcommerceException;

}
