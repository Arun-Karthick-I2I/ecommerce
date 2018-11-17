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
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.service.AdminService;
import com.ideas2it.ecommerce.service.OrderItemService;
import com.ideas2it.ecommerce.service.impl.AdminServiceImpl;
import com.ideas2it.ecommerce.service.impl.OrderItemServiceImpl;

/**
 * <p>
 * Administrator is given privileges such as displaying all the Orders placed 
 * by the Customer, searching a specific Order by ID, viewing all the active 
 * Customers and Sellers, searching a specific Customer or Seller by their 
 * respective ID or by Name.
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
            return new ModelAndView("AdminOrderPage",
                    Constants.LABEL_ORDERS,orders);  
        } else {
            return new ModelAndView("AdminOrderPage",
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
    private ModelAndView searchByOrderId(@RequestParam
            (Constants.LABEL_ID) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Order> orders = new ArrayList<Order>();
        try {
            Order order = adminService.searchByOrderId(id);
            if (null != order) {
                orders.add(order);
                modelAndView.addObject(Constants.LABEL_ORDERS, orders);
                modelAndView.setViewName("AdminOrderPage");
            } else {
                orders = getOrders();
                modelAndView.addObject(Constants.LABEL_ORDERS, orders);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_ORDER_NOT_AVAILABLE);
                modelAndView.setViewName("AdminOrderPage");
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
                Constants.LABEL_CUSTOMERS,customers);  
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
    private ModelAndView searchByCustomerId(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = new ArrayList<Customer>();
            Customer customer = getCustomer(id);
            if (null != customer) {
                modelAndView.addObject(Constants.LABEL_CUSTOMER, customer);
                modelAndView.setViewName("displayCustomer");
            } else {
                customers = getCustomers(Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
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
    private ModelAndView searchByCustomerName(@RequestParam
            (Constants.LABEL_NAME)String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = adminService.searchByCustomerName
                ("%"+name+"%", Boolean.TRUE);
            if (!customers.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
                modelAndView.setViewName("displayCustomers");
            } else {
                customers = getCustomers(Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
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
    private ModelAndView deleteCustomer(@RequestParam
            (Constants.LABEL_ID)Integer id) {
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
            modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
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
                    Constants.LABEL_SELLERS,sellers);  
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
    private ModelAndView searchBySellerId(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            Seller seller = adminService.searchBySellerId(id);
            if (null != seller) {
                modelAndView.addObject(Constants.LABEL_SELLER, seller);
                modelAndView.setViewName("displaySeller");
            } else {
                sellers = getSellers();
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
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
    private ModelAndView searchBySellerName(@RequestParam
            (Constants.LABEL_NAME)String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            sellers = adminService.searchBySellerName("%"+name+"%");
            if (!sellers.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                modelAndView.setViewName("displaySellers");
            } else {
                sellers = getSellers();
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
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
    private ModelAndView deleteSeller(@RequestParam
            (Constants.LABEL_ID)Integer id) {
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
            modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
            modelAndView.setViewName("displaySellers");
        } catch(EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to display the Products sold by the Seller for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Seller whose Products are to retrieved.
     * @return      Returns the Products sold by the Seller for the ID 
     *              specified. Otherwise, returns a failure message 
     *              indicating that no Products are sold by the Seller
     *              for the specified ID.
     */
    @PostMapping("displaySellerProducts") 
    private ModelAndView displaySellerProducts(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = new ArrayList<Product>();
        List<Seller> sellers = new ArrayList<Seller>();
        List<WarehouseProduct> warehouseProducts = 
            new ArrayList<WarehouseProduct>();
        try {
            warehouseProducts = adminService.getProductsBySeller(id);
            for (WarehouseProduct warehouseProduct : warehouseProducts) {
                products.add(warehouseProduct.getProduct());
            }
            if (!products.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
                modelAndView.setViewName("adminDisplayProducts");
            } else {
                sellers = getSellers();
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_PRODUCTS_UNAVAILABLE);
                modelAndView.setViewName("displaySellers");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to display the Orders placed by the Customer for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Customer whose Orders are to retrieved.
     * @return      Returns the Orders placed by the Customer for the ID 
     *              specified. Otherwise, returns a failure message 
     *              indicating that no Orders have been placed by the
     *              Customer for the specified ID.
     */
    @PostMapping("displayCustomerOrders") 
    private ModelAndView displayCustomerOrders(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = new ArrayList<Customer>();
        Customer customer = getCustomer(id);
        if (null != customer) {
            if(!(customer.getOrders().isEmpty())) {
                modelAndView.addObject(Constants.LABEL_ORDERS, 
                    customer.getOrders());
                modelAndView.setViewName("AdminOrderPage");
            } else {
                customers = getCustomers(Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ORDERS_UNAVAILABLE);
                modelAndView.setViewName("displayCustomers");
            }
        } else {
            customers = getCustomers(Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                Constants.MSG_CUSTOMER_NOT_AVAILABLE);
            modelAndView.setViewName("displayCustomers");
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to display the various Orders placed by different Customers for 
     * different from the same Seller for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Seller whose Orders are to retrieved.
     * @return      Returns the Orders of a Seller for the ID 
     *              specified. Otherwise, returns a failure message 
     *              indicating that no Orders have been placed by 
     *              Customers to that Seller.
     */
    @PostMapping("displaySellerOrders") 
    private ModelAndView displaySellerOrders(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            List<Integer> warehouseProductIds = adminService
                    .getWarehouseProductIdsBySeller(id);
            if (!warehouseProductIds.isEmpty()) {
                List<OrderItem> orderItems = adminService
                    .searchOrderItemsByWarehouseProductIds(warehouseProductIds);
                if (!orderItems.isEmpty()) {
                    modelAndView.addObject
                        (Constants.LABEL_ORDER_ITEMS, orderItems);
                    modelAndView.setViewName("displayOrders");
                } else {
                    sellers = getSellers();
                    modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_SELLER_ORDERS_UNAVAILABLE);
                    modelAndView.setViewName("displaySellers");
                }
            } else {
                sellers = getSellers();
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_SELLER_ORDERS_UNAVAILABLE);
                modelAndView.setViewName("displaySellers");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Used to display the Orders placed by different Customers for 
     * the Product ID specified.
     * </p>
     * 
     * @param   id  ID of the Product whose Orders are to fetched.
     * @return      Returns the Orders placed by different Customers for 
     *              Product ID specified. Otherwise, returns a failure
     *              message indicating that no Orders have been placed for 
     *              that particular Product.
     */
    @PostMapping("displayProductOrders") 
    private ModelAndView displayProductOrders(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = new ArrayList<Product>();
        try {
            List<Integer> warehouseProductIds = adminService
                .getWarehouseProductIdsByProduct(id);
            if (!warehouseProductIds.isEmpty()) {
                List<OrderItem> orderItems = adminService
                    .searchOrderItemsByWarehouseProductIds(warehouseProductIds);
                if (!orderItems.isEmpty()) {
                    modelAndView.addObject
                        (Constants.LABEL_ORDER_ITEMS, orderItems);
                    modelAndView.setViewName("displayOrders");
                } else {
                    products = getProducts();
                    modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                            Constants.MSG_ORDERS_UNAVAILABLE);
                    modelAndView.setViewName("adminDisplayProducts");
                }
            } else {
                products = getProducts();
                modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ORDERS_UNAVAILABLE);
                modelAndView.setViewName("adminDisplayProducts");
            }
        } catch (EcommerceException e) {
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
    
    /**
     * <p>
     * Used to fetch the details of all the Products available.
     * </p>
     * 
     * @return  Returns the list of Products available. Otherwise, returns an 
     *          empty object.
     */
    private List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();
        try {
            products = adminService.getProducts();
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return products;
    }
    
    /**
     * <p>
     * Redirects to the Administrator's Login page when invoked.
     * <p>
     * 
     * @return  Directs to the Administrator's Login page.
     */
    @GetMapping("/")
    private String loginForm() {
        return "adminLogin";
    }
}
