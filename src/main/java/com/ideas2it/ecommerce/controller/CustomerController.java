package com.ideas2it.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Address;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.service.CustomerService;
import com.ideas2it.ecommerce.service.impl.CustomerServiceImpl;


/**
 * <p>
 * CustomerController class to perform operations such as add new customer,
 * update customer details, purchase products, cancel placed orders, add
 * products to cart, remove from cart, place an order using cart products.
 * </p>
 *
 * @author Anantharaj.S
 */
public class CustomerController {

    private CustomerService customerService = new CustomerServiceImpl();

    /**
     * <p>
     * This method is used to show the add new customer to e-commerce web-site.
     * 
     * @param request A request message from a client to a server includes,
     *                within the first line of that message, the method to be
     *                applied to the resource, the identifier of the resource in
     *                use.
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "login" is the view name. After creating
     *         customer return login page.
     */
    @PostMapping(value = "/AddCustomer")
    public ModelAndView addCustomer(
            @ModelAttribute(Constants.LABEL_CUSTOMER) Customer customer,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (customerService.addCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        customer.getName()
                                + Constants.MSG_ADD_CUSTOMER_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        customer.getName() + Constants.MSG_ADD_CUSTOMER_FAIL);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject("message", e.getMessage());
        }
        modelAndView.setViewName("Login");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to show particular customer details..
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "CustomerUpdate" is view name and
     *         customer is model.
     */
    @GetMapping("/myAccount")
    public ModelAndView modifyAccount(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute(Constants.LABEL_CUSTOMER);
        modelAndView.addObject(Constants.LABEL_CUSTOMER, customer);
        modelAndView.setViewName("CustomerUpdate");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to display particular customer order details
     * </p>
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "OrdersDisplay" is the view name and set of orders
     *        and customer is the model.
     */
    @GetMapping("/myOrders")
    public ModelAndView myOrders( HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);
        List<Order> orders = customer.getOrders();
        if (null == orders) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, 
                Constants.MSG_NO_ORDER_PLACED);
        }
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("OrdersDisplay");
        return modelAndView;
    }
    
    /**
     * <p>
     * This method is used to cancel particular customer order details
     * </p>
     *
     * @return ModelAndView
     *        ModelAndView is an object that holds both the model and view. In 
     *        this method "OrdersDisplay" is the view name and set of orders
     *        and customer is the model.
     * 
     */
    @GetMapping("/cancelOrder")
    public ModelAndView cancelOrder(HttpServletRequest request,
            @RequestParam("id") String id) {
        HttpSession session=request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        try {
            Customer customer = (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);
            List<Order> orders = customer.getOrders();
            for (Order order : orders) {
                if (order.getId() == Integer.parseInt(id)) {
                     orders.remove(order);
                }
            }
            customer.setOrders(orders);
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CANCEL_ORDER_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CANCEL_ORDER_FAIL);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return myOrders(request);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
