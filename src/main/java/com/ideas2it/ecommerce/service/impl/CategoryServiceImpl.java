package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.CategoryDao;
import com.ideas2it.ecommerce.dao.impl.CategoryDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.service.CategoryService;

/**
 * <p>
 * This class provides basic functionalities such as get all 
 * Categories, add new Category if the Category doesn't already exist, 
 * update or delete an existing Category, fetch Category using ID or Name
 * specified.
 * </p>
 *  
 * @author Pavithra.S
 *
 */
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao = new CategoryDaoImpl();
	
	/**
     * {@inheritDoc}
     */
	@Override
    public List<Category> getCategories() throws EcommerceException { 
        return categoryDao.getCategories(); 
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
    public Boolean insertCategory(Category category) throws EcommerceException { 
        if ((null != category) 
                && (null != searchByName(category.getName()))) {
            return categoryDao.insertCategory(category);
        }
        return Boolean.FALSE;
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
    public Boolean deleteCategory(Integer id) throws EcommerceException {
        return categoryDao.deleteCategory(id);
    }
    
    /**
     * {@inheritDoc}
     */ 
	@Override
    public Boolean updateCategory(Category category) throws EcommerceException {
        return categoryDao.updateCategory(category);
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
    public Category searchById(Integer id) throws EcommerceException {
        return categoryDao.getById(id);
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
    public Category searchByName(String name) throws EcommerceException {
        return categoryDao.getByName(name);
    }
}
