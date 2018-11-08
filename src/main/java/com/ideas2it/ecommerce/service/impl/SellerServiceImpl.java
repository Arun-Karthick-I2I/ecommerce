package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.SellerDao;
import com.ideas2it.ecommerce.dao.impl.SellerDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.service.CategoryService;
import com.ideas2it.ecommerce.service.ProductService;
import com.ideas2it.ecommerce.service.SellerService;
import com.ideas2it.ecommerce.service.WarehouseProductService;

/**
 * <p>
 * The {@code SellerServiceImpl} provides seller related functionality such as
 * working on seller related data like warehouse products and other details of a
 * seller.
 * </p>
 *
 * @author Arun Karthick.J
 */
public class SellerServiceImpl implements SellerService {

    private SellerDao sellerDao = new SellerDaoImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private WarehouseProductService warehouseProductService = new WarehouseProductServiceImpl();

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean register(Seller seller) throws EcommerceException {
        return sellerDao.addSeller(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller checkSellerExistence(Seller seller)
            throws EcommerceException {
        return sellerDao.checkSellerPresence(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller searchSeller(Integer sellerId) throws EcommerceException {
        return sellerDao.getSeller(sellerId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller searchSellerByUserId(Integer userId)
            throws EcommerceException {
        return sellerDao.getSellerByUserId(userId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean deleteSeller(Seller seller) throws EcommerceException {
        return sellerDao.deleteSeller(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean updateSeller(Seller seller) throws EcommerceException {
        return sellerDao.updateSeller(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean addProduct(Product product) throws EcommerceException {
        return productService.addProduct(product);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean addWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        return warehouseProductService.addWarehouseProduct(warehouseProduct);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean deleteWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        return warehouseProductService.deleteWarehouseProduct(warehouseProduct);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean updateWarehouseProduct(WarehouseProduct warehouseProduct)
            throws EcommerceException {
        return warehouseProductService.updateWarehouseProduct(warehouseProduct);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public WarehouseProduct getWarehouseProduct(Integer warehouseProductId)
            throws EcommerceException {
        return warehouseProductService.searchById(warehouseProductId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public WarehouseProduct getWarehouseProductByProductId(Integer productId,
            Integer sellerId) throws EcommerceException {
        return warehouseProductService.searchByProductId(productId, sellerId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<WarehouseProduct> getAllWarehouseProducts(Seller seller)
            throws EcommerceException {
        return warehouseProductService.searchBySeller(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Product> searchProduct(String productName)
            throws EcommerceException {
        return productService.searchByName(productName);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Product searchProduct(Integer productId) throws EcommerceException {
        return productService.searchById(productId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Category> getCategories() throws EcommerceException {
        return categoryService.getCategories();
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Category searchCategory(Integer categoryId)
            throws EcommerceException {
        return categoryService.searchById(categoryId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Seller> getSellersByName(String sellerName)
            throws EcommerceException {
        return sellerDao.getSellersByName(sellerName);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Seller> getAllSellers() throws EcommerceException {
        return sellerDao.getAllSellers();
    }
}
