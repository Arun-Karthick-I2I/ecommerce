package com.ideas2it.ecommerce.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.User;
import com.ideas2it.ecommerce.service.SellerService;
import com.ideas2it.ecommerce.service.impl.SellerServiceImpl;

/**
 * <p>
 * The {@code SellerController} class provides functionality related to the
 * management of sellers. It provides sellers to control their warehouse
 * products such as quantity or price updation.
 * </p>
 */
public class SellerController {
    private SellerService sellerService = new SellerServiceImpl();

    public ModelAndView register(
            @ModelAttribute(Constants.LABEL_SELLER) Seller seller,
            HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("SellerHome");
        User user = new User();
        user.setId((Integer) session.getAttribute(Constants.LABEL_USER_ID));
        seller.setUser(user);
        try {
            if (null != sellerService.checkSellerExistence(seller)) {
                modelAndView = new ModelAndView("SellerForm", 
                    Constants.LABEL_MESSAGE, 
                        Constants.MSG_SELLER_ALREADY_EXIST);
            } else if (sellerService.registerSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_SELLER_REGISTER_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView = new ModelAndView("SellerHome", 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
}
