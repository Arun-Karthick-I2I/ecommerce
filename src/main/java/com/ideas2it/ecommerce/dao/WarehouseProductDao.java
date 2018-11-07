package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;

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
     * Searches for the list of WarehouseProducts with the specific name
     * </p>
     * 
     * @param name name of the warehouseProduct whose details are being searched
     *             for.
     * @return warehouseProducts Returns the list of WarehouseProducts with the
     *         desired criteria if they exist Returns null if no such
     *         WarehouseProducts exist.
     */
    List<WarehouseProduct> getWarehouseProductByName(String name)
            throws EcommerceException;

    /**
     * <p>
     * It fetches the WarehouseProduct List based on the list of ids
     * </p>
     *
     * @param  warehouseProductIds List of WarehouseProducts to be fetched.
     * @return warehouseProducts  Returns the list of warehouseProducts
     *         corresponding to the ids provided.
     */
    List<WarehouseProduct> getWarehouseProductsById(
            List<Integer> warehouseProductIds) throws EcommerceException;

    /**
     * <p>
     * It fetches the WarehouseProduct List based on the list of ids and 
     * </p>
     *
     * @param  seller List of WarehouseProducts to be fetched.
     * @return warehouseProducts  Returns the list of warehouseProducts
     *         corresponding to the ids provided.
     */
    List<WarehouseProduct> getWarehouseProductsBySeller(Seller seller) 
            throws EcommerceException;

    /**
     * <p>
     * Returns the list of WarehouseProducts available in the store i.e Database
     * i.e Copies the the WarehouseProducts from database to a list and then
     * returns it.
     * </p>
     *
     * @return warehouseProducts Returns the entire WarehouseProduct collection
     *         from the database as a list.
     */
    List<WarehouseProduct> getWarehouseProducts() throws EcommerceException;

}
