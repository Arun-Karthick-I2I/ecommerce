package com.ideas2it.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ecommerce.common.Constants;
import com.ideas2it.ecommerce.common.enums.ORDER_STATUS;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.logger.EcommerceLogger;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.OrderItem;
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
    private static final String SELLER_ORDERS = "Seller_Orders";
    private static final String SELLER_LOGIN = "SellerLogin";
    private static final String SHOW_WAREHOUSE = "ShowWarehouse";
    private static final String PRODUCT_FORM = "Seller_AddProduct";
    private static final String WAREHOUSE_PRODUCT_FORM = "WarehouseProductForm";

    private SellerService sellerService = new SellerServiceImpl();

    @GetMapping("/")
    public String showInitialPage() {
        return SELLER_LOGIN;
    }

    @GetMapping("home")
    public String showHomePage() {
        return SELLER_HOME;
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
            @ModelAttribute("product") Product product,
            @RequestParam MultipartFile productImage) {
        ModelAndView modelAndView = new ModelAndView(WAREHOUSE_PRODUCT_FORM);
        WarehouseProduct warehouseProduct = new WarehouseProduct();
        try {
            byte[] product_Image = productImage.getBytes();
            product.setImage(product_Image);
            if (sellerService.addProduct(product)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_ADD_PRODUCT_SUCCESS);
                EcommerceLogger.debug("Testing ###@ " + product.getCategory().getName());
                warehouseProduct.setProduct(product);
                modelAndView.addObject(Constants.LABEL_WAREHOUSE_PRODUCT,
                        warehouseProduct);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(SELLER_HOME);
        } catch (IOException e) {
            EcommerceLogger.error("Error while storing image", e);
            modelAndView.setViewName(SELLER_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * Searches for the product name provided by the seller. Returns a list with
     * the specified product name.`
     * </p>
     */
    @GetMapping("searchProduct")
    public ModelAndView searchProduct(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(PRODUCT_FORM);
        Product product = new Product();
        String productName = request.getParameter(Constants.LABEL_PRODUCT_NAME);
        try {
            List<Product> products = sellerService.searchProduct(productName);
            if (!products.isEmpty()) {
                modelAndView = showWarehouseProductForm(request,
                        products.get(0));
            } else {
                modelAndView.addObject("newProduct", Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_CATEGORIES,
                        sellerService.getAllCategories());
                modelAndView.addObject(Constants.LABEL_PRODUCT, product);
                product.setName(productName);
            }
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
    
    @GetMapping("getAllProducts")
    public ModelAndView getAllProducts() {
        ModelAndView modelAndView = new ModelAndView(SELLER_HOME);
        try {
            List<Product> products = sellerService.getAllProducts();
            List<Category> categories = sellerService.getAllCategories(); 
            modelAndView.addObject(Constants.LABEL_PRODUCTS, products);
            modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView; 
    }
    
    /**
     * <p>
     * Shows the new Warehouse Product Form to the seller where they can provide
     * the product quantity they have and the price at which they want to sell
     * those products.
     * </p>
     */
    private ModelAndView showWarehouseProductForm(HttpServletRequest request,
            Product product) {
        ModelAndView modelAndView = new ModelAndView(WAREHOUSE_PRODUCT_FORM);
        HttpSession session = request.getSession(Boolean.FALSE);
        Integer sellerId = (Integer) session
                .getAttribute(Constants.LABEL_SELLER_ID);
        Integer productId = product.getId();
        try {
            WarehouseProduct warehouseProduct = sellerService
                    .getWarehouseProductByProductId(productId, sellerId);
            if (null == warehouseProduct) {
                warehouseProduct = new WarehouseProduct();
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

    @GetMapping("showWarehouse")
    public ModelAndView displayAllWarehouseProducts(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(SHOW_WAREHOUSE);
        Integer sellerId = (Integer) session
                .getAttribute(Constants.LABEL_SELLER_ID);
        try {
            modelAndView.addObject(Constants.LABEL_WAREHOUSE_PRODUCTS,
                    sellerService.getAllWarehouseProducts(sellerId));
        } catch (EcommerceException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping("changeStatus")
    public ModelAndView changeOrderItemStatus(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.REDIRECT + "getAllOrders");
        List<Integer> orderItemIds = new ArrayList<Integer>();
        for (String orderItem : request
                .getParameterValues(Constants.LABEL_ORDER_ITEM_ID)) {
            orderItemIds.add(Integer.parseInt(orderItem));
        }
        try {
            List<OrderItem> orderItems = sellerService
                    .searchOrderItems(orderItemIds);
            for (OrderItem orderItem : orderItems) {
                switch (orderItem.getStatus()) {
                    case ORDERED:
                        orderItem.setStatus(ORDER_STATUS.DISPATCHED);
                        break;
                    case DISPATCHED:
                        orderItem.setStatus(ORDER_STATUS.DELIVERED);
                        break;
                    default:
                        break;
                }
            }
            if (sellerService.changeOrderItemsStatus(orderItems)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MSG_SELLER_ORDER_STATUS_UPDATE_SUCCESS);
            }
        } catch (EcommerceException e) {
            modelAndView.setViewName(SELLER_HOME);
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("getOrders")
    public ModelAndView displayOrdersItems(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(SELLER_ORDERS);
        HttpSession session = request.getSession(Boolean.FALSE);
        try {
            List<Integer> warehouseProductIds = sellerService
                    .getWarehouseProductIds((Integer) session
                            .getAttribute(Constants.LABEL_SELLER_ID));
            ORDER_STATUS status = ORDER_STATUS.valueOf(request.getParameter(Constants.LABEL_STATUS));
            List<OrderItem> orderItems = sellerService
                    .searchOrderItemsByStatus(warehouseProductIds, status);
            if (!orderItems.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_ORDER_ITEMS, orderItems);
            } else {
                modelAndView.setViewName(SELLER_HOME);
                modelAndView.addObject(Constants.LABEL_MESSAGE, Constants.MSG_SELLER_NO_ORDERS_IN_THAT_STATUS);
            }
        } catch (EcommerceException e) {
            modelAndView.setViewName(SELLER_HOME);
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }


    @GetMapping("getAllOrders")
    public ModelAndView displayAllOrdersItems(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(SELLER_ORDERS);
        try {
            List<Integer> warehouseProductIds = sellerService
                    .getWarehouseProductIds((Integer) session
                            .getAttribute(Constants.LABEL_SELLER_ID));
            List<OrderItem> orderItems = sellerService
                    .searchOrderItemsByWarehouseProductIds(warehouseProductIds);
            modelAndView.addObject(Constants.LABEL_ORDER_ITEMS, orderItems);
        } catch (EcommerceException e) {
            modelAndView.setViewName(SELLER_HOME);
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

}
