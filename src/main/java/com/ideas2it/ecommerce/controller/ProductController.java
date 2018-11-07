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
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.service.ProductService;
import com.ideas2it.ecommerce.service.impl.ProductServiceImpl;

/**
 * <p>
 * 
 * </p>
 * 
 * @author Pavithra.S
 *
 */
@Controller
@RequestMapping("product")
public class ProductController {
    private ProductService productService = new ProductServiceImpl();
    
    @PostMapping("display")
    private ModelAndView displayProducts() {
        List<Product> products = new ArrayList<Product>();
        products = getProducts();
        if (!products.isEmpty()) {
            return new ModelAndView("displayProducts",
                "products",products);  
        } else {
            return new ModelAndView("displayProducts",
                Constants.LABEL_MESSAGE,Constants.MSG_PRODUCTS_UNAVAILABLE);
        }
    }

    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    @PostMapping("searchById") 
    private ModelAndView searchById(@RequestParam("id")Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = new ArrayList<Product>();
        try {
            Product product = productService.searchById(id);
            if (null != product) {
                modelAndView.addObject("product", product);
                modelAndView.setViewName("displayProduct");
            } else {
                products = getProducts();
                modelAndView.addObject("products", products);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_PRODUCT_NOT_AVAILABLE);
                modelAndView.setViewName("displayProducts");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @return
     */
    @PostMapping("searchByName") 
    private ModelAndView searchByName(@RequestParam("name")String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = new ArrayList<Product>();
        try {
            Product product = productService.searchByName(name);
            if (null != product) {
                modelAndView.addObject("product", product);
                modelAndView.setViewName("displayProduct");
            } else {
                products = getProducts();
                modelAndView.addObject("products", products);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_PRODUCT_NOT_AVAILABLE);
                modelAndView.setViewName("displayProducts");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    private List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();
        try {
            products = productService.getProducts();
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return products;
    }
}
