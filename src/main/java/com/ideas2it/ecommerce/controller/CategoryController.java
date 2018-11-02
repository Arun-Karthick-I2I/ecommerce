package com.ideas2it.ecommerce.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.ModelAndView;  

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.service.CategoryService;
import com.ideas2it.ecommerce.service.impl.CategoryServiceImpl;

/**
 * <p>
 * The CategoryController gets the inputs from the user on which the operations 
 * such as add, delete, update and display are performed.
 * </p>
 * @author Pavithra.S
 *
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    private CategoryService categoryService = new CategoryServiceImpl(); 
    
    @RequestMapping(value = "display", method = RequestMethod.POST)
    private ModelAndView displayCategories() {
        List<Category> categories = new ArrayList<Category>();
        categories = getCategories();
        if (!categories.isEmpty()) {
            return new ModelAndView("displayCategories",
                "caegories",categories);  
        } else {
            return new ModelAndView("displayCategories",
                Constants.LABEL_MESSAGE,Constants.MESSAGE_CATEGORIES_UNAVAILABLE);
        }
    }
    
    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = categoryService.getCategories();
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return categories;
    }
}
