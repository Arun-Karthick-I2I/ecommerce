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
 * 
 * </p>
 * 
 * @author Pavithra.S
 *
 */

@Controller
@RequestMapping("category")
public class CategoryController {
    private CategoryService categoryService = new CategoryServiceImpl(); 
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @return 
     */
    @RequestMapping(value = "display", method = RequestMethod.POST)
    private ModelAndView displayCategories() {
        List<Category> categories = new ArrayList<Category>();
        categories = getCategories();
        if (!categories.isEmpty()) {
            return new ModelAndView("displayCategories",
                "categories",categories);  
        } else {
            return new ModelAndView("displayCategories",
                Constants.LABEL_MESSAGE,Constants.MSG_CATEGORIES_UNAVAILABLE);
        }
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)  
    public ModelAndView addForm(){  
        return new ModelAndView("addCategory","command",new Category());  
    } 
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param category
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST) 
    private ModelAndView addCategory(@ModelAttribute("category") 
            Category category) {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = new ArrayList<Category>();
        try {
            if (categoryService.insertCategory(category)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_INSERT_CATEGORY_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CATEGORY_EXISTS);  
            }
            categories = getCategories();
            modelAndView.addObject("categories", categories);
            modelAndView.setViewName("displayCategories"); 
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
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST) 
    private ModelAndView deleteCategory(@RequestParam("id")Integer id) {
        List<Category> categories = new ArrayList<Category>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (categoryService.deleteCategory(id)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CATEGORY_DELETE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CATEGORY_DELETE_FAILURE);
            }
            categories = getCategories();
            modelAndView.addObject("categories", categories);
            modelAndView.setViewName("displayCategories");
        } catch(EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    private ModelAndView editForm(@RequestParam("id")Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Category category = getCategory(id);
        modelAndView.addObject("category", category);
        modelAndView.addObject("command", new Category());
        modelAndView.setViewName("addCategory");
        return modelAndView;
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param category
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    private ModelAndView updateCategory(@ModelAttribute("category") 
            Category category) {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = new ArrayList<Category>();
        try {
            if (categoryService.updateCategory(category)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CATEGORY_UPDATE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CATEGORY_EXISTS);
            }
            categories = getCategories();
            modelAndView.addObject("categories", categories);
            modelAndView.setViewName("displayCategories");
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
     * @param id
     * @return
     */
    private Category getCategory(Integer id) {
        Category category = new Category();
        try {
            category = categoryService.retrieveById(id);
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return category;
    }
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     */
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
