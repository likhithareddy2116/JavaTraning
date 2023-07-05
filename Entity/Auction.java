package com.sample.rgt.Entity;

import java.util.ArrayList;
import java.util.List;

public class Auction {
	private static Auction instance = null;
	private List<Item> items;

	private Auction() {
		this.items = new ArrayList<>();
	}

	public static Auction getInstance() {
		if (instance == null) {
			instance = new Auction();
		}
		return instance;

	}

	public void addItem(Item item) {
		items.add(item);
	}

	public List<Item> getItems() {

		return items;
	}

}
