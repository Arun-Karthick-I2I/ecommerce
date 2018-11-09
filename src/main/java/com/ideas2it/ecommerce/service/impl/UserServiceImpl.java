package com.ideas2it.ecommerce.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.dao.UserDao;
import com.ideas2it.ecommerce.dao.impl.UserDaoImpl;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.User;
import com.ideas2it.ecommerce.service.CategoryService;
import com.ideas2it.ecommerce.service.CustomerService;
import com.ideas2it.ecommerce.service.ProductService;
import com.ideas2it.ecommerce.service.SellerService;
import com.ideas2it.ecommerce.service.UserService;

/**
 * <p>
 * The {@code UserServiceImpl} implements the UserService interface. 
 * It provides definitions for the functions declared in the UserService
 * Interface.
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class UserServiceImpl implements UserService {
    private static final String ALGORITHM = "SHA-256";
    private static final String SALT = "Its404NotFound";

    private UserDao userDao = new UserDaoImpl();
    private CustomerService customerService = new CustomerServiceImpl();
    private SellerService sellerService = new SellerServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    
    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean registerCustomer(Customer customer) throws EcommerceException {
        User user = customer.getUser();
        user.setPassword(generatePasswordHash(user.getPassword()));
        return customerService.addCustomer(customer);
    }
    
    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean registerSeller(Seller seller) throws EcommerceException {
        User user = seller.getUser();
        user.setPassword(generatePasswordHash(user.getPassword()));
        return sellerService.register(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Customer searchCustomer(Integer userId) throws EcommerceException {
        return customerService.getCustomerByUserId(userId);
    }

    @Override
    public Seller searchSeller(Integer userId) throws EcommerceException {
        return sellerService.searchSellerByUserId(userId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean checkUserNameAvailability(String userName) throws
            EcommerceException {
        return (null == userDao.getUser(userName));
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean validateUser(User user) throws EcommerceException {
        User existingUser = userDao.getUser(user.getUserName());
        Boolean message;
        if (null != existingUser) {
            if (((existingUser.getPassword()).equals(
                generatePasswordHash(user.getPassword()))) && 
                    (existingUser.getRole().equals(user.getRole()))) {
                user.setId(existingUser.getId());
                message = Boolean.TRUE;
            } else {
                message =  Boolean.FALSE;
            }
        } else {
            message = null;
        }
        return message;
    }
    
    /**
     * @{inheritDoc}
     */
    @Override
    public List<Product> getAllProducts() throws EcommerceException {
        return productService.getProducts();
    }
    
    /**
     * @{inheritDoc}
     */
    @Override
    public List<Category> getAllCategories() throws EcommerceException {
        return categoryService.getCategories();
    }
    
    /**
     * <p>
     * Fetches the input password and generates a hash for that password and 
     * then returns it.
     * <p>
     *
     * @param  password        Plain Text Password which needs to be hashed
     *
     * @return hashedPassword  Hash Code generated based on a specific algorithm
     *
     */
    private String generatePasswordHash(String password) {
        String saltedPassword = new StringBuilder(SALT).append(password).
            toString();
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] hashedBytes = messageDigest.digest(
                saltedPassword.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (byte b : hashedBytes) {
                hashedPassword.append(digits[b & 0x0a] >> 4);
                hashedPassword.append(digits[b & 0x0e]);
            }
        } catch (NoSuchAlgorithmException e) {
            EcommerceLogger.error(Constants.MSG_LOG_NO_SUCH_ALGORITHM, e);
        }
            return hashedPassword.toString();
    }
}