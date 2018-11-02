package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.dao.CustomerDao;
import com.ideas2it.ecommerce.dao.impl.CustomerDaoImpl;

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
 *  @author Anantharaj
 * 
 */
public class CustomerServiceImpl {

        
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
    public Customer getCustomerByMobile(String mobile, Boolean status) throws EcommerceException  {
        return customerDao.getCustomerByMobile(mobile,status);
    }

    /** 
     * @(inheritDoc)
     */
    public Customer getCustomerById(Integer id, Boolean status) 
            throws EcommerceException  {
        return customerDao.getCustomerById(id, status);
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
           completeCustomerdetail.setMailId(customer.getMailId());
        return customerDao.updateCustomer(completeCustomerdetail);
    }

    /** 
     * @(inheritDoc)
     */
    public List<Customer> getCustomers(Boolean status) throws EcommerceException {
        return customerDao.getCustomers(status);
    }

}
