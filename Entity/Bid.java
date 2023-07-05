package com.sample.rgt.Entity;

public class Bid {
	private Item item;
	private double amount;
	private boolean winningBid;

	public Bid(Item item, double amount, boolean winningBid) {

		this.item = item;
		this.amount = amount;
		this.winningBid = winningBid;
	}

	public Bid(Item item, double bidAmount) {

		this.item = item;
		this.amount = bidAmount;

	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isWinningBid() {
		return winningBid;
	}

	public void setWinningBid(boolean winningBid) {
		this.winningBid = winningBid;
	}

}
