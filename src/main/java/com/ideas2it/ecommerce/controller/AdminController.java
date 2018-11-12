package com.ideas2it.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.service.AdminService;
import com.ideas2it.ecommerce.service.impl.AdminServiceImpl;

/**
 * <p>
 * Admin is given privileges such as displaying all the Orders placed by the 
 * Customer, searching a specific Order by ID, viewing all the active Customers
 * and Sellers, searching a specific Customer or Seller by their respective ID
 * or by Name.
 * </p>
 * 
 * @author Pavithra.S
 *
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    private AdminService adminService = new AdminServiceImpl();
    
    /**
     * <p>
     * Used to display the details of all the Orders placed by several
     * Customers.
     * </p>
     * 
     * @return  Returns the list of Orders placed by the Customers.
     *          Otherwise, returns a failure message indicating that no Orders 
     *          are available.
     */
    @PostMapping("displayOrders")
    private ModelAndView displayOrders() {
        List<Order> orders = new ArrayList<Order>();
        orders = getOrders();
        if (!orders.isEmpty()) {
            return new ModelAndView("displayOrders",
                "orders",orders);  
        } else {
            return new ModelAndView("displayOrders",
                Constants.LABEL_MESSAGE,Constants.MSG_ORDERS_UNAVAILABLE);
        }
    }

    /**
     * <p>
     * Used to retrieve the details of the Order placed for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Order whose details are to retrieved 
     * @return      Returns the Order for the ID specified. Otherwise, returns
     *              a failure message indicating that the Order of the 
     *              specified ID isn't available.
     */
    @PostMapping("searchByOrderId") 
    private ModelAndView searchByOrderId(@RequestParam("id")Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Order> orders = new ArrayList<Order>();
        try {
            Order order = adminService.searchByOrderId(id);
            if (null != order) {
                modelAndView.addObject("order", order);
                modelAndView.setViewName("displayOrder");
            } else {
                orders = getOrders();
                modelAndView.addObject("orders", orders);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_ORDER_NOT_AVAILABLE);
                modelAndView.setViewName("displayProducts");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to display the details of all the active Customers.
     * </p>
     * 
     * @return  Returns the list of Customers. Otherwise, returns a failure 
     *          message indicating no Customers are available.
     */
    @PostMapping("displayCustomers")
    private ModelAndView displayCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        customers = getCustomers(Boolean.TRUE);
        if (!customers.isEmpty()) {
            return new ModelAndView("displayCustomers",
                "customers",customers);  
        } else {
            return new ModelAndView("displayCustomers",
                Constants.LABEL_MESSAGE,Constants.MSG_CUSTOMERS_NOT_AVAILABLE);
        }
    }
    
    /**
     * <p>
     * Used to retrieve the details of the Customer for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Customer whose details are to retrieved. 
     * @return      Returns the Customer for the ID specified. Otherwise, 
     *              returns a failure message indicating that the Customer of 
     *              the specified ID isn't available.
     */
    @PostMapping("searchByCustomerId") 
    private ModelAndView searchByCustomerId(@RequestParam("id")Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = new ArrayList<Customer>();
            Customer customer = getCustomer(id);
            if (null != customer) {
                modelAndView.addObject("customer", customer);
                modelAndView.setViewName("displayCustomer");
            } else {
                customers = getCustomers(Boolean.TRUE);
                modelAndView.addObject("customers", customers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_NOT_AVAILABLE);
                modelAndView.setViewName("displayCustomers");
            }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to retrieve the details of the Customer based on the name specified. 
     * </p>
     * 
     * @param   name Name of the Customer whose details are to retrieved.
     * @return       Returns the list of Customers for the name specified. 
     *               Otherwise, returns a failure message indicating that no 
     *               Customer is available for the name specified. 
     */
    @PostMapping("searchByCustomerName") 
    private ModelAndView searchByCustomerName(@RequestParam("name")String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = adminService.searchByCustomerName("%"+name+"%", Boolean.TRUE);
            if (!customers.isEmpty()) {
                modelAndView.addObject("customers", customers);
                modelAndView.setViewName("displayCustomers");
            } else {
                customers = getCustomers(Boolean.TRUE);
                modelAndView.addObject("customers", customers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_NOT_AVAILABLE);
                modelAndView.setViewName("displayCustomers");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to deactivate the Customer Account based on the ID specified.
     * </p>
     * 
     * @param   id  ID of the Customer to be deleted.
     * @return      Returns success message, if the customer Account has been
     *              deactivated successfully. Otherwise returns failure message,
     *              if the deletion is unsuccessful.
     */
    @PostMapping("deleteCustomer") 
    private ModelAndView deleteCustomer(@RequestParam("id")Integer id) {
        Customer customer = new Customer();
        List<Customer> customers = new ArrayList<Customer>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            customer = getCustomer(id);
            if (adminService.deleteCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_DELETE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_DELETE_FAILURE);
            }
            customers = getCustomers(Boolean.TRUE);
            modelAndView.addObject("customers", customers);
            modelAndView.setViewName("displayCustomers");
        } catch(EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to display the details of all the Sellers who would sell products
     * belonging to various Categories. 
     * </p>
     * 
     * @return  Returns the list of Sellers. Otherwise, returns a failure 
     *          message indicating no Sellers are available.
     */
    @PostMapping("displaySellers")
    private ModelAndView displaySellers() {
        List<Seller> sellers = new ArrayList<Seller>();
        sellers = getSellers();
        if (!sellers.isEmpty()) {
            return new ModelAndView("displaySellers",
                "sellers",sellers);  
        } else {
            return new ModelAndView("displaySellers",
                Constants.LABEL_MESSAGE,Constants.MSG_SELLERS_NOT_AVAILABLE);
        }
    }
    
    /**
     * <p>
     * Used to retrieve the details of the Seller for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Seller whose details are to retrieved.
     * @return      Returns the Seller for the ID specified. Otherwise, returns
     *              a failure message indicating that the Seller of the 
     *              specified ID isn't available.
     */
    @PostMapping("searchBySellerId") 
    private ModelAndView searchBySellerId(@RequestParam("id")Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            Seller seller = adminService.searchBySellerId(id);
            if (null != seller) {
                modelAndView.addObject("seller", seller);
                modelAndView.setViewName("displaySeller");
            } else {
                sellers = getSellers();
                modelAndView.addObject("sellers", sellers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_NOT_AVAILABLE);
                modelAndView.setViewName("displaySellers");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to retrieve the details of the Seller based on the name specified. 
     * </p>
     * 
     * @param   name  Name of the Seller whose details are to retrieved.
     * @return        Returns the list of Sellers for the name specified. 
     *                Otherwise, returns a failure message indicating that no 
     *                Seller is available for the name specified. 
     */
    @PostMapping("searchBySellerName") 
    private ModelAndView searchBySellerName(@RequestParam("name")String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            sellers = adminService.searchBySellerName("%"+name+"%");
            if (!sellers.isEmpty()) {
                modelAndView.addObject("sellers", sellers);
                modelAndView.setViewName("displaySellers");
            } else {
                sellers = getSellers();
                modelAndView.addObject("sellers", sellers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_NOT_AVAILABLE);
                modelAndView.setViewName("displaySellers");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to delete the Seller based on the ID specified.
     * </p>
     * 
     * @param   id  ID of the Seller to deleted.
     * @return      Returns success message, if the Seller has been deleted
     *              successfully. Otherwise returns failure message, if the
     *              deletion is unsuccessful. 
     */
    @PostMapping("deleteSeller") 
    private ModelAndView deleteSeller(@RequestParam("id")Integer id) {
        Seller seller = new Seller();
        List<Seller> sellers = new ArrayList<Seller>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            seller.setId(id);
            if (adminService.deleteSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_DELETE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_DELETE_FAILURE);
            }
            sellers = getSellers();
            modelAndView.addObject("sellers", sellers);
            modelAndView.setViewName("displaySellers");
        } catch(EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to fetch the details of all the Sellers available.
     * </p>
     * 
     * @return  Returns the list of Sellers available. Otherwise, returns an 
     *          empty object.
     */
    private List<Seller> getSellers() {
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            sellers = adminService.getSellers();
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return sellers;
    }
    
    /**
     * <p>
     * Used to fetch the details of all the active Customers, if the status is
     * true. If the status is false, fetches all the deactivated accounts. 
     * </p>
     * 
     * @param   status  Status of the Customers checking whether it has been 
     *                  deleted or not
     * @return          Returns the list of Customers available. Otherwise, 
     *                  returns an empty object.
     */
    private List<Customer> getCustomers(Boolean status) {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = adminService.getCustomers(status);
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return customers;
    }
    
    /**
     * <p>
     * Used to fetch the details of all the Orders placed.
     * </p>
     * 
     * @return  Returns the list of Orders placed. Otherwise, returns an 
     *          empty object.
     */
    private List<Order> getOrders() {
        List<Order> orders = new ArrayList<Order>();
        try {
            orders = adminService.getOrders();
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return orders;
    }
    
    /**
     * <p>
     * Used to fetch the details of the Customer for the ID specified. 
     * </p>
     * 
     * @param   id  ID of the Customer to be fetched.
     * @return      Returns the Customer for the ID specified. Otherwise,
     *              returns an empty object.
     */
    private Customer getCustomer(Integer id) {
        Customer customer = new Customer();
        try {
            customer = adminService.searchByCustomerId(id, Boolean.TRUE);
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return customer;
    }
    
    @GetMapping("/")
    private String loginForm() {
        return "adminLogin";
    }
}
