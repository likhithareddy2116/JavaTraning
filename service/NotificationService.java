package com.sample.rgt.service;

import com.sample.rgt.Entity.Item;
import com.sample.rgt.Entity.User;

public class NotificationService {
	public void notifyUser(User user, Item item) {
		double currentBid = item.getCurrentHighestBid();
		User highestBidder = item.getHighestBidder();
		if (highestBidder != null && highestBidder != user) {
			System.out.println("You have been outbid on item: " + item.getName());
			System.out.println("Current highest bid: " + currentBid);
			System.out.println("Highest bidder: " + highestBidder.getUserName());
		}

	}

}
