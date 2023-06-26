package com.rgt.sample.OnlineShopping.Entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ProductCatalog {

	private HashMap<String, Product> products;

	public ProductCatalog() {
		products = new HashMap<>();
	}

	public HashMap<String, Product> getProducts() {
		return products;
	}

	public void setProducts(HashMap<String, Product> products) {
		this.products = products;
	}

	public void loadProducts(String fileName) {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
			products = (HashMap<String, Product>) inputStream.readObject();
			System.out.println("Product file loaded successfully.");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Failed to load product file: " + e.getMessage());
		}
	}

	public void saveProducts(String fileName) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
			outputStream.writeObject(products);
			System.out.println("Product file saved successfully.");
		} catch (IOException e) {
			System.out.println("Failed to save product file: " + e.getMessage());
		}
	}
    /**
     * this method is used for getProduct with name
     * @param name
     * @return
     */
	public Product getProduct(String name) {
		return products.get(name);

	}
   /**
    * Getting all products
    * @return
    */
	public HashMap<String, Product> getAllProducts() {
		return products;

	}
    /**
     * This method is used for added the product
     * @param product
     */
	public void addProduct(Product product) {
		products.put(product.getName(), product);
	}

}
