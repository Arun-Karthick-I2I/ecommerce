
package com.ideas2it.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.common.enums.Role.USER_ROLES;
import com.ideas2it.ecommerce.exception.EcommerceException;
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
 */
public class UserController {
    private static final String INDEX_PAGE = "index";
    private static final String ADMIN_HOME = "AdminHome";
    private static final String SELLER_HOME = "SellerHome";

    private UserService userService = new UserServiceImpl();

    /**
     * <p>
     * Requests the view handler to show the registration form page.
     * </p>
     * 
     * @param model A ModelMap object that adds attribute to the Http Request
     *              Object with the help of JSON.
     * @return viewName Name of the view which has to be displayed
     */
    @GetMapping(value = "registrationForm")
    public String registrationForm(ModelMap model) {
        model.addAttribute(Constants.LABEL_REGISTER, Boolean.TRUE);
        return INDEX_PAGE;
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
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);
        try {
            if (userService.registerCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_REGISTER_SUCCESS
                                + customer.getMobileNumber());
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
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);
        try {
            if (userService.registerSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_REGISTER_SUCCESS
                                + seller.getMobileNumber());
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Checks the username and password and logins the user if the credentials
     * are correct. Redirects to the corresponding page based on their role.
     * </p>
     */
    @PostMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.TRUE);
        session.setMaxInactiveInterval(Constants.SESSION_MAX_INACTIVE_INTERVAL);
        User user = new User();
        user.setUserName(request.getParameter(Constants.LABEL_USERNAME));
        user.setPassword(request.getParameter(Constants.LABEL_PASSWORD));
        user.setRole(
                USER_ROLES.valueOf(request.getParameter(Constants.LABEL_ROLE)));
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);
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
                if (user.getRole() == USER_ROLES.CUSTOMER) {
                    Customer customer = userService
                            .searchCustomer(user.getId());
                    session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                } else if (user.getRole() == USER_ROLES.SELLER) {
                    Seller seller = userService.searchSeller(user.getId());
                    session.setAttribute(Constants.LABEL_SELLER_ID, seller.getId());
                    modelAndView.setViewName(SELLER_HOME);
                } else {
                    modelAndView.setViewName(ADMIN_HOME);
                }
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Invalidates the session and logs the customer out of the page.
     * </p>
     */
    @PostMapping("logout")
    public String logout(HttpSession session, ModelMap model) {
        if (null != session) {
            session.invalidate();
        }
        model.addAttribute(Constants.LABEL_MESSAGE, Constants.MSG_LOGGED_OUT);
        return INDEX_PAGE;
    }

}
