package com.sample.rgt.Entity;

import com.sample.rgt.Interface.BiddingStrategy;

public class IncrementBiddingStrategy implements BiddingStrategy {

	@Override
	public double bid(Item item, User user, double bidAmount) {
		double currentBid = item.getCurrentHighestBid();
		double newBid = currentBid + 1.0;
		item.placeBid(user, newBid);
		return newBid;
	}

}
