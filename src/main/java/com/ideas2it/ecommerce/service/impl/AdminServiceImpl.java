package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.OrderDao;
import com.ideas2it.ecommerce.dao.impl.OrderDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.service.AdminService;
import com.ideas2it.ecommerce.service.CustomerService;
import com.ideas2it.ecommerce.service.SellerService;

/**
 * <p>
 * 
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class AdminServiceImpl implements AdminService{
    private OrderDao orderDao = new OrderDaoImpl();
    private CustomerService customerService = new CustomerServiceImpl();
    private SellerService sellerService = new SellerServiceImpl();

    /**
     * {@inheritDoc}
     */
    public List<Order> getOrders() throws EcommerceException {
        return orderDao.getOrders();
    }

    /**
     * {@inheritDoc}
     */
    public Order searchByOrderId(Integer id) throws EcommerceException {
        return orderDao.getById(id);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Customer> getCustomers(Boolean status) throws EcommerceException {
        return customerService.getCustomers(status);
    }
    
    /**
     * {@inheritDoc}
     */
    public Customer searchByCustomerId(Integer id, Boolean status) 
            throws EcommerceException {
        return customerService.getCustomerById(id, status);
    }
    
    /**
     * {@inheritDoc}
     */
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
    public List<Seller> getSellers() throws EcommerceException {
        return sellerService.getAllSellers();
    }
    
    /**
     * {@inheritDoc}
     */
    public Seller searchBySellerId(Integer id) throws EcommerceException {
        return sellerService.searchSeller(id);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Seller> searchBySellerName(String name) throws EcommerceException {
        return sellerService.getSellersByName(name);
    }
    
    /**
     * {@inheritDoc}
     */
    public Boolean deleteSeller(Seller seller) throws EcommerceException {
        return sellerService.deleteSeller(seller);
    }
}
