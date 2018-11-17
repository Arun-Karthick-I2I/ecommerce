package com.ideas2it.ecommerce.common;

public class Constants {
    
    // General Constants
    
    public static final Character COLON_SYMBOL = ':';
    
    public static final Character PERCENT_SYMBOL = '%';
    
    public static final Character SPACE = ' ';
    
    public static final Integer SESSION_MAX_INACTIVE_INTERVAL = 200000;
    
    public static final String REDIRECT = "redirect:";
    
    public static final String LABEL_ADDRESS = "address";
    
    public static final String LABEL_ADDRESSES = "addresses";
    
    public static final String LABEL_ADDRESS_ID = "addressId";

    public static final String LABEL_CATEGORIES = "categories";
    
    public static final String LABEL_EMAIL_ID = "emailId";

    public static final String LABEL_MOBILE_NUMBER = "mobileNumber";
    
    public static final String LABEL_ISACTIVE = "isActive";
    
    public static final String LABEL_ID = "id";

    public static final String LABEL_MESSAGE = "message";
    
    public static final String LABEL_PASSWORD = "password";
    
    public static final String LABEL_PRODUCT = "product";
    
    public static final String LABEL_PRODUCT_ID = "productId";

    public static final String LABEL_PRODUCTS = "products";
    
    public static final String LABEL_PRODUCT_NAME = "productName";
    
    public static final String LABEL_REGISTER = "register";
    
    public static final String LABEL_ROLE = "role";

    public static final String LABEL_SELLER = "seller";
    
    public static final String LABEL_SELLER_ID = "sellerId";
    
    public static final String LABEL_USERNAME = "userName";

    public static final String LABEL_NAME = "name";

    public static final String LABEL_ORDER_ITEM_ID = 
        "orderItemId";
    
    public static final String LABEL_ORDER_ITEMS = 
        "orderItems";
        
    public static final String LABEL_STATUS = "status";
            
    public static final String LABEL_MODE_OF_PAYMENT = "modeOfPayment";
    
    public static final String LABEL_USER = "user";

    public static final String LABEL_CUSTOMER = "customer";
    
    public static final String LABEL_USER_ID = "userId";
    
    public static final String LABEL_WAREHOUSE_PRODUCT = 
        "warehouseProduct";
    
    public static final String LABEL_WAREHOUSE_PRODUCTS = 
        "warehouseProducts";
        
    public static final String LABEL_WAREHOUSE_PRODUCT_ID = 
        "warehouseProductId";
    
    public static final String LABEL_WAREHOUSE_PRODUCT_NAME = 
        "warehouseProductName";
    
    public static final String LABEL_ORDERS = "orders";
    
    public static final String LABEL_ORDER = "order";
    
    public static final String LABEL_CUSTOMERS = "customers";
    
    public static final String LABEL_SELLERS = "sellers";
    
    public static final String MSG_INVALID_CREDENTIALS = "Invalid Credentials";
    
    public static final String LABEL_CATEGORY = "category";
    
    public static final String MSG_REGISTER_SUCCESS = 
        "You have registered successfully with username:";

    public static final String MSG_SESSION_FACTORY_FAIL =
        "Something went wrong with our system.. Please Try Again Later";
    
    public static final String MSG_EXCEPTION_REGISTER = 
        "Something went wrong while registering : "; 
    
    public static final String MSG_EXCEPTION_SEARCH_USER = 
        "Something went wrong while fetching User..Try Again Later";
    
    public static final String MSG_LOG_NO_SUCH_ALGORITHM =
        "Problem Hashing Password : No Such Algorithm Found";
    
    public static final String MSG_LOGGED_OUT = 
        "You have been logged out successfully";

    public static final String MSG_ADD_ADDRESS_SUCCESS = 
        "New Address added successfully..";

    public static final String MSG_ADD_ADDRESS_FAIL = 
        "Something went wrong while inserting new address...";
    
    public static final String MSG_UPDATE_ADDRESS_SUCCESS = 
        "Address updated Successfully..";

    public static final String MSG_UPDATE_ADDRESS_FAIL =  
        "Something went wrong while updating address...";
    
    public static final String MSG_DELETE_ADDRESS_SUCCESS = 
        "Address updated Successfully..";
    
    public static final String MSG_DELETE_ADDRESS_FAIL =  
        "Something went wrong while updating address...";

    public static final String MSG_NO_SUCH_USER_EXISTS = 
        "No such user exists...Please Register";
    
    public static final String MSG_SELECT_ATLEAST_ONE_PRODUCT = 
        "Select atleast one product to purchase..";

    //Admin Specific Constants
    public static final String MSG_CATEGORY_ID = "\tCategory ID";
    
    public static final String MSG_CATEGORY_NAME = "\tCategory Name:";  
    
    public static final String MSG_PRODUCT_ID = "\tProduct ID";
    
