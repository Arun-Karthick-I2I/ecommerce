package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.common.enums.ORDER_STATUS;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;

/**
 * <p>
 * The {@code SellerService} interface provides the operations that can be done
 * by a seller. It provides the basic functions needed such as register, find,
 * view and update seller details.
 * </p>
 *
 * @author Arun Karthick.J
 */
public interface SellerService {

    /**
     * <p>
     * Fetches the details from user and creates an entry or account for the
     * seller if no such account exists
     * </p>
     *
     * @param seller Seller Object with details of a new seller for whom an
     *               entry has to be created
     * @return true If a new account is created for the seller successfully.
     *         false If there is a problem in creating the account.
     */
    Boolean register(Seller seller) throws EcommerceException;

    /**
     * <p>
     * Checks for the presence of sellers with the given mobile number or email
     * id
     * <p>
     *
     * @param seller Seller with mobile number and email id whose presence needs
     *               to be checked
     * @return Seller If a seller with either same email or mobile number exists
     *         null If no such seller exists
     */
    Seller checkSellerExistence(Seller seller) throws EcommerceException;

    /**
     * <p>
     * Searches for the seller with the given mobile number
     * </p>
     * 
     * @param sellerId ID of the seller whose details are being searched for
     * @return seller Returns the seller with the desired mobile number if it
     *         exist Returns null if no such seller exist.
     */
    Seller searchSeller(Integer sellerId) throws EcommerceException;

    /**
     * <p>
     * Fetches the details from user and checks for the correctness from the
     * database and returns message accordingly.
     * </p>
     *
     * @param userId User ID of the Seller who has to be logged in.
     * @return seller If the seller details are correct. null If no such seller
     *         exists.
     */
    Seller searchSellerByUserId(Integer userId) throws EcommerceException;

    /**
     * <p>
     * Deletes the corresponding Seller details
     * </p>
     *
     * @param seller Seller whose details needs to be deleted.
     * @return true If the seller object is deleted successfully. false If the
     *         seller object is not deleted successfully.
     */
    Boolean deleteSeller(Seller seller) throws EcommerceException;

    /**
     * <p>
     * Updates the contents of the corresponding Seller.
     * </p>
     *
     * @param seller Seller whose details needs to be updated.
     * @return true If the details are updated successfully. false If the
     *         details are not updated successfully.
     */
    Boolean updateSeller(Seller seller) throws EcommerceException;

    /**
     * <p>
     * Adds a new product that is available for sale from a particular seller.
     * </p>
     * 
     * @param product Product which needs to be stored
     * @return message Returns true if the new product is added successfully
     *         else returns false
     */
    Boolean addProduct(Product product) throws EcommerceException;

    /**
     * <p>
     * Adds a new product that is available for sale from a particular seller.
     * </p>
     * 
     * @param warehouseProduct WarehouseProduct which needs to be stored
     * @return message Returns true if the new warehouseproduct is added
     *         successfully else returns false
     */
    Boolean addWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    /**
     * <p>
     * Removes a product that is no longer available from a seller.
     * </p>
     * 
     * @param warehouseProduct WarehouseProduct which needs to be removed
     * @return message Returns true if the new warehouseproduct is removed
     *         successfully else returns false
     */
    Boolean deleteWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    /**
     * <p>
     * Updates a product that is available from a particular seller.
     * </p>
     * 
     * @param warehouseProduct WarehouseProduct which needs to be updated
     * @return message Returns true if the new warehouseproduct is updated
     *         successfully else returns false
     */
    Boolean updateWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    /**
     * <p>
     * Searches through the list for the specific WarehouseProduct ID.
     * </p>
     * 
     * @param warehouseProductId ID of the WarehouseProduct which has to be
     *                           searched for in the list.
     * @return warehouseProduct Returns the WarehouseProduct with the desired ID
     *         if it exist Returns null if no such WarehouseProduct exist.
     */
    WarehouseProduct getWarehouseProduct(Integer warehouseProductId)
            throws EcommerceException;

