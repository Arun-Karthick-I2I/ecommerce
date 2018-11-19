package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.exception.EcommerceException;

/**
 * <p>
 * This interface provides basic functionalities such as get all 
 * Categories, add new Category, update or delete an existing Category, 
 * fetch Category using ID or Name specified. 
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public interface CategoryService {
	
	/**
	 * <p>
	 * Used to fetch the details of all the available Categories.
	 * </p>
	 * 
	 * @return  Returns the list of Categories available. Otherwise,
     *          returns an empty Object.
	 */
    List<Category> getCategories() throws EcommerceException;
    
    /**
     * <p>
     * Used to add new Category to the list using the inputs obtained from user.
     * Before add the Category checks whether the Category already exists.
     * </p>
     * 
     * @param   category  New Category to be inserted
     * @return            Returns true, if the Category has been inserted 
     *                    successfully. Otherwise returns false, if the 
     *                    insertion is unsuccessful. 
     */
    Boolean insertCategory(Category category) throws EcommerceException;
    
    /**
     * <p>
     * Used to delete the Category based on the ID specified.
     * </p>
     * 
     * @param   id  ID of the Category to deleted.
     * @return      Returns true, if the Category has been deleted
     *              successfully. Otherwise returns false, if the deletion is
     *              unsuccessful. 
     */
    Boolean deleteCategory(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * Used to alter the Name of the Category for the ID specified.
     * </p>
     * 
     * @param   category  Updated Category to be inserted
     * @return            Returns true, if the Category has been updated 
     *                    successfully. Otherwise returns false, if the 
     *                    update is unsuccessful.
     */
    Boolean updateCategory(Category category) throws EcommerceException;
    
    /**
     * <p>
     * Used to fetch the details of the Category for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Category whose details are to fetched.
     * @return      Returns the Category for the ID specified. Otherwise,
     *              returns an empty object.
     */
    Category searchById(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * Used to fetch the details of the Category based on the name specified. 
     * </p>
     * 
     * @param   name  Name of the Category whose details are to fetched
     * @return        Returns the Category for the name specified. 
     *                Otherwise, returns a failure message indicating that the
     *                Category is not available for the name specified.
     */
    Category searchByName(String name) throws EcommerceException;
}
