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
import com.ideas2it.ecommerce.common.enums.Role.ORDER_STATUS;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Address;
import com.ideas2it.ecommerce.model.CartProduct;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.Product;
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
        HttpSession session = request.getSession(Boolean.FALSE);
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
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Order order = new Order();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            order.setId(Integer.parseInt(id));
            order.setStatus(ORDER_STATUS.CANCELLED);
            if (customerService.cancelOrder(order)) {
                List<Order> orders = customer.getOrders();
                for (Integer i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getId() == Integer.parseInt(id)) {
                        orders.remove(orders.get(i));
                    }
                }
                customer.setOrders(orders);
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

    /**
     * <p>
     * This method is used to display particular customer order details
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "CartDisplay" is the view name and set
     *         of cartProducts is the model.
     */
    @GetMapping("/Cart")
    public ModelAndView myCart(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session
                .getAttribute(Constants.LABEL_CUSTOMER);
        List<CartProduct> cartProducts = customer.getCartProducts();
        if (cartProducts.isEmpty()) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CART_EMPTY);
        }
        modelAndView.addObject("cartProducts", cartProducts);
        modelAndView.setViewName("CartDisplay");
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
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            Integer quantity = Integer
                    .parseInt((String) request.getParameter("quantity"));
            Integer id = Integer.parseInt(request.getParameter("id"));
            List<CartProduct> cartProducts = customer.getCartProducts();
            Boolean result = Boolean.TRUE;
            for (Integer i = 0; i < cartProducts.size(); i++) {
                if (id == cartProducts.get(i).getId()) {
                    cartProducts.get(i)
                            .setQuantity(cartProducts.get(i).getQuantity() + 1);
                    result = Boolean.FALSE;
                    break;
                }
            }
            if (result) {
                CartProduct cartProduct = new CartProduct();
                WarehouseProduct warehouseProduct = customerService
                        .getWarehouseProduct(id);
                cartProduct.setWarehouseProduct(warehouseProduct);
                cartProduct.setCustomer(customer);
                cartProduct.setQuantity(quantity);
                cartProduct.setPrice(quantity * warehouseProduct.getPrice());
                cartProducts.add(cartProduct);
            }
            customer.setCartProducts(cartProducts);
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                modelAndView.addObject("cartProducts",
                        customer.getCartProducts());
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_CART_FAIL);
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
     * This method is used to remove particular product from cart
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @GetMapping("/removeFromCart")
    public ModelAndView removeFromCart(@RequestParam("id") String id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            List<CartProduct> cartProducts = customer.getCartProducts();
            for (Integer i = 0; i < cartProducts.size(); i++) {
                if (Integer.parseInt(id) == cartProducts.get(i)
                        .getWarehouseProduct().getId()) {
                    cartProducts.remove(cartProducts.get(i));
                    break;
                }
            }
            customer.setCartProducts(cartProducts);
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                modelAndView.addObject("cartProducts",
                        customer.getCartProducts());
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_CART_FAIL);
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
     * This method is used to remove particular product from cart
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @GetMapping("/updateCart")
    public ModelAndView updateCart(@RequestParam("id") String id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            List<CartProduct> cartProducts = customer.getCartProducts();
            Integer quantity = Integer
                    .parseInt(request.getParameter("quantity"));
            for (Integer i = 0; i < cartProducts.size(); i++) {
                if (Integer.parseInt(id) == cartProducts.get(i).getId()) {
                    cartProducts.get(i).setQuantity(quantity);
                    break;
                }
            }
            customer.setCartProducts(cartProducts);
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                modelAndView.addObject("cartProducts",
                        customer.getCartProducts());
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
     * This method is used to purchase a particular product.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @PostMapping("buyProduct")
    public ModelAndView placeOrder(@RequestParam("id") String id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
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
            order.setStatus(ORDER_STATUS.ORDERED);
            List<Order> orders = new ArrayList<Order>();
            orders.add(order);
            List<Order> unplacedOrders = customerService.addOrders(orders);
            if (unplacedOrders.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_SUCCESS);
                modelAndView.setViewName("OrdersDisplay");
            } else if (unplacedOrders.size() == orders.size()) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_FAIL);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_SOME_ORDER_FAIL);
                modelAndView.addObject("unplacedOrders", unplacedOrders);
            }
            customer = customerService.getCustomerById(customer.getId(),
                    Boolean.TRUE);
            session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_ADD_ORDER_FAIL);
            modelAndView.setViewName("CustomerOperations");
        }
        return myOrders(request);
    }

    /**
     * <p>
     * This method is used to purchase list of products for a particular
     * customer.
     * </p>
     * 
     * @return ModelAndView ModelAndView is an object that holds both the model
     *         and view. In this method "OrdersDisplay" is the view name and set
     *         of orders and customer is the model.
     */
    @PostMapping("purchaseProducts")
    public ModelAndView PurchaseProducts(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Order order;
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            Address address = new Address();
            address.setId(Integer.parseInt(request.getParameter("addressId")));
            List<Integer> warehouseProductIds = new ArrayList<Integer>();
            for (String id : request.getParameterValues("warehouseProductId")) {
                warehouseProductIds.add(Integer.parseInt(id));
            }
            List<WarehouseProduct> warehouseProducts = customerService
                    .getWarehouseProductsByIds(warehouseProductIds);
            List<CartProduct> cartProducts = customer.getCartProducts();
            List<Order> orders = customer.getOrders();
            for (Integer i = 0; i < cartProducts.size(); i++) {
                if (warehouseProducts.get(i).getId() == cartProducts.get(i)
                        .getWarehouseProduct().getId()) {
                    order = new Order();
                    order.setCustomer(customer);
                    order.setWarehouseProduct(warehouseProducts.get(i));
                    order.setPrice(cartProducts.get(i).getQuantity()
                            * (warehouseProducts.get(i)).getPrice());
                    order.setQuantity(cartProducts.get(i).getQuantity());
                    order.setAddress(address);
                    LocalDate todayDate = LocalDate.now();
                    order.setOrderDate(todayDate);
                    order.setStatus(ORDER_STATUS.ORDERED);
                    orders.add(order);
                }
            }
            List<Order> unplacedOrders = customerService.addOrders(orders);
            if (unplacedOrders.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_SUCCESS);
                modelAndView.setViewName("OrdersDisplay");
            } else if (unplacedOrders.size() == orders.size()) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_FAIL);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_SOME_ORDER_FAIL);
                modelAndView.addObject("unplacedOrders", unplacedOrders);
            }
            customer = customerService.getCustomerById(customer.getId(),
                    Boolean.TRUE);
            session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        modelAndView.addObject("orders", customer.getOrders());
        modelAndView.setViewName("OrdersDisplay");
        return myOrders(request);
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
    private void deleteCartProducts(Customer customer,
            List<WarehouseProduct> warehouseProducts) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<CartProduct> cartProducts = customer.getCartProducts();
            for (Integer i = 0; i < cartProducts.size(); i++) {
                if (warehouseProducts.get(i).getId() == cartProducts.get(i)
                        .getWarehouseProduct().getId()) {
                    cartProducts.remove(cartProducts.get(i));
                }
            }
            customer.setCartProducts(cartProducts);
            if (customerService.updateCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ORDER_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
    }

    /**
     * <p>
     * This method is used to add new delivery address of the customer
     * </p>
     *
     * @param address needed for add new address. It contains street, city,
     *                state, pin-code of delivery address
     */
    @PostMapping("addAddress")
    public ModelAndView addAddress(@ModelAttribute("address") Address address,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            List<Address> addresses = customer.getAddresses();
            addresses.add(address);
            customer.setAddresses(addresses);
            if (customerService.updateCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ADDRESS_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ADDRESS_FAIL);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        modelAndView.setViewName("myAccount");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to delete delivery address of the customer
     * </p>
     *
     * @param address needed for add new address. It contains street, city,
     *                state, pin-code of delivery address
     */
    @PostMapping("deleteAddress")
    public ModelAndView deleteAddress(@RequestParam("id") String id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            List<Address> addresses = customer.getAddresses();
            for (Integer i = 0; i < addresses.size(); i++) {
                if (Integer.parseInt(id) == addresses.get(i).getId()) {
                    addresses.remove(addresses.get(i));
                    break;
                }
            }
            customer.setAddresses(addresses);
            if (customerService.updateCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_DELETE_ADDRESS_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_DELETE_ADDRESS_FAIL);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_DELETE_ADDRESS_FAIL);
        }
        session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        modelAndView.setViewName("myAccount");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to update delivery address of the customer
     * </p>
     *
     * @param address needed for update address. It contains street, city,
     *                state, pin-code of delivery address
     */
    @PostMapping("updateAddress")
    public ModelAndView updateAddress(
            @ModelAttribute("address") Address address,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            List<Address> addresses = customer.getAddresses();
            for (Integer i = 0; i < addresses.size(); i++) {
                if (address.getId() == addresses.get(i).getId()) {
                    addresses.remove(addresses.get(i));
                    addresses.add(address);
                    break;
                }
            }
            customer.setAddresses(addresses);
            if (customerService.updateCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_UPDATE_ADDRESS_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_UPDATE_ADDRESS_FAIL);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_UPDATE_ADDRESS_FAIL);
        }
        session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        modelAndView.setViewName("myAccount");
        return modelAndView;
    }

    /**
     * <p>
     * This method is used to search products from e-commerce web-site based on
     * category id and product name.
     * </p>
     */
    @PostMapping("searchProduct")
    public ModelAndView searchProduct(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = (Customer) session.getAttribute("customer");
        try {
            Integer categoryId = Integer.parseInt(request.getParameter("id"));
            String productName = request.getParameter("name");
            List<Product> products = new ArrayList<Product>();
            if (null == categoryId) {
                products = customerService.searchProduct(productName);
            } else {
                products = customerService.searchProduct(categoryId,
                        productName);
            }
            if (products.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_PRODUCT_LIST_EMPTY);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_UPDATE_ADDRESS_FAIL);
        }
        session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        modelAndView.setViewName("homepage");
        return modelAndView;
    }


    /**
     * <p>
     * This method is used to display particular product details such as available sellers for this product,
     * rating of this product, price, image, description about that product etc.
     * </p>
     * 
     * @param id Needed for which product details will display.
     */
    @PostMapping("productPage")
    public ModelAndView getProduct(@RequestParam("id") String id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        try {
            Product product = customerService
                    .searchProduct(Integer.parseInt("id"));
            List<WarehouseProduct> warehouseProducts = product
                    .getWarehouseProducts();
            modelAndView.addObject("product", product);
            modelAndView.setViewName("productPage");
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_PRODUCT_PAGE_OPEN_FAIL);
            modelAndView.setViewName("homepage");
        }
        return modelAndView;
    }

}
