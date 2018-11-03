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
import com.ideas2it.ecommerce.model.Order;
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
    private ModelAndView searchById(@RequestParam("id")Integer id) {
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
