package com.sample.rgt.service;

import java.util.ArrayList;
import java.util.List;

import com.sample.rgt.Entity.User;

public class UserManagementService {
	private List<User> users;
	private User currentUser;

	public UserManagementService() {
		this.users = new ArrayList<>();
	}

	public void createUser(String username, String password) {
		User user = new User(username, password);
		users.add(user);
	}

	public User authenticateUser(String username, String password) {
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				currentUser = user;
				return user;
			}
		}
		return null; // User not found or invalid credentials
	}

	public User getCurrentUser() {

		return currentUser;
	}
}
