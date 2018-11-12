package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.dao.CustomerDao;
import com.ideas2it.ecommerce.dao.impl.CustomerDaoImpl;
import com.ideas2it.ecommerce.service.CategoryService;
import com.ideas2it.ecommerce.service.CustomerService;
import com.ideas2it.ecommerce.service.OrderService;
import com.ideas2it.ecommerce.service.ProductService;
import com.ideas2it.ecommerce.service.WarehouseProductService;

/**
 * <p>
 * CustomerServiceImpl class is contains the operations of the customers
 * such as Insert new customer account, soft delete on existing customer
 * in e-commerce web-site, update existing customer details in database, 
 * searching the customer details...
 *
 * This class have the methods of the DvdStore functions
 * </p>
 * 
 *  @author Anantharaj.S
 * 
 */
public class CustomerServiceImpl implements CustomerService {
        
    private CustomerDao customerDao = new CustomerDaoImpl();
    private OrderService orderService = new OrderServiceImpl();
    private WarehouseProductService warehouseProductService = new WarehouseProductServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    
    /** 
     * @(inheritDoc)
     */
     public Boolean addCustomer( Customer customer ) throws EcommerceException {
         return customerDao.insertCustomer(customer);
     }

    /** 
     * @(inheritDoc)
     */
    public Customer getCustomerByMobile(String mobile, Boolean isActive) throws EcommerceException  {
        return customerDao.getCustomerByMobile(mobile,isActive);
    }

    /** 
     * @(inheritDoc)
     */
    public Customer getCustomerById(Integer id, Boolean isActive) 
            throws EcommerceException  {
        return customerDao.getCustomerById(id, isActive);
    }

    /** 
     * @(inheritDoc)
     */
    public List<Customer> getCustomerByName(String name, Boolean isActive) 
            throws EcommerceException  {
        return customerDao.getCustomerByName(name, isActive);
    }
    
    
    /** 
     * @(inheritDoc)
     */
    public Customer getCustomerByUserId(Integer userId) throws EcommerceException {
        return customerDao.getCustomerByUserId(userId, Boolean.TRUE);
    }
    
    /** 
     * @(inheritDoc)
     */
    public Boolean deleteCustomer(Customer customer) throws EcommerceException {
        return customerDao.deleteCustomer(customer);
    }

    /** 
     * @(inheritDoc)
     */
    public Boolean updateCustomer(Customer customer) throws EcommerceException {
           Customer completeCustomerdetail = customerDao.getCustomerById
               (customer.getId(), Boolean.TRUE);
           completeCustomerdetail.setName(customer.getName());
           completeCustomerdetail.setMobileNumber(customer.getMobileNumber());
           completeCustomerdetail.setEmailId(customer.getEmailId());
        return customerDao.updateCustomer(completeCustomerdetail);
    }

    /** 
     * @(inheritDoc)
     */
    public List<Customer> getCustomers(Boolean isActive) throws EcommerceException {
        return customerDao.getCustomers(isActive);
    }
    
    /**
     * @{inheritDoc}
     */
    @Override
    public List<Category> getAllCategories() throws EcommerceException {
        return categoryService.getCategories();
    }
    
    /**
     * @{inheritDoc}
     */
    @Override
    public List<Product> getAllProducts() throws EcommerceException {
        return productService.getProducts();
    }
    
    /** 
     * @(inheritDoc)
     */
     public List<Product> searchProduct(Integer categoryId, String productName) 
             throws EcommerceException {
         return productService.searchByCategory(categoryId,productName);
     }
     
    /** 
     * @(inheritDoc)
     */
     public List<Product> searchProduct(String productName) throws EcommerceException {
         return productService.searchByName(productName);
     }
     
    /** 
     * @(inheritDoc)
     */
     public Product searchProduct(Integer productId) throws EcommerceException {
         return productService.searchById(productId);
     }

     /**
      * @(inheritDoc)
      */
     public List<Order> addOrders(List<Order> orders) throws EcommerceException {
         return orderService.addOrders(orders);
     }
     
     /** 
      * @(inheritDoc)
      */
      public Boolean cancelOrder(Order order) throws EcommerceException {
          return orderService.deleteOrder(order);
      }

      /** 
     * @(inheritDoc)
     */
    public WarehouseProduct getWarehouseProduct(Integer id) throws EcommerceException {
        return warehouseProductService.searchById(id);
    }
    
    /** 
     * @(inheritDoc)
     */
    public List<WarehouseProduct> getWarehouseProductsByIds(List<Integer> warehouseProductIds) 
            throws EcommerceException {
        return warehouseProductService.getWarehouseProductsByIds(warehouseProductIds);
    }

}
