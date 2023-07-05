package com.sample.rgt.Entity;

import com.sample.rgt.Interface.BiddingStrategy;

public class AutomaticBiddingstratagey implements BiddingStrategy {

	@Override
	public double bid(Item item, User user, double bidAmount) {
		double currentBid = item.getCurrentHighestBid();
		double newBid = currentBid + bidAmount;
		item.placeBid(user, newBid);
		System.out.println("Automatic bid placed by " + user.getUserName() + " on item: " + item.getName());
		System.out.println("New bid amount: " + newBid);
		return newBid;
	}
}
