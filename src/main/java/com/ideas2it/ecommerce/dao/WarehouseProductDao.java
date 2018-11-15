package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.WarehouseProduct;

/**
 * <p>
 * The {@code WarehouseProductDao} interface provides warehouse products related
 * operations that can be performed to a e-commerce Store. It provides the basic
 * operations such as add, remove and find warehouse products from the store.
 * </p>
 *
 * @author Arun Karthick.J
 */
public interface WarehouseProductDao {
    /**
     * <p>
     * Adds a WarehouseProduct into the Database
     * </p>
     *
     * @param warehouseProduct WarehouseProduct which has to be added to the
     *                         Database
     * @return message Returns true if WarehouseProduct is successfully created
     *         and added to the database else Returns false.
     */
    Boolean addWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    /**
     * <p>
     * Removes a WarehouseProduct from the Database
     * </p>
     *
     * @param warehouseProduct WarehouseProduct which has to be removed from the
     *                         Database
     * @return message Returns true if WarehouseProduct is successfully deleted
     *         from the database else Returns false.
     */
    Boolean removeWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    /**
     * <p>
     * Updates the WarehouseProduct details in the Database
     * </p>
     *
     * @param warehouseProduct WarehouseProduct which has to be updated in the
     *                         Database
     * @return message Returns true if WarehouseProduct is successfully updated
     *         in the database else Returns false.
     */
    Boolean updateWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    /**
     * <p>
     * Searches through the database based on WarehouseProduct ID. It compares
     * the WarehouseProduct with those WarehouseProducts in the Database.
     * </p>
     * 
     * @param warehouseProductId Id of the WarehouseProduct which has to be
     *                           searched.
     * @return resultWarehouseProduct Returns the WarehouseProduct with the
     *         desired criteria if it exist Returns null if no such
     *         WarehouseProduct exist.
     */
    WarehouseProduct getWarehouseProductById(Integer warehouseProductId)
            throws EcommerceException;

    /**
     * <p>
     * Returns the list of WarehouseProducts available in the store that matches
     * the product provided.
     * </p>
     *
     * @param productId Id of the Product which has to be searched.
     * @return warehouseProducts Returns the entire WarehouseProduct collection
     *         from the database based on the product ID.
     */
    List<WarehouseProduct> getWarehouseProductsByProductId(Integer productId)
            throws EcommerceException;

    /**
     * <p>
     * Returns the list of WarehouseProducts available in the store that matches
     * the product provided.
     * </p>
     *
     * @param productId Id of the Product which has to be searched.
     * @param seller    Seller whose warehouse products need to be searched.
     * @return warehouseProducts Returns the entire WarehouseProduct collection
     *         from the database based on the product ID.
     */
    WarehouseProduct getWarehouseProductByProductId(Integer productId,
            Integer sellerId) throws EcommerceException;

    /**
     * <p>
     * It fetches the WarehouseProduct List based on the list of ids
     * </p>
     *
     * @param warehouseProductIds List of WarehouseProducts to be fetched.
     * @return warehouseProducts Returns the list of warehouseProducts
     *         corresponding to the ids provided.
     */
    List<WarehouseProduct> getWarehouseProductsByIds(
            List<Integer> warehouseProductIds) throws EcommerceException;

    /**
     * <p>
     * It fetches the WarehouseProduct List based on the seller.
     * </p>
     *
     * @param sellerId Seller whose warehouse products need to be searched
     * @return warehouseProducts Returns the list of warehouseProducts
     *         corresponding to the seller.
     */
    List<WarehouseProduct> getWarehouseProductsBySeller(Integer sellerId)
            throws EcommerceException;

    /**
     * <p>
     * It fetches the WarehouseProduct ID List based on the seller.
     * </p>
     *
     * @param sellerId Seller whose warehouse products need to be searched
     * @return warehouseProducts Returns the list of warehouseProducts
     *         corresponding to the seller.
     */
    List<Integer> getWarehouseProductIdsBySeller(Integer sellerId)
            throws EcommerceException;
}
