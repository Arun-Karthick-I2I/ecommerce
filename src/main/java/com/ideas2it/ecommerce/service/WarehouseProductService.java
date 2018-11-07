package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;

public interface WarehouseProductService {

    Boolean addWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    Boolean deleteWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    Boolean updateWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException;

    WarehouseProduct searchById(Integer warehouseId) throws EcommerceException;

    List<WarehouseProduct> searchByProductId(Integer productId)
            throws EcommerceException;

    List<WarehouseProduct> searchBySeller(Seller seller)
            throws EcommerceException;
    
    List<WarehouseProduct> getWarehouseProductsByIds(List<Integer> warehouseProductIds)
            throws EcommerceException;

}
