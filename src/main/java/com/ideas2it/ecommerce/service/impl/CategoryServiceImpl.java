package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.CategoryDao;
import com.ideas2it.ecommerce.dao.impl.CategoryDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao = new CategoryDaoImpl();
	
	/**
     * {@inheritDoc}
     */
    public List<Category> getCategories() throws EcommerceException { 
        return categoryDao.getCategories(); 
    }
    
    /**
     * {@inheritDoc}
     */
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
    public Boolean deleteCategory(Integer id) throws EcommerceException {
        return categoryDao.deleteCategory(id);
    }
    
    /**
     * {@inheritDoc}
     */ 
    public Boolean updateCategory(Category category) throws EcommerceException {
        return categoryDao.updateCategory(category);
    }
    
    /**
     * {@inheritDoc}
     */
    public Category searchById(Integer id) throws EcommerceException {
        return categoryDao.getById(id);
    }
    
    /**
     * {@inheritDoc}
     */
    public Category searchByName(String name) throws EcommerceException {
        return categoryDao.getByName(name);
    }
}
