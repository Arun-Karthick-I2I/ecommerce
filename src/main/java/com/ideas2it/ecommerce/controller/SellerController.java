package com.ideas2it.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Address;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.model.WarehouseProduct;
import com.ideas2it.ecommerce.service.SellerService;
import com.ideas2it.ecommerce.service.impl.SellerServiceImpl;

/**
 * <p>
 * The {@code SellerController} provides functionality related to the management
 * of sellers and provides sellers to control their warehouse products such as
 * adding new products to warehouse, adding new warehouse address, maintaining
 * their warehouse product's quantity or price.
 * </p>
 * 
 * @author Arun Karthick.J
 */
@Controller
@RequestMapping("seller")
public class SellerController {
    private static final String INDEX_PAGE = "index";
    private static final String EDIT_SELLER = "EditSeller";
    private static final String SELLER_HOME = "SellerHome";
    private static final String SELLER_LOGIN = "SellerLogin";
    private static final String PRODUCT_FORM = "ProductForm";
    private static final String WAREHOUSE_PRODUCT_FORM = "WarehouseProductForm";

    private SellerService sellerService = new SellerServiceImpl();

    @GetMapping("/")
    public String showInitialPage() {
        return SELLER_LOGIN;
    }

    /**
     * <p>
     * Shows the seller his personal profile and provides option to edit those
     * details.
     * </p>
     */
    @GetMapping("viewProfile")
    public ModelAndView showAccount(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(EDIT_SELLER);
        try {
            Seller seller = sellerService.searchSeller(
                    (Integer) session.getAttribute(Constants.LABEL_SELLER_ID));
            modelAndView.addObject(Constants.LABEL_SELLER, seller);
        } catch (EcommerceException e) {
            modelAndView.setViewName(INDEX_PAGE);
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Updates the seller profile if it doesn't conflict with others. It will
     * show an alert if another seller exists with same mobile number or email
     * id.
     * </p>
     */
    @PostMapping("updateProfile")
    public ModelAndView updateAccount(@ModelAttribute("seller") Seller seller,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        try {
            Seller existingSeller = sellerService.searchSeller(seller.getId());
            seller.setUser(existingSeller.getUser());
            seller.setAddresses(existingSeller.getAddresses());
            seller.setRating(existingSeller.getRating());
            seller.setWarehouseProducts(existingSeller.getWarehouseProducts());
            existingSeller = sellerService.checkSellerExistence(seller);
            if (null != existingSeller) {
                if ((seller.equals(existingSeller))
                        && (sellerService.updateSeller(seller))) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                            Constants.MSG_SELLER_UPDATE_SUCCESS);
                } else if (!(seller.equals(existingSeller))) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                            Constants.MSG_SELLER_ALREADY_EXIST);
                }
            } else if (sellerService.updateSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_SELLER_UPDATE_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Shows the seller new address form that can be used to provide additional
     * warehouse addresses.
     * </p>
     */
    @GetMapping("newAddress")
    public ModelAndView createAddress() {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        modelAndView.addObject("addressForm", Boolean.TRUE);
        modelAndView.addObject(Constants.LABEL_ADDRESS, new Address());
        return modelAndView;
    }

    /**
     * <p>
     * Adds the new warehouse address to the seller.
     * </p>
     */
    @PostMapping("addAddress")
    public ModelAndView addAddress(@ModelAttribute("address") Address address,
            HttpSession session) throws EcommerceException {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        Seller seller = sellerService.searchSeller(
                (Integer) session.getAttribute(Constants.LABEL_SELLER_ID));
        try {
            List<Address> addresses = seller.getAddresses();
            addresses.add(address);
            seller.setAddresses(addresses);
            if (sellerService.updateSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_ADDRESS_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Shows the seller edit address form that can be used to provide updated
     * address details for the warehouse address given.
     * </p>
     */
    @PostMapping("editAddress")
    public ModelAndView editAddress(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        modelAndView.addObject("addressForm", Boolean.TRUE);
        Address address = new Address();
        Integer.parseInt(request.getParameter(Constants.LABEL_ADDRESS));
        modelAndView.addObject(Constants.LABEL_ADDRESS, address);
        return modelAndView;
    }

    /**
     * <p>
     * Updates the warehouse address based on the input from edit warehouse
     * address form.
     * </p>
     */
    @PostMapping("updateAddress")
    public ModelAndView updateAddress(
            @ModelAttribute("address") Address address, HttpSession session)
            throws EcommerceException {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        Seller seller = sellerService.searchSeller(
                (Integer) session.getAttribute(Constants.LABEL_SELLER_ID));
        try {
            List<Address> addresses = seller.getAddresses();
            Address existingaddress = new Address();
            existingaddress.setId(address.getId());
            addresses.remove(existingaddress);
            addresses.add(address);
            seller.setAddresses(addresses);
            if (sellerService.updateSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_UPDATE_ADDRESS_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Removes the corresponding warehouse address from the seller based on the
     * seller input.
     * </p>
     */
    @PostMapping("removeAddress")
    public ModelAndView removeAddress(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        try {
            Seller seller = sellerService.searchSeller(
                    (Integer) session.getAttribute(Constants.LABEL_SELLER_ID));
            Address address = new Address();
            address.setId(Integer.parseInt(
                    request.getParameter(Constants.LABEL_ADDRESS_ID)));
            List<Address> addresses = seller.getAddresses();
            addresses.remove(address);
            seller.setAddresses(addresses);
            if (sellerService.updateSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_DELETE_ADDRESS_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Shows the new Product Form to the seller where they can provide the
     * product name which they want to add to their warehouse.
     * </p>
     */
    @GetMapping("newProduct")
    public String openProductForm() {
        return PRODUCT_FORM;
    }

    /**
     * <p>
     * Creates a product if no such product exists.
     * </p>
     */
    @PostMapping("createProduct")
    public ModelAndView createProduct(
            @ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView(WAREHOUSE_PRODUCT_FORM);
        WarehouseProduct warehouseProduct = new WarehouseProduct();
        try {
            if (sellerService.addProduct(product)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_WAREHOUSE_PRODUCT_SUCCESS);
                warehouseProduct.setProduct(product);
                modelAndView.addObject(Constants.LABEL_WAREHOUSE_PRODUCT,
                        warehouseProduct);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(SELLER_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * Searches for the product name provided by the seller. Returns a list with
     * the specified product name.
     * </p>
     */
    @GetMapping("searchProduct")
    public ModelAndView searchProduct(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(PRODUCT_FORM);
        Product product = new Product();
        String productName = request.getParameter(Constants.LABEL_PRODUCT_NAME);
        try {
            List<Product> products = sellerService.searchProduct(productName);
            if (null != products) {
                modelAndView.addObject("selectProduct", Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
            } else {
                modelAndView.addObject("newProduct", Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_CATEGORIES,
                        sellerService.getCategories());
                product.setName(productName);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        modelAndView.addObject(Constants.LABEL_PRODUCT, product);
        return modelAndView;
    }

    /**
     * <p>
     * Shows the new Warehouse Product Form to the seller where they can provide
     * the product quantity they have and the price at which they want to sell
     * those products.
     * </p>
     */
    @PostMapping("showWarehouseProductForm")
    public ModelAndView showWarehouseProductForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(WAREHOUSE_PRODUCT_FORM);
        Product product = new Product();
        HttpSession session = request.getSession(Boolean.FALSE);
        Integer sellerId = (Integer) session
                .getAttribute(Constants.LABEL_SELLER_ID);
        Integer productId = Integer
                .parseInt(request.getParameter(Constants.LABEL_PRODUCT_ID));
        try {
            WarehouseProduct warehouseProduct = sellerService
                    .getWarehouseProductByProductId(productId, sellerId);
            if (null == warehouseProduct) {
                warehouseProduct = new WarehouseProduct();
                product = sellerService.searchProduct(productId);
                warehouseProduct.setProduct(product);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_WAREHOUSE_PRODUCT_ALREADY_PRESENT);
            }
            modelAndView.addObject(Constants.LABEL_WAREHOUSE_PRODUCT,
                    warehouseProduct);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(SELLER_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * Adds a product to the warehouse along with the quantity available at
     * their warehouse and the price at which the seller wishes to sell.
     * </p>
     */
    @PostMapping("addWarehouseProduct")
    public ModelAndView addWarehouseProduct(
            @ModelAttribute("warehouseProduct") WarehouseProduct warehouseProduct) {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        try {
            if (sellerService.addWarehouseProduct(warehouseProduct)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_WAREHOUSE_PRODUCT_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Deletes a product from the warehouse of the specified seller.
     * </p>
     */
    @PostMapping("deleteWarehouseProduct")
    public ModelAndView deleteWarehouseProduct(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        HttpSession session = request.getSession(Boolean.FALSE);
        try {
            Seller seller = sellerService.searchSeller(
                    (Integer) session.getAttribute(Constants.LABEL_SELLER_ID));
            WarehouseProduct warehouseProduct = new WarehouseProduct();
            warehouseProduct.setId(Integer.parseInt(request
                    .getParameter(Constants.LABEL_WAREHOUSE_PRODUCT_ID)));
            List<WarehouseProduct> warehouseProducts = seller
                    .getWarehouseProducts();
            warehouseProducts.remove(warehouseProduct);
            seller.setWarehouseProducts(warehouseProducts);
            if (sellerService.updateSeller(seller)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_REMOVE_WAREHOUSE_PRODUCT_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Shows the edit Warehouse Product Form to the seller where they can update
     * the product quantity they have and the price at which they want to sell
     * those products.
     * </p>
     */
    @PostMapping("editWarehouseProduct")
    public ModelAndView editWarehouseProduct(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(WAREHOUSE_PRODUCT_FORM);

        try {
            WarehouseProduct warehouseProduct = sellerService
                    .getWarehouseProduct(Integer.parseInt(request.getParameter(
                            Constants.LABEL_WAREHOUSE_PRODUCT_ID)));
            modelAndView.addObject(Constants.LABEL_WAREHOUSE_PRODUCT,
                    warehouseProduct);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Updates a product to the warehouse with the new quantity available at
     * their warehouse and with the new price at which the seller wishes to
     * sell.
     * </p>
     */
    @PostMapping("updateWarehouseProduct")
    public ModelAndView updateWarehouseProduct(
            @ModelAttribute("warehouseProduct") WarehouseProduct warehouseProduct) {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        try {
            if (sellerService.updateWarehouseProduct(warehouseProduct)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_UPDATE_WAREHOUSE_PRODUCT_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

}
