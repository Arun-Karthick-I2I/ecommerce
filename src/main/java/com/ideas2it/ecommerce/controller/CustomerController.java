package com.ideas2it.ecommerce.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Address;
import com.ideas2it.ecommerce.model.CartProduct;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.WarehouseProduct;
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
@Controller
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
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        modelAndView.addObject(Constants.LABEL_CUSTOMER, customer);
        modelAndView.setViewName("CustomerUpdate");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to display particular customer order details
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @GetMapping("/myOrders")
    public ModelAndView myOrders(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        List<Order> orders = customer.getOrders();
        if (null == orders) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_NO_ORDER_PLACED);
        }
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("OrdersDisplay");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to display particular customer order details
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @GetMapping("/addCart")
    public ModelAndView addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            Integer quantity = Integer
                    .parseInt((String) request.getParameter("quantity"));
            Integer id = Integer.parseInt(request.getParameter("id"));
            WarehouseProduct warehouseProduct = customerService
                    .getWarehouseProduct(id);
            List<CartProduct> cartProducts = customer.getCartProducts();
            CartProduct cartProduct = new CartProduct();
            cartProduct.setWarehouseProduct(warehouseProduct);
            cartProduct.setCustomer(customer);
            cartProduct.setQuantity(quantity);
            cartProduct.setPrice(quantity * warehouseProduct.getPrice());
            cartProducts.add(cartProduct);
            customer.setCartProducts(cartProducts);
            if (customerService.updateCustomer(customer)) {
                session.setAttribute("customer", customer);
                modelAndView.addObject("cartProducts",
                        customer.getCartProducts());
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_NO_ORDER_PLACED);
            }
            modelAndView.setViewName("CartDisplay");
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName("homepage");
        }

        return modelAndView;
    }

    /**
     * <p>
     * This method is used to place order for customer want to purchase...
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @PostMapping("buyProduct")
    public ModelAndView placeOrder(@RequestParam("id") String id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            Integer quantity = Integer
                    .parseInt((String) request.getParameter("quantity"));
            Integer addressId = Integer
                    .parseInt(request.getParameter("addressId"));
            WarehouseProduct warehouseProduct = customerService
                    .getWarehouseProduct(Integer.parseInt(id));
            Address address = new Address();
            address.setId(addressId);
            Order order = new Order();
            order.setCustomer(customer);
            order.setWarehouseProduct(warehouseProduct);
            order.setPrice(quantity * warehouseProduct.getPrice());
            order.setQuantity(quantity);
            order.setAddress(address);
            LocalDate todayDate = LocalDate.now();
            order.setOrderDate(todayDate);
            order.setStatus("ORDERED");
            if (customerService.addOrder(order)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_SUCCESS);
                modelAndView.setViewName("OrdersDisplay");
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_FAIL);
            }
            customer = customerService.getCustomerById(customer.getId(),
                    Boolean.TRUE);
            session.setAttribute("customer", customer);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName("CustomerOperations");
        }
        return myOrders(request);
    }

    /**
     * <p>
     * This method is used to place order for customer want to purchase...
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @PostMapping("purchaseProducts")
    public ModelAndView PurchaseProducts(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        Order order;
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            List<Integer> warehouseProductIds = new ArrayList<Integer>();
            String[] warehouseProductQuantities = request
                    .getParameterValues("quantity");
            String[] cartProductIds = request.getParameterValues("cartProductId");
            Address address = new Address();
            address.setId(Integer.parseInt(request.getParameter("addressId")));
            for (String id : request.getParameterValues("id")) {
                warehouseProductIds.add(Integer.parseInt(id));
            }
            List<WarehouseProduct> warehouseProducts = customerService
                    .getWarehouseProductsById(warehouseProductIds);
            for (Integer i = 0; i < warehouseProductIds.size(); i++) {
                if (warehouseProductIds.get(i) == Integer
                        .parseInt(warehouseProductQuantities[i])) {
                    order = new Order();
                    order.setCustomer(customer);
                    order.setWarehouseProduct(warehouseProducts.get(i));
                    order.setPrice(
                            Integer.parseInt(warehouseProductQuantities[i])
                                    * (warehouseProducts.get(i)).getPrice());
                    order.setQuantity(
                            Integer.parseInt(warehouseProductQuantities[i]));
                    order.setAddress(address);
                    LocalDate todayDate = LocalDate.now();
                    order.setOrderDate(todayDate);
                    order.setStatus("ORDERED");
                    List<Order> orders = customer.getOrders();
                    orders.add(order);
                    customer.setOrders(orders);
                }
            }
            if (customerService.(customer)) {
                session.setAttribute("customer", customer);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_FAIL);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        modelAndView.addObject("orders", customer.getOrders());
        modelAndView.setViewName("OrdersDisplay");
        return myOrders(request);
    }

    /**
     * <p>
     * This method is used to cancel particular customer order details
     * </p>
     *
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @GetMapping("/cancelOrder")
    public ModelAndView cancelOrder(HttpServletRequest request,
            @RequestParam("id") String id) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        try {
            Customer customer = (Customer) session
                    .getAttribute(Constants.LABEL_CUSTOMER);
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