    public static final String MSG_PRODUCT_NAME = "\tProduct Name:"; 
    
    public static final String MSG_CART_PRODUCT_ID = "\tCart Product ID";
    
    public static final String MSG_ORDER_ID = "\tOrder ID";
    
    public static final String MSG_CUSTOMER_ID = "\tCustomer ID";
    
            
    public static final String MSG_CATEGORY_SEARCH_FAILURE =
        "Unsuccessful search of Category";

    public static final String MSG_CATEGORIES_UNAVAILABLE = 
        "No Categories are available";
    
    public static final String MSG_CATEGORIES_DISPLAY_FAILURE =
        "Unsuccessful display of Categories";
    
    public static final String MSG_INSERT_CATEGORY_SUCCESS = 
        "Category inserted successfully";
    
    public static final String MSG_CATEGORY_EXISTS =
        "Category already exits";
    
    public static final String MSG_CATEGORY_INSERT_FAILURE =
        "Unsuccessful insertion of Category";
    
    public static final String MSG_CATEGORY_DELETE_SUCCESS =
        "Category deleted successfully";    
    
    public static final String MSG_CATEGORY_DELETE_FAILURE =
        "Unsuccessful deletion of Category";
    
    public static final String MSG_CATEGORY_UPDATE_SUCCESS = 
        "Category updated successfully";
    
    public static final String MSG_CATEGORY_UPDATE_FAILURE = 
        "Unsuccessful updation of Category";
    
    public static final String MSG_CATEGORY_NOT_AVAILABLE =
        "Category not available";
    
    public static final String MSG_CATEGORY_PRODUCTS_UNAVAILABLE =
        "Products not available for this Category";
    
    
    public static final String MSG_CART_PRODUCT_INSERT_FAILURE =
        "Unsuccessful insertion of Cart Product";
    
    public static final String MSG_CART_PRODUCT_DELETE_FAILURE =
        "Unsuccessful deletion of Cart Product";
    
    
    public static final String MSG_PRODUCTS_DISPLAY_FAILURE = 
        "Unsuccessful display of Products";
    
    public static final String MSG_PRODUCT_SEARCH_FAILURE = 
        "Unsuccessful search of Product";
    
    public static final String MSG_PRODUCTS_UNAVAILABLE =
        "Sorry, No Products are available";
    
    public static final String MSG_PRODUCT_NOT_AVAILABLE = 
        "Product not available";
    
    public static final String MSG_PRODUCT_INSERT_FAILURE =
        "Unsuccessful insertion of Category";
    
    
    public static final String MSG_ORDERS_DISPLAY_FAILURE = 
        "Unsuccessful display of Orders";
        
    public static final String MSG_ORDER_SEARCH_FAILURE = 
        "Unsuccessful search of Order";
    
    public static final String MSG_ORDERS_UNAVAILABLE = 
        "No Orders has been placed yet...";
    
    public static final String MSG_ORDER_NOT_AVAILABLE =
        "Order not available";
    
    public static final String MSG_ORDER_INSERT_FAILURE =
        "Unsuccessful insertion of Order";
    
    public static final String MSG_ORDER_DELETE_FAILURE =
        "Unsuccessful deletion of Order";
    
    
    public static final String MSG_CUSTOMERS_NOT_AVAILABLE =
        "No Customers are available";
    
    public static final String MSG_CUSTOMER_NOT_AVAILABLE =
        "Customer not available";
    
    public static final String MSG_CUSTOMER_DELETE_SUCCESS =
        "Customer deleted successfully";    
        
    public static final String MSG_CUSTOMER_DELETE_FAILURE =
        "Unsuccessful deletion of Customer";
    
    
    public static final String MSG_SELLER_NOT_AVAILABLE =
        "Seller not available";
    
    public static final String MSG_SELLERS_NOT_AVAILABLE =
        "No Sellers are available";
    
    public static final String MSG_SELLER_DELETE_FAILURE =
        "Unsuccessful deletion of Seller";
    
    
    //Customer Specific Constants

    public static final String ERROR_INSERT_CUSTOMER = 
        "Something went wrong while insertng customer :";
    
    public static final String ERROR_SEARCH_CUSTOMER = 
        "Something went wrong while fetching customer.. ";

    public static final String ERROR_DELETE_CUSTOMER = 
        "Something went wrong while deleting customer : ";

    public static final String MSG_SERVER_ERROR = 
        "Problem with the system..try after sometime";

    public static final String ERROR_UPDATE_CUSTOMER = 
        "Something went wrong while updating customer : ";

    public static final String ERROR_GET_CUSTOMERS = 
        "Something went wrong while displaying customers...";

    public static final String MESSAGE_EXCEPTION_SEARCH_CUSTOMER = 
        "Problem with Database while searching Customer : Try Again Later ";

    public static final String MSG_ADD_CUSTOMER_SUCCESS = 
        " account created successfully.. ";
    
