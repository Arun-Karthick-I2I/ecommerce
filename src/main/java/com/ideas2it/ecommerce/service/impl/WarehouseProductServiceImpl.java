package com.ideas2it.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ecommerce.dao.WarehouseProductDao;
import com.ideas2it.ecommerce.dao.impl.WarehouseProductDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Order;
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
    public List<WarehouseProduct> searchBySeller(Seller seller)
            throws EcommerceException {
        return warehouseProductDao.getWarehouseProductsBySeller(seller);
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
    public List<Order> reduceQuantity(List<Order> orders)
            throws EcommerceException {
        WarehouseProduct warehouseProduct = new WarehouseProduct();
        List<Order> outOfStockOrders = new ArrayList<Order>();
        for (Order order : orders) {
            warehouseProduct = order.getWarehouseProduct();
            warehouseProduct.setQuantity(
                    warehouseProduct.getQuantity() - order.getQuantity());
            if (warehouseProduct.getQuantity() >= 0) {
                warehouseProductDao.updateWarehouseProduct(warehouseProduct);
            } else {
                outOfStockOrders.add(order);
            }
        }
        return outOfStockOrders;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean increaseQuantity(List<Order> orders)
            throws EcommerceException {
        WarehouseProduct warehouseProduct = new WarehouseProduct();
        for (Order order : orders) {
            warehouseProduct = order.getWarehouseProduct();
            warehouseProduct.setQuantity(
                    warehouseProduct.getQuantity() + order.getQuantity());
                warehouseProductDao.updateWarehouseProduct(warehouseProduct);
        }
        return Boolean.TRUE;
    }
}
