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
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.service.AdminService;
import com.ideas2it.ecommerce.service.impl.AdminServiceImpl;

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
    private static final String ADMIN_ORDER_PAGE = "AdminOrderPage";
    private static final String ADMIN_CUSTOMER_PAGE = "AdminCustomerPage";
    private static final String ADMIN_SELLER_PAGE = "AdminSellerPage";
    private static final String ADMIN_PRODUCT_PAGE = "AdminProductPage";
    private static final String ADMIN_CATEGORY_PAGE = "AdminCategoryPage";
    private static final String ADMIN_DISPLAY_ORDERS = "AdminDisplayOrders";
    private static final String ADMIN_LOGIN = "AdminLogin";
    
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
    @GetMapping("displayOrders")
    private ModelAndView displayOrders() {
        List<Order> orders = new ArrayList<Order>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            orders = getOrders(modelAndView);
            if (!orders.isEmpty()) {
                return new ModelAndView(ADMIN_ORDER_PAGE,
                    Constants.LABEL_ORDERS,orders);  
            } else {
                return new ModelAndView(ADMIN_ORDER_PAGE,
                    Constants.LABEL_MESSAGE,Constants.MSG_ORDERS_UNAVAILABLE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(ADMIN_CATEGORY_PAGE);
        }
        return modelAndView;
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
    @GetMapping("searchByOrderId") 
    private ModelAndView searchByOrderId(@RequestParam
            (Constants.LABEL_ID) Integer id) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_ORDER_PAGE);
        List<Order> orders = new ArrayList<Order>();
        try {
            Order order = adminService.searchByOrderId(id);
            if (null != order) {
                orders.add(order);
                modelAndView.addObject(Constants.LABEL_ORDERS, orders);
            } else {
                orders = getOrders(modelAndView);
                modelAndView.addObject(Constants.LABEL_ORDERS, orders);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_ORDER_NOT_AVAILABLE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
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
    @GetMapping("displayCustomers")
    private ModelAndView displayCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            customers = getCustomers(modelAndView, Boolean.TRUE);
            if (!customers.isEmpty()) {
                return new ModelAndView(ADMIN_CUSTOMER_PAGE,
                    Constants.LABEL_CUSTOMERS,customers);  
            } else {
                return new ModelAndView(ADMIN_CUSTOMER_PAGE,
                    Constants.LABEL_MESSAGE,Constants.MSG_CUSTOMERS_NOT_AVAILABLE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(ADMIN_CATEGORY_PAGE);
        }
        return modelAndView;
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
    @GetMapping("searchByCustomerId") 
    private ModelAndView searchByCustomerId(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_CUSTOMER_PAGE);
        List<Customer> customers = new ArrayList<Customer>();
        try {
            Customer customer = getCustomer(modelAndView, id);
            if (null != customer) {
                customers.add(customer);
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
            } else {
                customers = getCustomers(modelAndView, Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_NOT_AVAILABLE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
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
    @GetMapping("searchByCustomerName") 
    private ModelAndView searchByCustomerName(@RequestParam
            (Constants.LABEL_NAME)String name) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_CUSTOMER_PAGE);
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = adminService.searchByCustomerName
                ("%"+name+"%", Boolean.TRUE);
            if (!customers.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
            } else {
                customers = getCustomers(modelAndView, Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_NOT_AVAILABLE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
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
        ModelAndView modelAndView = new ModelAndView(ADMIN_CUSTOMER_PAGE);
        try {
            customer = getCustomer(modelAndView, id);
            if (adminService.deleteCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_DELETE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_DELETE_FAILURE);
            }
            customers = getCustomers(modelAndView, Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
        } catch(EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
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
    @GetMapping("displaySellers")
    private ModelAndView displaySellers() {
        List<Seller> sellers = new ArrayList<Seller>();
        ModelAndView modelAndView = new ModelAndView(ADMIN_CATEGORY_PAGE);
        try {
            sellers = getSellers(modelAndView);
            if (!sellers.isEmpty()) {
                return new ModelAndView(ADMIN_SELLER_PAGE,
                    Constants.LABEL_SELLERS,sellers);  
            } else {
                return new ModelAndView(ADMIN_SELLER_PAGE,
                    Constants.LABEL_MESSAGE,Constants.MSG_SELLERS_NOT_AVAILABLE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(ADMIN_CATEGORY_PAGE);
        }
        return modelAndView;
        
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
    @GetMapping("searchBySellerId") 
    private ModelAndView searchBySellerId(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_SELLER_PAGE);
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            Seller seller = adminService.searchBySellerId(id);
            if (null != seller) {
                sellers.add(seller);
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
            } else {
                sellers = getSellers(modelAndView);
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_NOT_AVAILABLE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
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
    @GetMapping("searchBySellerName") 
    private ModelAndView searchBySellerName(@RequestParam
            (Constants.LABEL_NAME)String name) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_SELLER_PAGE);
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            sellers = adminService.searchBySellerName("%"+name+"%");
            if (!sellers.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
            } else {
                sellers = getSellers(modelAndView);
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_NOT_AVAILABLE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
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
        ModelAndView modelAndView = new ModelAndView(ADMIN_SELLER_PAGE);
        try {
            seller.setId(id);
            if (adminService.deleteSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_DELETE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_DELETE_FAILURE);
            }
            sellers = getSellers(modelAndView);
            modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
        } catch(EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
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
    @GetMapping("displaySellerProducts") 
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
                modelAndView.setViewName(ADMIN_PRODUCT_PAGE);
            } else {
                sellers = getSellers(modelAndView);
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_PRODUCTS_UNAVAILABLE);
                modelAndView.setViewName(ADMIN_SELLER_PAGE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(ADMIN_SELLER_PAGE);
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
    @GetMapping("displayCustomerOrders") 
    private ModelAndView displayCustomerOrders(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = new ArrayList<Customer>();
        try {
            Customer customer = getCustomer(modelAndView, id);
            if (null != customer) {
                if(!(customer.getOrders().isEmpty())) {
                    modelAndView.addObject(Constants.LABEL_ORDERS, 
                        customer.getOrders());
                    modelAndView.setViewName(ADMIN_ORDER_PAGE);
                } else {
                    customers = getCustomers(modelAndView, Boolean.TRUE);
                    modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ORDERS_UNAVAILABLE);
                    modelAndView.setViewName(ADMIN_CUSTOMER_PAGE);
                }
            } else {
                customers = getCustomers(modelAndView, Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CUSTOMER_NOT_AVAILABLE);
                modelAndView.setViewName(ADMIN_CUSTOMER_PAGE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(ADMIN_CUSTOMER_PAGE);
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
    @GetMapping("displaySellerOrders") 
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
                    modelAndView.setViewName(ADMIN_DISPLAY_ORDERS);
                } else {
                    sellers = getSellers(modelAndView);
                    modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_SELLER_ORDERS_UNAVAILABLE);
                    modelAndView.setViewName(ADMIN_SELLER_PAGE);
                }
            } else {
                sellers = getSellers(modelAndView);
                modelAndView.addObject(Constants.LABEL_SELLERS, sellers);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_SELLER_ORDERS_UNAVAILABLE);
                modelAndView.setViewName(ADMIN_SELLER_PAGE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(ADMIN_SELLER_PAGE);
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
    @GetMapping("displayProductOrders") 
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
                    modelAndView.setViewName(ADMIN_DISPLAY_ORDERS);
                } else {
                    products = getProducts(modelAndView);
                    modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                            Constants.MSG_ORDERS_UNAVAILABLE);
                    modelAndView.setViewName(ADMIN_PRODUCT_PAGE);
                }
            } else {
                products = getProducts(modelAndView);
                modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ORDERS_UNAVAILABLE);
                modelAndView.setViewName(ADMIN_PRODUCT_PAGE);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(ADMIN_PRODUCT_PAGE);
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
    private List<Seller> getSellers(ModelAndView modelAndView) 
            throws EcommerceException {
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            sellers = adminService.getSellers();
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
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
    private List<Customer> getCustomers(ModelAndView modelAndView, 
            Boolean status) throws EcommerceException {
        List<Customer> customers = new ArrayList<Customer>();
        customers = adminService.getCustomers(status);
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
    private List<Order> getOrders(ModelAndView modelAndView) 
            throws EcommerceException {
        List<Order> orders = new ArrayList<Order>();
        orders = adminService.getOrders();
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
    private Customer getCustomer(ModelAndView modelAndView, Integer id) 
            throws EcommerceException {
        Customer customer = new Customer();
        customer = adminService.searchByCustomerId(id, Boolean.TRUE);
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
    private List<Product> getProducts(ModelAndView modelAndView) 
            throws EcommerceException {
        List<Product> products = new ArrayList<Product>();
        products = adminService.getProducts();
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
        return ADMIN_LOGIN;
    }
}