    /**
     * <p>
     * Searches through the list for the specific Product ID and Seller ID.
     * </p>
     * 
     * @param productId ID of the Product which has to be searched for in the
     *                  warehouse.
     * @param sellerId  ID of the Seller whose warehouse needs to be searched
     *                  for the specific product
     * @return warehouseProduct Returns the WarehouseProduct with the desired ID
     *         if it exist Returns null if no such WarehouseProduct exist.
     */
    WarehouseProduct getWarehouseProductByProductId(Integer productId,
            Integer sellerId) throws EcommerceException;

    /**
     * <p>
     * Searches for the list of products with the specified name
     * </p>
     * 
     * @param productName Name of the product which has to be searched for.
     * @return warehouseProduct Returns the list of Products with the desired
     *         name if it exist Returns null if no such products exist.
     */
    List<Product> searchProduct(String productName) throws EcommerceException;

    /**
     * <p>
     * Searches for the list of products with the specified ID
     * </p>
     * 
     * @param productId ID of the product which has to be searched for.
     * @return warehouseProduct Returns the Product with the desired ID if it
     *         exist Returns null if no such products exist.
     */
    Product searchProduct(Integer productId) throws EcommerceException;

    /**
     * <p>
     * Fetches all the products available in the store.
     * </p>
     *
     * @return Products Returns the list of all products available in the store.
     */
    List<Product> getAllProducts() throws EcommerceException;

    /**
     * <p>
     * Searches the warehouse products based on the seller.
     * </p>
     *
     * @param sellerId Seller whose warehouse products needs to be searched.
     * @return warehouseProducts Returns the list of warehouse products
     *         corresponding to the seller.
     */
    List<WarehouseProduct> getAllWarehouseProducts(Integer sellerId)
            throws EcommerceException;

    /**
     * <p>
     * Searches the warehouse products based on the seller.
     * </p>
     *
     * @param sellerId Seller whose warehouse products needs to be searched.
     * @return warehouseProducts Returns the list of warehouse products
     *         corresponding to the seller.
     */
    List<Integer> getWarehouseProductIds(Integer sellerId)
            throws EcommerceException;

    /**
     * <p>
     * It fetches the list of dvdCategories available.
     * </p>
     *
     * @return categories Returns the list of available categories
     */
    List<Category> getAllCategories() throws EcommerceException;

    /**
     * <p>
     * Requests the findCategory function to find category with the specific ID.
     * </p>
     * 
     * @param categoryID Category ID whose details have to be found.
     * @return Category Returns the Category with the desired ID if it exist
     *         Returns null if no such category exist.
     */
    Category searchCategory(Integer categoryId) throws EcommerceException;

    Boolean changeOrderItemsStatus(List<OrderItem> orderItems)
            throws EcommerceException;

    List<OrderItem> searchOrderItems(List<Integer> orderItemIds)
            throws EcommerceException;

    List<OrderItem> searchOrderItemsByWarehouseProductIds(
            List<Integer> warehouseProductIds) throws EcommerceException;

    List<OrderItem> searchOrderItemsByStatus(
            List<Integer> warehouseProductIds, ORDER_STATUS status)
            throws EcommerceException;

    /**
     * <p>
     * Provides the list of all sellers with the specified name from the
     * DVDStore along with their order details.
     * </p>
     * 
     * @param sellerName name of the sellers whose details have to be found.
     * @return sellers Returns the list of sellers with their order details.
     */
    List<Seller> getSellersByName(String sellerName) throws EcommerceException;

    /**
     * <p>
     * Provides the total list of all sellers of the DVDStore along with their
     * order details.
     * </p>
     * 
     * @return sellers Returns the list of sellers with their order details.
     */
    List<Seller> getAllSellers() throws EcommerceException;
}
