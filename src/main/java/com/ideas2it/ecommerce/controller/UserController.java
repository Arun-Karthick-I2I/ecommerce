
package com.ideas2it.ecommerce.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.common.enums.USER_ROLES;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Address;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Customer;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.User;
import com.ideas2it.ecommerce.service.UserService;
import com.ideas2it.ecommerce.service.impl.UserServiceImpl;

/**
 * <p>
 * The {@code UserController} class provides functionality related to the
 * management of users. It provides option to create and validate login
 * credentials.
 * </p>
 * 
 * @author Arun Karthick.J
 */
@Controller
public class UserController {

    private UserService userService = new UserServiceImpl();

    /**
     * <p>
     * Redirects the user to the welcome page of the ecommerce along with
     * necessary data.
     * </p>
     */
    @GetMapping("/")
    public ModelAndView showInitialPage() {
        ModelAndView modelAndView = new ModelAndView(Constants.CUSTOMER_HOME);
        try {
            modelAndView.addObject(Constants.LABEL_PRODUCTS,
                    userService.getAllProducts());
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                    userService.getAllCategories());
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Creates an entry for the user if no such user exists else returns a
     * message accordingly.
     * </p>
     */
    @PostMapping("registerCustomer")
    public ModelAndView registerCustomer(
            @ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView(
                Constants.REDIRECT + Constants.INITIAL_PATH);
        customer.setMobileNumber(customer.getUser().getUserName());
        try {
            if (userService.checkUserNameAvailability(customer.getUser())) {
                if (userService.registerCustomer(customer)) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                            Constants.MSG_REGISTER_SUCCESS
                                    + customer.getMobileNumber());
                }
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ANOTHER_USER_EXISTS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Creates an entry for the user if no such user exists else returns a
     * message accordingly.
     * </p>
     */
    @PostMapping("registerSeller")
    public ModelAndView registerSeller(
            @ModelAttribute("seller") Seller seller) {
        ModelAndView modelAndView = new ModelAndView(Constants.SELLER_LOGIN);
        seller.setMobileNumber(seller.getUser().getUserName());
        try {
            if (userService.checkUserNameAvailability(seller.getUser())) {
                if (userService.registerSeller(seller)) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                            Constants.MSG_REGISTER_SUCCESS
                                    + seller.getMobileNumber());
                }
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ANOTHER_USER_EXISTS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Checks the user name and password and logins the user if the credentials
     * are correct. Redirects to the corresponding page based on their role.
     * </p>
     */
    @PostMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.TRUE);
        if (!session.isNew()) {
            session.invalidate();
            session = request.getSession(Boolean.TRUE);
        }
        session.setMaxInactiveInterval(Constants.SESSION_MAX_INACTIVE_INTERVAL);
        User user = new User();
        user.setUserName(request.getParameter(Constants.LABEL_USERNAME));
        user.setPassword(request.getParameter(Constants.LABEL_PASSWORD));
        user.setRole(
                USER_ROLES.valueOf(request.getParameter(Constants.LABEL_ROLE)));
        ModelAndView modelAndView = new ModelAndView();
        if (USER_ROLES.CUSTOMER == user.getRole()) {
            modelAndView
                    .setViewName(Constants.REDIRECT + Constants.INITIAL_PATH);
        } else if (USER_ROLES.SELLER == user.getRole()) {
            modelAndView.setViewName(Constants.SELLER_LOGIN);
        } else if (USER_ROLES.ADMIN == user.getRole()) {
            modelAndView.setViewName(Constants.ADMIN_LOGIN);
        }
        try {
            Boolean isAuthenticated = userService.validateUser(user);
            if (null == isAuthenticated) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_NO_SUCH_USER_EXISTS);
            } else if (!isAuthenticated) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_INVALID_CREDENTIALS);
            } else {
                session.setAttribute(Constants.LABEL_USER_ID, user.getId());
                session.setAttribute(Constants.LABEL_ROLE, user.getRole());
                if (USER_ROLES.CUSTOMER == user.getRole()) {
                    Customer customer = userService
                            .searchCustomer(user.getId());
                    session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                } else if (USER_ROLES.SELLER == user.getRole()) {
                    loginSeller(modelAndView, user, session);
                } else {
                    List<Category> categories = userService.getAllCategories();
                    Collections.sort(categories);
                    modelAndView.addObject(Constants.LABEL_CATEGORIES,
                            categories);
                    modelAndView.setViewName(Constants.ADMIN_HOME);
                }
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the details required to be displayed on the seller home.
     * </p>
     */
    private void loginSeller(ModelAndView modelAndView, User user,
            HttpSession session) throws EcommerceException {
        Seller seller = userService.searchSeller(user.getId());
        session.setAttribute(Constants.LABEL_SELLER_ID, seller.getId());
        modelAndView.addObject(Constants.LABEL_WAREHOUSE_PRODUCTS,
                userService.getAllWarehouseProducts(seller.getId()));
        modelAndView.setViewName(Constants.SELLER_HOME);
    }

    /**
     * <p>
     * Invalidates the session and logs the customer out of the page.
     * </p>
     */
    @GetMapping("logout")
    public String logout(HttpSession session, ModelMap model) {
        String viewName = Constants.REDIRECT + Constants.INITIAL_PATH;
        USER_ROLES role = USER_ROLES.CUSTOMER;
        if (null != session) {
            role = (USER_ROLES) session.getAttribute(Constants.LABEL_ROLE);
            if (USER_ROLES.SELLER == role) {
                viewName = Constants.SELLER_LOGIN;
                model.addAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_LOGGED_OUT);
            } else if (USER_ROLES.ADMIN == role) {
                viewName = Constants.ADMIN_LOGIN;
                model.addAttribute(Constants.LABEL_MESSAGE,
                        Constants.MSG_LOGGED_OUT);
            }
            session.invalidate();
        }

        return viewName;
    }

    /**
     * <p>
     * Shows the list of available address for that user.
     * </p>
     */
    @GetMapping("showAddress")
    public ModelAndView displayAddress(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        USER_ROLES role = (USER_ROLES) session
                .getAttribute(Constants.LABEL_ROLE);
        if (USER_ROLES.CUSTOMER == role) {
            modelAndView.setViewName(Constants.CUSTOMER_HOME);
        } else if (USER_ROLES.SELLER == role) {
            modelAndView.addObject("showAddress", Boolean.TRUE);
            modelAndView.setViewName(Constants.SELLER_PROFILE);
        }
        try {
            User user = userService.searchUser(
                    (Integer) session.getAttribute(Constants.LABEL_USER_ID));
            modelAndView.addObject(Constants.LABEL_ADDRESSES,
                    user.getAddresses());
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Shows the new address form that can be used to provide additional
     * addresses.
     * </p>
     */
    @GetMapping("newAddress")
    public ModelAndView createAddress() {
        ModelAndView modelAndView = new ModelAndView(Constants.SELLER_PROFILE);
        modelAndView.addObject("addressForm", Boolean.TRUE);
        modelAndView.addObject(Constants.LABEL_ADDRESS, new Address());
        return modelAndView;
    }

    /**
     * <p>
     * Adds the new address to the user.
     * </p>
     */
    @PostMapping("addAddress")
    public ModelAndView addAddress(@ModelAttribute("address") Address address,
            HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = userService.searchUser(
                    (Integer) session.getAttribute(Constants.LABEL_USER_ID));
            List<Address> addresses = user.getAddresses();
            addresses.add(address);
            user.setAddresses(addresses);
            if (userService.updateUser(user)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ADDRESS_SUCCESS);
            }
            backtoHome(modelAndView, session, user);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Show the edit address form to the user where he can change the necessary
     * fields.
     * </p>
     */
    @PostMapping("editAddress")
    public ModelAndView editAddress(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.SELLER_PROFILE);
        modelAndView.addObject("addressForm", Boolean.TRUE);
        HttpSession session = request.getSession(Boolean.FALSE);
        Integer addressId = Integer
                .parseInt(request.getParameter(Constants.LABEL_ADDRESS_ID));
        User user;
        try {
            user = userService.searchUser(
                    (Integer) session.getAttribute(Constants.LABEL_USER_ID));
            for (Address address : user.getAddresses()) {
                if (address.getId() == addressId) {
                    modelAndView.addObject(Constants.LABEL_ADDRESS, address);
                    break;
                }
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Updates the address based on the new input from user.
     * </p>
     */
    @PostMapping("updateAddress")
    public ModelAndView updateAddress(
            @ModelAttribute("address") Address address, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = userService.searchUser(
                    (Integer) session.getAttribute(Constants.LABEL_USER_ID));
            List<Address> addresses = user.getAddresses();
            Address existingaddress = new Address();
            existingaddress.setId(address.getId());
            addresses.remove(existingaddress);
            addresses.add(address);
            user.setAddresses(addresses);
            if (userService.updateUser(user)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_UPDATE_ADDRESS_SUCCESS);
            }
            backtoHome(modelAndView, session, user);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Removes the corresponding address from the user.
     * </p>
     */
    @PostMapping("removeAddress")
    public ModelAndView removeAddress(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = userService.searchUser(
                    (Integer) session.getAttribute(Constants.LABEL_USER_ID));
            Address address = new Address();
            address.setId(Integer.parseInt(
                    request.getParameter(Constants.LABEL_ADDRESS_ID)));
            List<Address> addresses = user.getAddresses();
            addresses.remove(address);
            user.setAddresses(addresses);
            if (userService.updateUser(user)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_DELETE_ADDRESS_SUCCESS);
            }
            backtoHome(modelAndView, session, user);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Transfers the control to the corresponding page based on the user role.
     * </p>
     */
    private void backtoHome(ModelAndView modelAndView, HttpSession session,
            User user) {
        USER_ROLES role = (USER_ROLES) session
                .getAttribute(Constants.LABEL_ROLE);
        if (USER_ROLES.CUSTOMER == role) {
            modelAndView.setViewName(Constants.CUSTOMER_PROFILE);
            Customer customer = (Customer) session
                    .getAttribute(Constants.LABEL_CUSTOMER);
            customer.setUser(user);
        } else if (USER_ROLES.SELLER == role) {
            modelAndView.setViewName(Constants.SELLER_HOME);
        }
    }

}