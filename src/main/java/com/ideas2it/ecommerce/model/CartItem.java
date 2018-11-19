package com.ideas2it.ecommerce.model;

/**
 * <p>
 * Cart acts like a wish-list that contains all the Products, the Customer 
 * is interested and would purchase later. Cart is a collection of CartItems.
 * CartItem indicates a single Product to be stored in the Cart along with  
 * their respective price and quantity required by the Customer who wishes to 
 * place the Order.
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class CartItem {
	private Integer id;
	private Integer quantity;
	private Float price;
	private Customer customer;
	private WarehouseProduct warehouseProduct;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public WarehouseProduct getWarehouseProduct() {
		return warehouseProduct;
	}

	public void setWarehouseProduct(WarehouseProduct warehouseProduct) {
		this.warehouseProduct = warehouseProduct;
	}
	
    /** 
     * <p>
     * Compares whether two Cart Items are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param address
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar cart item is present
     *         false  When cart items are not similar
     *
     */
    @Override
    public boolean equals(Object cartItem) {
        if (null == cartItem) {
            return Boolean.FALSE;
        }

        if (!(cartItem instanceof CartItem)) {
            return Boolean.FALSE;
        }

        if (this == (CartItem) cartItem) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((CartItem) cartItem).id));
    }

}
