package com.ideas2it.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.common.enums.Role.USER_ROLES;
import com.ideas2it.ecommerce.exception.EcommerceException;
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
 */
public class UserController {
    private static final String INDEX_PAGE = "index";
    
    private UserService userService = new UserServiceImpl();
    
    /**
     * <p>
     * Requests the view handler to show the registration form page.
     * </p>
     * 
     * @param model  A ModelMap object that adds attribute to the Http Request
     *               Object with the help of JSON.
     *
     * @return viewName  Name of the view which has to be displayed
     *
     */
    @GetMapping(value = "registrationForm")
    public String registrationForm(ModelMap model) {
        model.addAttribute(Constants.LABEL_REGISTER, Boolean.TRUE);
        return INDEX_PAGE;
    }

    /**
     * <p>
     * Creates an entry for the user if no such user exists else returns a 
     * MSG accordingly.
     * </p>
     *
     */
    @PostMapping("register")
    public String register(HttpServletRequest request, ModelMap model) {
        User user = new User();
        user.setUserName(request.getParameter(Constants.LABEL_USERNAME));
        user.setPassword(request.getParameter(Constants.LABEL_PASSWORD));
        user.setRole(USER_ROLES.CUSTOMER);
        try {  
            if (userService.register(user)) {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.MSG_REGISTER_SUCCESS);
            } 
        } catch(EcommerceException e) {
            model.addAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return INDEX_PAGE;
    }

    /**
     * <p>
     * Checks the username and password and logins the user if the credentials
     * are correct.
     * </p>
     *
     */
    @PostMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.TRUE);
        session.setMaxInactiveInterval(Constants.SESSION_MAX_INACTIVE_INTERVAL);
        User user = new User();
        user.setUserName(request.getParameter(Constants.LABEL_USERNAME));
        user.setPassword(request.getParameter(Constants.LABEL_PASSWORD));
        user.setRole(USER_ROLES.valueOf(request.getParameter(Constants.LABEL_ROLE)));
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);
        try {
            Boolean isAuthenticated = userService.validateUser(user);
            // Need to Complete 
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Invalidates the session and logs the customer out of the page. 
     * </p>
     *
     */
    @PostMapping("logout")
    public String logout(HttpSession session, ModelMap model) { 
        if (null != session) {
            session.invalidate();
        }
        model.addAttribute(Constants.LABEL_MESSAGE, 
            Constants.MSG_LOGGED_OUT);
        return INDEX_PAGE;
    }

}