    public static final String MSG_ADD_CUSTOMER_FAIL = 
        " account failed to create..";
    
    public static final String ERROR_INSERT_ORDER = 
        "Something went wrong while placing an order...";

    public static final String MSG_SERACH_CUSTOMER_BY_ID_FAIL = 
        "No account match with this ID..";;

    public static final String MSG_SERACH_CUSTOMER_BY_MOBILE_FAIL = 
        "No account match with this mobile number..";
        
    public static final String MSG_NO_ORDER_PLACED = 
        "No orders has been placed..";
        
    public static final String MSG_ADD_ORDER_SUCCESS = 
        "Your order has been successfully placed...";
    
    public static final String MSG_ADD_ORDER_FAIL = 
        "Fail to place an order...";

    public static final String MSG_CANCEL_ORDER_SUCCESS = 
        "Order has been successfully cancelled..";

    public static final String MSG_CANCEL_ORDER_FAIL = 
        "Failed to cancel order..";
    public static final String MSG_ADD_CART_SUCCESS =
        "Added to Cart Success..";
    public static final String MSG_ADD_CART_FAIL = 
        "Failed to add cart... try after sometime..";
        
    public static final String MSG_CART_EMPTY = 
        "Your Shopping Cart is empty";

    public static final String MSG_PRODUCT_LIST_EMPTY =
        "Sorry, no results found!!!";
    
    public static final String MSG_PRODUCT_PAGE_OPEN_FAIL = 
         "Something went wrong while opening product page..";
    
    public static final String MSG_ADD_SOME_ORDER_FAIL = 
        "Order has been plced exclude some follwing orders:";
    
    public static final String MSG_SEARCH_ORDER_ITEMS_FAIL = 
        "Something went wrong while fetching Order Item details..Try Again Later";

    public static final String MSG_UPDATE_ORDER_ITEMS_FAIL = 
        "Something went wrong while Updating Order Item details..Try Again Later";
            
    public static final String MSG_DONT_HAVE_ENOUGH_QUANTITY = 
        "Some products dont have enough quantity...";
    
    //Seller Specific Constants
    
    public static final String MSG_SELLER_ALREADY_EXIST = 
        "Another Seller exists with the same mobile number or email ID";
    
    public static final String MSG_SELLER_DELETE_SUCCESS = 
        "Seller details have been removed successfully from the store";
        
    public static final String MSG_SELLER_DELETE_FAIL = 
        "Something went wrong while deleting Seller details..Try Again later";

    public static final String MSG_SELLER_UPDATE_SUCCESS = 
        "Your details have been updated successfully";
    
    public static final String MSG_SELLER_UPDATE_FAIL = 
        "Something went wrong while updating Seller details..Try Again later";

    public static final String MSG_SELLER_SEARCH_FAIL = 
        "Something went wrong while finding Seller details..Try Again later";
    
    public static final String MSG_SELLER_NO_ORDERS_IN_THAT_STATUS = 
        "There are no Orders with the specified Status";

    public static final String MSG_SELLER_ORDER_STATUS_UPDATE_SUCCESS = 
        "Status has been updated successfully for the specified Orders";
            
    public static final String MSG_EXCEPTION_GET_SELLERS =
        "Something went wrong while retrieving sellers...Try Again Later";
    
    public static final String MSG_SELLER_REGISTER_SUCCESS =
        "You have registered successfully as a seller";
    
    public static final String MSG_ADD_PRODUCT_SUCCESS =
        "Product added to the Store Successfully";
        
    public static final String MSG_ADD_WAREHOUSE_PRODUCT_SUCCESS =
        "Product added to your Warehouse Successfully";
        
    public static final String MSG_ADD_WAREHOUSE_PRODUCT_FAIL =
        "Something went wrong while adding product...Try Again Later";
    
    public static final String MSG_GET_WAREHOUSE_PRODUCTS_FAIL =
        "Something went wrong while fetching products...Try Again Later";

    public static final String MSG_REMOVE_WAREHOUSE_PRODUCT_SUCCESS =
        "Product removed from warehouse Successfully";

    public static final String MSG_REMOVE_WAREHOUSE_PRODUCT_FAIL =
        "Something went wrong while removing product...Try Again Later";

    public static final String MSG_SEARCH_WAREHOUSE_PRODUCT_FAIL =
        "Something went wrong while searching product...Try Again Later";

    public static final String MSG_UPDATE_WAREHOUSE_PRODUCT_SUCCESS =
        "Product details updated in Warehouse Successfully";

    public static final String MSG_UPDATE_WAREHOUSE_PRODUCT_FAIL =
        "Something went wrong while updating product...Try Again Later";

    public static final String MSG_WAREHOUSE_PRODUCT_ALREADY_PRESENT = 
        "The given product is already present in your Warehouse";
}
