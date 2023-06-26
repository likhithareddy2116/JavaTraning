package com.rgt.sample.OnlineShopping.Entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int nextConfirmationNumber = 1;
	private int confirmationNumber;
	private HashMap<Product, Integer> items;
	private double totalPrice;

	public Order(HashMap<Product, Integer> items) {
        this.confirmationNumber = nextConfirmationNumber++;
		this.items = new HashMap<>(items);
		this.totalPrice = calculateTotalPrice();
	}

	/**
	 * This method is used for to calculate the total price of the product
	 * 
	 * @return
	 */
	private double calculateTotalPrice() {
		double totalPrice = 0;
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			totalPrice += product.getPrice() * quantity;
		}
		return totalPrice;
	}

	public int getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(int confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public HashMap<Product, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Product, Integer> items) {
		this.items = items;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order{" + "confirmationNumber=" + confirmationNumber + ", items=" + items + ", totalPrice=" + totalPrice
				+ '}';
	}

}
