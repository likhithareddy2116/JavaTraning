package com.rgt.sample.OnlineShopping.Entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap<Product, Integer> items;

	public ShoppingCart() {
		this.items = new HashMap<>();
	}

	public HashMap<Product, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Product, Integer> items) {
		this.items = items;
	}
    
	/**
	 * Add Item into the cart based on product and quantity
	 * @param product
	 * @param quantity
	 */
	public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }
	/**
	 * Remove Item from cart
	 * @param product
	 */
	public void removeItem(Product product) {
		items.remove(product);

	}
     /**
      * Getting total price of the product
      * @return
      */
	public double getTotalPrice() {
		double totalPrice = 0;
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			totalPrice += product.getPrice() * quantity;
		}
		return totalPrice;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			sb.append(product.toString()).append("Quantity in cart: ").append(quantity).append("\n");
		}
		return sb.toString();
	}

}
