package com.rgt.sample.OnlineShopping.Entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Order> orders;

	public OrderHistory() {
		this.orders = new ArrayList<>();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Load the order history into file
	 * 
	 * @param fileName
	 */
	public void loadOrderHistory(String orderFileName) {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(orderFileName))) {
			orders = (List<Order>) inputStream.readObject();
			System.out.println("Order history loaded successfully.");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Failed to load order history: " + e.getMessage());
		}
	}

	/**
	 * save order history file
	 * 
	 * @param fileName
	 */
	public void saveOrderHistory(String productFileName) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(productFileName))) {
			outputStream.writeObject(orders);
			System.out.println("Order history saved successfully.");
		} catch (IOException e) {
			System.out.println("Failed to save order history: " + e.getMessage());
		}
	}

	public void addOrder(Order order) {
		orders.add(order);

	}

	public List<Order> getAllOrders() {
		return orders;
	}

}
