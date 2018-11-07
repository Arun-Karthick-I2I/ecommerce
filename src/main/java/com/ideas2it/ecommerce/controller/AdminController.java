package com.ideas2it.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("admin")
public class AdminController {
    private AdminService adminService = new AdminServiceImpl();
    
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
    
    @PostMapping("searchByCustomerId") 
    private ModelAndView searchByCustomerId(@RequestParam("id")Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = new ArrayList<Customer>();
        try {
            Customer customer = adminService.searchByCustomerId(id, Boolean.TRUE);
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
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    @PostMapping("searchByCustomerName") 
    private ModelAndView searchByCustomerName(@RequestParam("name")String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = adminService.searchByCustomerName(name, Boolean.TRUE);
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
    
    @PostMapping("deleteCustomer") 
    private ModelAndView deleteCustomer(@RequestParam("id")Integer id) {
        Customer customer = new Customer();
        List<Customer> customers = new ArrayList<Customer>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            customer.setId(id);
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
    
    @PostMapping("searchBySellerName") 
    private ModelAndView searchBySellerName(@RequestParam("name")String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            sellers = adminService.searchBySellerName(name);
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
    
    private List<Seller> getSellers() {
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            sellers = adminService.getSellers();
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return sellers;
    }
    
    private List<Customer> getCustomers(Boolean status) {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = adminService.getCustomers(status);
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return customers;
    }
    
    private List<Order> getOrders() {
        List<Order> orders = new ArrayList<Order>();
        try {
            orders = adminService.getOrders();
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return orders;
    }
}
