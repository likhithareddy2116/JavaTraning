package com.springboot.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Entity.User;
import com.springboot.Repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() throws IOException, ClassNotFoundException {
		List<User> users = new ArrayList<>();
		File dataDir = new File(userRepository.getDataDirectory());
		File[] files = dataDir.listFiles();
		if (files != null) {
			for (File file : files) {
				long userId = Long.parseLong(file.getName().split("\\.")[0]);
				User user = userRepository.getUserById(userId);
				users.add(user);
			}
		}
		return users;
	}

	public User getUserById(long id) throws IOException, ClassNotFoundException {
		return userRepository.getUserById(id);
	}

	public String deleteUser(long id) throws IOException, ClassNotFoundException {
		User existingUser = userRepository.getUserById(id);
		if (existingUser == null) {
			throw new IllegalArgumentException("User not found");
		}
		userRepository.delete(id);
		return "Deleted successfully";
	}
}