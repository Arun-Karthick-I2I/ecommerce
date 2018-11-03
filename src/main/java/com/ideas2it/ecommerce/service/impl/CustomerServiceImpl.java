package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.dao.CustomerDao;
import com.ideas2it.ecommerce.dao.impl.CustomerDaoImpl;
import com.ideas2it.ecommerce.service.CustomerService;

/**
 * <p>
 * CustomerServiceImpl class is contains the operations of the customers
 * such as Insert new customer account, soft delete on existing customer
 * in e-commerce website, update existing customer details in database, 
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

}
