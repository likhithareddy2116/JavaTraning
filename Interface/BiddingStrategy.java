package com.sample.rgt.Interface;

import com.sample.rgt.Entity.Item;
import com.sample.rgt.Entity.User;

public interface BiddingStrategy {

	public double bid(Item item, User user,double bidAmount);

}
