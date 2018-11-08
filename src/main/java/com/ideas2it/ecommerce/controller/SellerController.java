package com.ideas2it.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Address;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.service.SellerService;
import com.ideas2it.ecommerce.service.impl.SellerServiceImpl;

/**
 * <p>
 * The {@code SellerController} class provides functionality related to the
 * management of sellers. It provides sellers to control their warehouse
 * products such as quantity or price updation.
 * </p>
 */
@Controller
@RequestMapping("seller")
public class SellerController {
    private static final String INDEX_PAGE = "index";
    private static final String EDIT_SELLER = "EditSeller";
    private static final String SELLER_HOME = "SellerHome";

    private SellerService sellerService = new SellerServiceImpl();

    @GetMapping("/viewProfile")
    public ModelAndView modifyAccount(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(EDIT_SELLER);
        try {
            Seller seller = sellerService.searchSeller(
                    (Integer) session.getAttribute(Constants.LABEL_SELLER_ID));
            modelAndView.addObject(Constants.LABEL_SELLER, seller);
        } catch (EcommerceException e) {
            modelAndView.setViewName(INDEX_PAGE);
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping("/addAddress")
    private ModelAndView addAddress(@ModelAttribute("address") Address address,
            HttpSession session) throws EcommerceException {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        Seller seller = sellerService.searchSeller(
                (Integer) session.getAttribute(Constants.LABEL_SELLER_ID));
        try {
            List<Address> addresses = seller.getAddresses();
            addresses.add(address);
            seller.setAddresses(addresses);
            if (sellerService.updateSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ADDRESS_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping("/updateAddress")
    private ModelAndView updateAddress(@ModelAttribute("address") Address address,
            HttpSession session) throws EcommerceException {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        Seller seller = sellerService.searchSeller(
                (Integer) session.getAttribute(Constants.LABEL_SELLER_ID));
        try {
            List<Address> addresses = seller.getAddresses();
            Address existingaddress = new Address();
            existingaddress.setId(address.getId());
            addresses.remove(existingaddress);
            addresses.add(address);
            seller.setAddresses(addresses);
            if (sellerService.updateSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_UPDATE_ADDRESS_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
    
    private ModelAndView removeAddress(HttpServletRequest request) {
        
    }

}
