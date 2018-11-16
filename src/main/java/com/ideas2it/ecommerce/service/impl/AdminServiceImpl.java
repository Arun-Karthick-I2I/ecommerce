package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.service.AdminService;
import com.ideas2it.ecommerce.service.CustomerService;
import com.ideas2it.ecommerce.service.OrderItemService;
import com.ideas2it.ecommerce.service.OrderService;
import com.ideas2it.ecommerce.service.ProductService;
import com.ideas2it.ecommerce.service.SellerService;
import com.ideas2it.ecommerce.service.WarehouseProductService;

/**
 * <p>
 * This class provides basic functionalities such as get all the Orders
 * placed by the Customer, fetch a specific Order by ID, viewing all the 
 * active Customers and Sellers, searching a specific Customer or Seller by 
 * their respective ID or by Name.
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class AdminServiceImpl implements AdminService{
    private OrderService orderService = new OrderServiceImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private SellerService sellerService = new SellerServiceImpl();
    private WarehouseProductService warehouseProductService = new WarehouseProductServiceImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> getOrders() throws EcommerceException {
        return orderService.getOrders();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order searchByOrderId(Integer id) throws EcommerceException {
        return orderService.searchById(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> getCustomers(Boolean status) throws EcommerceException {
        return customerService.getCustomers(status);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Customer searchByCustomerId(Integer id, Boolean status) 
            throws EcommerceException {
        return customerService.getCustomerById(id, status);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> searchByCustomerName(String name, Boolean status) 
            throws EcommerceException {
        return customerService.getCustomerByName(name, status);
    }
    
    /**
     * {@inheritDoc}
     */
    public Boolean deleteCustomer(Customer customer) throws EcommerceException {
        return customerService.deleteCustomer(customer);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Seller> getSellers() throws EcommerceException {
        return sellerService.getAllSellers();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Seller searchBySellerId(Integer id) throws EcommerceException {
        return sellerService.searchSeller(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Seller> searchBySellerName(String name) throws EcommerceException {
        return sellerService.getSellersByName(name);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteSeller(Seller seller) throws EcommerceException {
        return sellerService.deleteSeller(seller);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<WarehouseProduct> getProductsBySeller(Integer id) throws EcommerceException {
        return warehouseProductService.searchBySeller(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getWarehouseProductIdsBySeller(Integer id) throws EcommerceException {
        return warehouseProductService.getIdsBySeller(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderItem> searchOrderItemsByWarehouseProductIds(List<Integer> 
            warehouseProductIds) throws EcommerceException {
        return orderItemService.searchByWarehouseProductIds(warehouseProductIds);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getWarehouseProductIdsByProduct(Integer id) throws EcommerceException {
        return warehouseProductService.getIdsByProduct(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getProducts() throws EcommerceException {
        return productService.getProducts();
    }
}
