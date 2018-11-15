package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.OrderDao;
import com.ideas2it.ecommerce.dao.impl.OrderDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.service.AdminService;
import com.ideas2it.ecommerce.service.CustomerService;
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
    private OrderDao orderDao = new OrderDaoImpl();
    private CustomerService customerService = new CustomerServiceImpl();
    private SellerService sellerService = new SellerServiceImpl();
    private WarehouseProductService warehouseProductService = new WarehouseProductServiceImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> getOrders() throws EcommerceException {
        return orderDao.getOrders();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order searchByOrderId(Integer id) throws EcommerceException {
        return orderDao.getById(id);
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
}
