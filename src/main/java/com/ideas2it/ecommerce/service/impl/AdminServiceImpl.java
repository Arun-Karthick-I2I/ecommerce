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
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    public List<Order> getOrders() throws EcommerceException {
        return orderDao.getOrders();
    }

    /**
     * <p>
     * 
     * </p>
     * 
     * @param id 
     * @return 
     */
    public Order searchByOrderId(Integer id) throws EcommerceException {
        return orderDao.getById(id);
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param status
     */
    public List<Customer> getCustomers(Boolean status) throws EcommerceException {
        return customerService.getCustomers(status);
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     */
    public Customer searchByCustomerId(Integer id, Boolean status) 
            throws EcommerceException {
        return customerService.getCustomerById(id, status);
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @param status
     * @return
     * @throws EcommerceException
     */
    public List<Customer> searchByCustomerName(String name, Boolean status) 
            throws EcommerceException {
        return customerService.getCustomerByName(name, status);
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param customer
     * @return
     */
    public Boolean deleteCustomer(Customer customer) throws EcommerceException {
        return customerService.deleteCustomer(customer);
    }
    
    /**
     * <p>
     * 
     * </p>
     */
    public List<Seller> getSellers() throws EcommerceException {
        return sellerService.getAllSellers();
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    public Seller searchBySellerId(Integer id) throws EcommerceException {
        return sellerService.searchSeller(id);
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @return
     */
    public List<Seller> searchBySellerName(String name) throws EcommerceException {
        return sellerService.getSellersByName(name);
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param seller
     * @return
     */
    public Boolean deleteSeller(Seller seller) throws EcommerceException {
        return sellerService.deleteSeller(seller);
    }
}
