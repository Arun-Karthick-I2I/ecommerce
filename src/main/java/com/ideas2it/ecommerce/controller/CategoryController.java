package com.ideas2it.ecommerce.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.ModelAndView;  

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.service.CategoryService;
import com.ideas2it.ecommerce.service.impl.CategoryServiceImpl;

/**
 * <p>
 * Used to perform basic functionalities such as displaying all the available 
 * Categories, adding new Category, deleting or updating an existing Category,
 * fetching a Category by ID, and displaying all the products for a specific
 * Category.
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
     * Used to display the details of all the Categories available.
     * </p>
     * 
     * @return  Returns the list of Categories. Otherwise, returns a failure 
     *          message indicating that no Categories are available. 
     */
    @PostMapping("display")
    private ModelAndView displayCategories() {
        List<Category> categories = new ArrayList<Category>();
        categories = getCategories();
        Collections.sort(categories);
        for (Category category : categories) {
        System.out.println("Categories" +category.getId());
        }
        if (!categories.isEmpty()) {
            return new ModelAndView("displayCategories",
                Constants.LABEL_CATEGORIES,categories);  
        } else {
            return new ModelAndView("displayCategories",
                Constants.LABEL_MESSAGE,Constants.MSG_CATEGORIES_UNAVAILABLE);
        }
    }
    
    /**
     * <p>
     * Gets the input such as Category Name from the user and stores the new
     * Category obtained to the database. 
     * </p>
     * 
     * @param   category  New Category to be inserted
     * @return            Returns success message, if the Category has been 
     *                    inserted successfully. Otherwise returns failure 
     *                    message, if the insertion is unsuccessful. 
     */
    @PostMapping("insert") 
    private ModelAndView addCategory(@ModelAttribute(Constants.LABEL_CATEGORY) 
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
            modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
            modelAndView.setViewName("displayCategories"); 
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to delete the Category based on the ID specified.
     * </p>
     * 
     * @param   id  ID of the Category to deleted.
     * @return      Returns success message, if the Category has been deleted
     *              successfully. Otherwise returns failure message, if the
     *              deletion is unsuccessful. 
     */
    @PostMapping("delete") 
    private ModelAndView deleteCategory(@RequestParam
            (Constants.LABEL_ID)Integer id) {
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
            modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
            modelAndView.setViewName("displayCategories");
        } catch(EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to alter the Name of the Category for the ID specified.
     * </p>
     * 
     * @param   category  Updated Category to be inserted
     * @return            Returns success message, if the Category has been 
     *                    updated successfully. Otherwise, returns failure 
     *                    message, if the update is unsuccessful.
     */
    @PostMapping("update")
    private ModelAndView updateCategory(@ModelAttribute
            (Constants.LABEL_CATEGORY)Category category) {
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
            modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
            modelAndView.setViewName("displayCategories");
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        } 
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to retrieve the details of the Category for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Category whose details are to retrieved.
     * @return      Returns the Category for the ID specified. Otherwise,
     *              returns a failure message indicating that the Category 
     *              of the specified ID isn't available.
     */
    @PostMapping("searchById") 
    private ModelAndView searchById(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = new ArrayList<Category>();
        try {
            Category category = categoryService.searchById(id);
            if (null != category) {
                modelAndView.addObject(Constants.LABEL_CATEGORY, category);
                modelAndView.setViewName("displayCategory");
            } else {
                categories = getCategories();
                modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CATEGORY_NOT_AVAILABLE);
                modelAndView.setViewName("displayCategories");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to retrieve the details of the Category based on the name specified. 
     * </p>
     * 
     * @param   name  Name of the Category whose details are to retrieved
     * @return        Returns the Category for the name specified. 
     *                Otherwise, returns a failure message indicating that the
     *                Category is not available for the name specified. 
     */
    @PostMapping("searchByName") 
    private ModelAndView searchByName(@RequestParam
            (Constants.LABEL_NAME)String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = new ArrayList<Category>();
        try {
            Category category = categoryService.searchByName("%"+name+"%");
            if (null != category) {
                categories.add(category);
                modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
                modelAndView.setViewName("displayCategories");
            } else {
                categories = getCategories();
                modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CATEGORY_NOT_AVAILABLE);
                modelAndView.setViewName("displayCategories");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to display all the products for the Category specified.
     * </p>
     * 
     * @param   id  ID of the Category whose Products to be displayed.
     * @return      Returns list of Products to be displayed for the Category
     *              ID specified. Otherwise, returns failure message indicating
     *              no products are available.
     */
    @PostMapping("displayProducts") 
    private ModelAndView displayProducts(@RequestParam
            (Constants.LABEL_ID)Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = getCategories();
        try {
            Category category = categoryService.searchById(id);
            if (null != category) {
                List<Product> products = category.getProducts();
                if (!products.isEmpty()) {
                    modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
                    modelAndView.setViewName("adminDisplayProducts");
                } else {
                    modelAndView.addObject
                        (Constants.LABEL_CATEGORIES,categories);
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MSG_CATEGORY_PRODUCTS_UNAVAILABLE);
                    modelAndView.setViewName("displayCategories");
                }
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MSG_CATEGORY_NOT_AVAILABLE);
                modelAndView.setViewName("displayCategories");
            }
        } catch (EcommerceException e) {
            EcommerceLogger.error(e.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Used to fetch the details of all the Categories available.
     * </p>
     * 
     * @return  Returns the list of Categories available. Otherwise, returns 
     *          an empty object.
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
