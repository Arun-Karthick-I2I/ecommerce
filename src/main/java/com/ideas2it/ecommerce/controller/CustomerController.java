package com.ideas2it.ecommerce.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.common.enums.ORDER_STATUS;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Address;
import com.ideas2it.ecommerce.model.CartItem;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.service.CustomerService;
import com.ideas2it.ecommerce.service.impl.CustomerServiceImpl;

/**
 * <p>
 * CustomerController class to perform operations such as add new customer,
 * update customer details, purchase products, cancel placed orders, add
 * products to cart, remove from cart, place an order from cart, return the
 * delivered products.
 * </p>
 *
 * @author Anantharaj.S
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    private CustomerService customerService = new CustomerServiceImpl();

    /**
     * <p>
     * This method is used to update customer details to e-commerce web-site.
     * 
     * @param request A request message from a client to a server includes,
     *                within the first line of that message, the method to be
     *                applied to the resource, the identifier of the resource in
     *                use.
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "myAccount" is the view name. After
     *         updating customer return profile page.
     */
    @PostMapping("updateCustomer")
    public ModelAndView updateCustomer(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            customer.setName(request.getParameter(Constants.LABEL_NAME));
            customer.setEmailId(request.getParameter(Constants.LABEL_EMAIL_ID));
            customer.setMobileNumber(
                    request.getParameter(Constants.LABEL_MOBILE_NUMBER));
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
            }
            modelAndView = modifyAccount(request);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to show particular customer details..
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "myAccount" is view name and customer is
     *         model.
     */
    @GetMapping("myaccount")
    public ModelAndView modifyAccount(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        modelAndView.addObject(Constants.LABEL_CUSTOMER, customer);
        modelAndView.setViewName(Constants.JSP_MY_ACCOUNT);
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to display particular customer order details
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "myOrders" is the view name and set of
     *         orders and customer is the model.
     */
    @GetMapping("myOrders")
    public ModelAndView myOrders(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            List<Order> orders = new ArrayList<Order>(customer.getOrders());
            Collections.reverse(orders);
            Map<Integer, LocalDate> returnDates = new HashMap<Integer, LocalDate>();
            LocalDate returnDate;
            for (Order order : orders) {
                returnDate = order.getOrderDate().toLocalDate().plusDays(10);
                returnDates.put(order.getId(), returnDate);
            }
            modelAndView.addObject(Constants.LABEL_TODAY, LocalDate.now());
            modelAndView.addObject(Constants.LABEL_RETURN_DATES, returnDates);
            modelAndView.addObject(Constants.LABEL_ORDERS, orders);
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        modelAndView.setViewName(Constants.JSP_MY_ORDERS);
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to cancel particular order item details. After
     * cancelled order return to customer orders page.
     * </p>
     *
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "myOrders" is the view name and set of
     *         orders and customer is the model.
     */
    @PostMapping("cancelOrder")
    public ModelAndView cancelOrder(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer orderItemId = Integer.parseInt(
                    request.getParameter(Constants.LABEL_ORDER_ITEM_ID));
            List<Integer> orderItemIds = new ArrayList<Integer>();
            orderItemIds.add(orderItemId);
            List<OrderItem> orderItems = customerService
                    .getOrderItemsByIds(orderItemIds);
            OrderItem cancelledOrderItem = new OrderItem();
            for (OrderItem orderItem : orderItems) {
                if (orderItemId == orderItem.getId()) {
                    cancelledOrderItem = orderItem;
                    orderItem.setStatus(ORDER_STATUS.CANCELLED);
                    break;
                }
            }
            modelAndView = cancelOrder(request, orderItems, cancelledOrderItem);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to cancel particular order item detail and increase
     * the quantity of cancelled warehouse product.
     * </p>
     * 
     * @param orderItems         needed for cancel the orderItem.
     * @param cancelledOrderItem needed for increase the quantity.
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "myOrders" is the view name and set of
     *         orders and customer is the model.
     */
    private ModelAndView cancelOrder(HttpServletRequest request,
            List<OrderItem> orderItems, OrderItem cancelledOrderItem) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            if (customerService.cancelOrder(orderItems)) {
                Order order = new Order();
                List<OrderItem> cancelOrder = new ArrayList<OrderItem>();
                cancelOrder.add(cancelledOrderItem);
                order.setOrderItems(cancelOrder);
                customerService.IncreaseQuantityAftercancelOrder(order);
                customer = customerService.getCustomerById(customer.getId(),
                        Boolean.TRUE);
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                modelAndView = myOrders(request);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_CANCEL_ORDER_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_CANCEL_ORDER_FAIL);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to return particular product after delivered from
     * e-commerce web-site, If the customer didn't satisfy that product.
     * </p>
     *
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @PostMapping("returnOrder")
    public ModelAndView returnOrder(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            Integer orderItemId = Integer.parseInt(
                    request.getParameter(Constants.LABEL_ORDER_ITEM_ID));
            List<Integer> orderItemIds = new ArrayList<Integer>();
            orderItemIds.add(orderItemId);
            List<OrderItem> orderItems = customerService
                    .getOrderItemsByIds(orderItemIds);
            for (OrderItem orderItem : orderItems) {
                if (orderItemId == orderItem.getId()) {
                    orderItem.setStatus(ORDER_STATUS.RETURNED);
                    break;
                }
            }
            if (customerService.cancelOrder(orderItems)) {
                modelAndView = myOrders(request);
                customer = customerService.getCustomerById(customer.getId(),
                        Boolean.TRUE);
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_RETURN_ORDER_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_RETURN_ORDER_FAIL);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_RETURN_ORDER_FAIL);
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to display particular customer cart product details
     * </p>
     *
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "Cart" is the view name.
     */
    @GetMapping("Cart")
    public ModelAndView myCart(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        modelAndView.setViewName(Constants.JSP_CART);
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to customer add product to their cart.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "Cart" is the view name.
     */
    @PostMapping("addCart")
    public ModelAndView addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            Integer id = Integer
                    .parseInt(request.getParameter(Constants.LABEL_ID));
            WarehouseProduct warehouseProduct = customerService
                    .getWarehouseProduct(id);
            List<CartItem> cartItems = customer.getCartItems();
            Boolean result = Boolean.TRUE;
            for (Integer i = 0; i < cartItems.size(); i++) {
                if (id == cartItems.get(i).getWarehouseProduct().getId()) {
                    cartItems.get(i)
                            .setQuantity(cartItems.get(i).getQuantity() + 1);
                    cartItems.get(i).setPrice(cartItems.get(i).getQuantity()
                            * warehouseProduct.getPrice());
                    result = Boolean.FALSE;
                    break;
                }
            }
            if (result) {
                CartItem cartItem = new CartItem();
                cartItem.setWarehouseProduct(warehouseProduct);
                cartItem.setCustomer(customer);
                cartItem.setQuantity(1);
                cartItem.setPrice(warehouseProduct.getPrice());
                cartItems.add(cartItem);
            }
            customer.setCartItems(cartItems);
            if (customerService.updateCustomer(customer)) {
                customer = customerService.getCustomerById(customer.getId(),
                        Boolean.TRUE);
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_CART_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_CART_FAIL);
            }
            Product product = customerService
                    .searchProduct(warehouseProduct.getProduct().getId());
            modelAndView.addObject(Constants.LABEL_PRODUCT, product);
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
            modelAndView.setViewName(Constants.JSP_PRODUCT_PAGE);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(Constants.JSP_CUSTOMER_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to remove particular product from cart.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "Cart" is the view name.
     */
    @PostMapping("removeFromCart")
    public ModelAndView removeFromCart(
            @RequestParam(Constants.LABEL_ID) String id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            List<CartItem> cartItems = customer.getCartItems();
            for (Integer i = 0; i < cartItems.size(); i++) {
                if (Integer.parseInt(id) == cartItems.get(i).getId()) {
                    cartItems.remove(cartItems.get(i));
                    break;
                }
            }
            customer.setCartItems(cartItems);
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_CART_FAIL);
            }
            modelAndView.setViewName(Constants.JSP_CART);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_ADD_CART_FAIL);
            modelAndView.setViewName(Constants.JSP_CART);
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to increase or decrease particular product quantity
     * in cart.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "Cart" is the view name.
     */
    @PostMapping("updateCart")
    public ModelAndView updateCart(@RequestParam(Constants.LABEL_ID) String id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            List<CartItem> cartItems = customer.getCartItems();
            Integer quantity = Integer
                    .parseInt(request.getParameter(Constants.LABEL_QUANTITY));
            Float price = Float
                    .parseFloat(request.getParameter(Constants.LABEL_PRICE));
            for (Integer i = 0; i < cartItems.size(); i++) {
                if (Integer.parseInt(id) == cartItems.get(i).getId()) {
                    cartItems.get(i).setQuantity(quantity);
                    cartItems.get(i).setPrice(price);
                    break;
                }
            }
            customer.setCartItems(cartItems);
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        modelAndView.setViewName(Constants.JSP_CART);
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to display selected product to purchase which
     * contains the details of product price, seller name, price of product,
     * delivery address and mode of payment.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersPage" is the view name and set of
     *         orders and customer is the model.
     */
    @PostMapping("orderProduct")
    public ModelAndView OrderDetails(
            @RequestParam(Constants.LABEL_ID) Integer id,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Constants.LABEL_BUY_PRODUCT, Boolean.TRUE);
        try {
            WarehouseProduct warehouseProduct = customerService
                    .getWarehouseProduct(id);
            List<WarehouseProduct> warehouseProducts = new ArrayList<WarehouseProduct>();
            warehouseProducts.add(warehouseProduct);
            Map<Integer, Integer> quantities = new HashMap<Integer, Integer>();
            quantities.put(id, 1);
            modelAndView.addObject(Constants.LABEL_WAREHOUSE_PRODUCTS,
                    warehouseProducts);
            modelAndView.addObject(Constants.LABEL_QUANTITIES, quantities);
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
            modelAndView.addObject(Constants.LABEL_TOTAL_PRICE,
                    warehouseProduct.getPrice());
            modelAndView.setViewName(Constants.JSP_ORDER_PAGE);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_ADD_ORDER_FAIL);
            modelAndView.setViewName(Constants.JSP_CUSTOMER_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to purchase multiple products with different quantities.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersPage" is the view name and set of
     *         orders and customer is the model.
     */
    @PostMapping("buyProducts")
    public ModelAndView OrderDetails(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Constants.LABEL_BUY_PRODUCT, Boolean.FALSE);
        try {
            List<Integer> warehouseProductIds = new ArrayList<Integer>();
            String[] warehouseProductIdsExists = request
                    .getParameterValues(Constants.LABEL_WAREHOUSE_PRODUCT_ID);
            if ((null == warehouseProductIdsExists)
                    || (0 > warehouseProductIdsExists.length)) {
                modelAndView = myCart(request);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_SELECT_ATLEAST_ONE_PRODUCT);
                return modelAndView;
            }
            for (String id : warehouseProductIdsExists) {
                warehouseProductIds.add(Integer.parseInt(id));
            }
            List<WarehouseProduct> warehouseProducts = customerService
                    .getWarehouseProductsByIds(warehouseProductIds);
            modelAndView = OrderDetails(request, warehouseProducts);
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_ADD_ORDER_FAIL);
            modelAndView.setViewName(Constants.JSP_CUSTOMER_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used assign values to ModelAndView Object. This objects
     * have product details of customer ordered product anf=d their
     * corresponding quantities
     * </p>
     * 
     * @param warehouseProducts needed for placing an order.
     */
    private ModelAndView OrderDetails(HttpServletRequest request,
            List<WarehouseProduct> warehouseProducts) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        List<CartItem> cartItems = customer.getCartItems();
        Map<Integer, Integer> quantities = new HashMap<Integer, Integer>();
        Float totalPrice = (float) 0;
        for (Integer i = 0; i < cartItems.size(); i++) {
            for (Integer j = 0; j < warehouseProducts.size(); j++) {
                if (warehouseProducts.get(j).getId() == cartItems.get(i)
                        .getWarehouseProduct().getId()) {
                    quantities.put(warehouseProducts.get(j).getId(),
                            cartItems.get(i).getQuantity());
                    totalPrice = totalPrice + (cartItems.get(i).getQuantity()
                            * (warehouseProducts.get(j)).getPrice());
                }
            }
        }
        modelAndView.addObject(Constants.LABEL_WAREHOUSE_PRODUCTS,
                warehouseProducts);
        modelAndView.addObject(Constants.LABEL_QUANTITIES, quantities);
        modelAndView.addObject(Constants.LABEL_TOTAL_PRICE, totalPrice);
        modelAndView.setViewName(Constants.JSP_ORDER_PAGE);
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to purchase particular product or multiple products
     * with different quantities.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "myOrders" is the view name and set of
     *         orders and customer is the model.
     */
    @PostMapping("placeOrder")
    public ModelAndView placeOrder(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Boolean buyProduct = Boolean.parseBoolean(
                request.getParameter(Constants.LABEL_BUY_PRODUCT));
        if (buyProduct) {
            modelAndView = PurchaseProductDirect(request);
        } else {
            modelAndView = PurchaseProductFromCart(request);
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to purchase a particular product from product page.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersPage" is the view name.
     */
    private ModelAndView PurchaseProductDirect(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView(
                Constants.REDIRECT + "myOrders");
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            Integer warehouseProductId = Integer
                    .parseInt(request.getParameter(Constants.LABEL_ID));
            Integer addressId = Integer
                    .parseInt(request.getParameter(Constants.LABEL_ADDRESS_ID));
            WarehouseProduct warehouseProduct = customerService
                    .getWarehouseProduct(warehouseProductId);
            Order order = new Order();
            order.setCustomer(customer);
            order.setPrice(warehouseProduct.getPrice());
            LocalDate todayDate = LocalDate.now();
            order.setOrderDate(Date.valueOf(todayDate));
            Address address = new Address();
            address.setId(addressId);
            order.setAddress(address);
            order.setModeOfPayment(
                    request.getParameter(Constants.LABEL_MODE_OF_PAYMENT));
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(1);
            orderItem.setPrice(warehouseProduct.getPrice());
            orderItem.setStatus(ORDER_STATUS.ORDERED);
            orderItem.setWarehouseProduct(warehouseProduct);
            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            orderItems.add(orderItem);
            order.setOrderItems(orderItems);
            List<OrderItem> unavailableOrderItems = customerService
                    .addOrder(order);
            if (unavailableOrderItems.isEmpty()) {
                session.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_SUCCESS);
            } else {
                session.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_DONT_HAVE_ENOUGH_QUANTITY);
            }
            customer = customerService.getCustomerById(customer.getId(),
                    Boolean.TRUE);
            session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        } catch (EcommerceException e) {
            session.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MSG_ADD_ORDER_FAIL);
            modelAndView.setViewName(Constants.REDIRECT + "myOrders");
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to purchase list of products for a particular
     * customer from selected cart products.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "myOrders" is the view name and set of
     *         orders and customer is the model.
     */
    public ModelAndView PurchaseProductFromCart(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView(
                Constants.REDIRECT + "myOrders");
        Order order;
        OrderItem orderItem;
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        try {
            Address address = new Address();
            address.setId(Integer.parseInt(
                    request.getParameter(Constants.LABEL_ADDRESS_ID)));
            List<Integer> warehouseProductIds = new ArrayList<Integer>();
            for (String id : request.getParameterValues(Constants.LABEL_ID)) {
                warehouseProductIds.add(Integer.parseInt(id));
            }
            List<WarehouseProduct> warehouseProducts = customerService
                    .getWarehouseProductsByIds(warehouseProductIds);
            List<CartItem> cartItems = customer.getCartItems();
            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            Float totalPrice = (float) 0;
            for (Integer i = 0; i < cartItems.size(); i++) {
                for (Integer j = 0; j < warehouseProducts.size(); j++) {
                    if (warehouseProducts.get(j).getId() == cartItems.get(i)
                            .getWarehouseProduct().getId()) {
                        orderItem = new OrderItem();
                        orderItem.setQuantity(cartItems.get(i).getQuantity());
                        orderItem.setPrice(cartItems.get(i).getQuantity()
                                * (warehouseProducts.get(j)).getPrice());
                        orderItem.setWarehouseProduct(warehouseProducts.get(j));
                        orderItem.setStatus(ORDER_STATUS.ORDERED);
                        orderItems.add(orderItem);
                        totalPrice = totalPrice + orderItem.getPrice();
                    }
                }
            }
            order = new Order();
            order.setAddress(address);
            order.setOrderItems(orderItems);
            LocalDate todayDate = LocalDate.now();
            order.setOrderDate(Date.valueOf(todayDate));
            order.setPrice(totalPrice);
            order.setCustomer(customer);
            order.setModeOfPayment(
                    request.getParameter(Constants.LABEL_MODE_OF_PAYMENT));
            List<OrderItem> unplacedOrders = customerService.addOrder(order);
            if (unplacedOrders.isEmpty()) {
                deleteCartProducts(request, customer, warehouseProducts);
                customer = customerService.getCustomerById(customer.getId(),
                        Boolean.TRUE);
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                session.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_SUCCESS);
            } else {
                session.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_DONT_HAVE_ENOUGH_QUANTITY);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to delete cart product details after placing an order
     * </p>
     *
     * @param customer          needed for remove cart products from customer
     *                          object
     * @param warehouseProducts needed for which cart products want to remove
     *                          from customer object
     */
    private void deleteCartProducts(HttpServletRequest request,
            Customer customer, List<WarehouseProduct> warehouseProducts) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<CartItem> cartItems = customer.getCartItems();
            for (Integer i = 0; i < cartItems.size(); i++) {
                if (warehouseProducts.get(i).getId() == cartItems.get(i)
                        .getWarehouseProduct().getId()) {
                    cartItems.remove(cartItems.get(i));
                }
            }
            customer.setCartItems(cartItems);
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
    }

    /**
     * <p>
     * This method is used to search products from e-commerce web-site based on
     * category id and product name.
     * </p>
     */
    @PostMapping("searchProduct")
    public ModelAndView searchProduct(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer categoryId = Integer.parseInt(
                    request.getParameter(Constants.LABEL_CATEGORY_ID));
            String productName = request.getParameter(Constants.LABEL_NAME);
            List<Product> products = new ArrayList<Product>();
            if (0 == categoryId) {
                products = customerService
                        .searchProduct(Constants.PERCENT_SYMBOL + productName
                                + Constants.PERCENT_SYMBOL);
            } else {
                products = customerService.searchProduct(categoryId,
                        Constants.PERCENT_SYMBOL + productName
                                + Constants.PERCENT_SYMBOL);
            }
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
            modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_UPDATE_ADDRESS_FAIL);
        }
        modelAndView.setViewName(Constants.JSP_CUSTOMER_HOME);
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to display particular product details such as
     * available sellers for this product, rating of this product, price, image,
     * description about that product etc.
     * </p>
     * 
     * @param id Needed for which product details will display.
     */
    @PostMapping("productPage")
    public ModelAndView getProduct(@RequestParam(Constants.LABEL_ID) Integer id,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Product product = customerService.searchProduct(id);
            modelAndView.addObject(Constants.LABEL_PRODUCT, product);
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
            modelAndView.setViewName(Constants.JSP_PRODUCT_PAGE);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_PRODUCT_PAGE_OPEN_FAIL);
            modelAndView.setViewName(Constants.JSP_CUSTOMER_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to get product details based on category.
     * </p>
     */
    @PostMapping("products")
    public ModelAndView getProductsByCategory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer categoryId = Integer.parseInt(
                    request.getParameter(Constants.LABEL_CATEGORY_ID));
            Category category = customerService.getCategory(categoryId);
            List<Product> products = category.getProducts();
            modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    customerService.getAllCategories());
            modelAndView.setViewName(Constants.JSP_CUSTOMER_HOME);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_PRODUCT_PAGE_OPEN_FAIL);
            modelAndView.setViewName(Constants.JSP_CUSTOMER_HOME);
        }
        return modelAndView;
    }

}
