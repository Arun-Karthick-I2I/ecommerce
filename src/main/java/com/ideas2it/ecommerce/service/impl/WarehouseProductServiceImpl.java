package com.ideas2it.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ecommerce.dao.WarehouseProductDao;
import com.ideas2it.ecommerce.dao.impl.WarehouseProductDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
import com.ideas2it.ecommerce.model.OrderItem;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.service.WarehouseProductService;

/**
 * <p>
 * The {@code WarehouseProductServiceImpl} provides seller related functionality
 * on warehouse products that are sold by a seller.
 * </p>
 *
 * @author Arun Karthick.J
 */
public class WarehouseProductServiceImpl implements WarehouseProductService {
    private WarehouseProductDao warehouseProductDao = new WarehouseProductDaoImpl();

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean addWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        return warehouseProductDao.addWarehouseProduct(warehouseProduct);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean deleteWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        return warehouseProductDao.removeWarehouseProduct(warehouseProduct);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean updateWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        return warehouseProductDao.updateWarehouseProduct(warehouseProduct);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public WarehouseProduct searchById(Integer warehouseProductId)
            throws EcommerceException {
        return warehouseProductDao.getWarehouseProductById(warehouseProductId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<WarehouseProduct> searchByProductId(Integer productId)
            throws EcommerceException {
        return warehouseProductDao.getWarehouseProductsByProductId(productId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public WarehouseProduct searchByProductId(Integer productId,
            Integer sellerId) throws EcommerceException {
        return warehouseProductDao.getWarehouseProductByProductId(productId,
                sellerId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<WarehouseProduct> searchBySeller(Integer sellerId)
            throws EcommerceException {
        return warehouseProductDao.getWarehouseProductsBySeller(sellerId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Integer> getIdsBySeller(Integer sellerId)
            throws EcommerceException {
        return warehouseProductDao.getWarehouseProductIdsBySeller(sellerId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<WarehouseProduct> getWarehouseProductsByIds(
            List<Integer> warehouseProductIds) throws EcommerceException {
        return warehouseProductDao
                .getWarehouseProductsByIds(warehouseProductIds);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<OrderItem> reduceQuantity(Order order)
            throws EcommerceException {
        WarehouseProduct warehouseProduct = new WarehouseProduct();
        List<OrderItem> outOfStockItems = new ArrayList<OrderItem>();
        List<OrderItem> inStockItems = new ArrayList<OrderItem>();
        for (OrderItem orderItem : order.getOrderItems()) {
            warehouseProduct = orderItem.getWarehouseProduct();
            warehouseProduct.setQuantity(
                    warehouseProduct.getQuantity() - orderItem.getQuantity());
            if (warehouseProduct.getQuantity() >= 0) {
                inStockItems.add(orderItem);
                warehouseProductDao.updateWarehouseProduct(warehouseProduct);
            } else {
                outOfStockItems.add(orderItem);
            }
        }
        if (!outOfStockItems.isEmpty()) {
           order.setOrderItems(inStockItems);
           increaseQuantity(order);
        }
        return outOfStockItems;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean increaseQuantity(Order order)
            throws EcommerceException {
        WarehouseProduct warehouseProduct = new WarehouseProduct();
        for (OrderItem orderItem : order.getOrderItems()) {
            warehouseProduct = orderItem.getWarehouseProduct();
            warehouseProduct.setQuantity(
                    warehouseProduct.getQuantity() + orderItem.getQuantity());
                warehouseProductDao.updateWarehouseProduct(warehouseProduct);
        }
        return Boolean.TRUE;
    }
}
