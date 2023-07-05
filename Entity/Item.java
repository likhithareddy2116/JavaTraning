package com.sample.rgt.Entity;

public class Item {
	private String name;
	private String description;
	private double currentHighestBid;
	private User highestBidder;

	public Item(String name, String description, double startingBid) {
		this.name = name;
		this.description = description;
		this.currentHighestBid = startingBid;
		highestBidder = null;
	}

	public void placeBid(User user, double bidAmount) {
		if (bidAmount > currentHighestBid) {
			currentHighestBid = bidAmount;
			highestBidder = user;
			user.addBid(new Bid(this, bidAmount));

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(User highestBidder) {
		this.highestBidder = highestBidder;
	}

	public double getCurrentHighestBid() {
		return currentHighestBid;
	}
}
