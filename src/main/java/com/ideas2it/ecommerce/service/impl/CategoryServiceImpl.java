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
}
