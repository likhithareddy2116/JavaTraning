package com.sample.rgt.Entity;

import com.sample.rgt.Interface.Observer;

public class UserObserver implements Observer {

	private User user;

	public UserObserver(User user) {
		this.user = user;
	}

	@Override
	public void update(Item item) {
		return ;
	}
	

}
