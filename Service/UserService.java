package com.springboot.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Entity.User;
import com.springboot.Repository.UserRepository;

@Service
public class UserService {
	//private static long nextId = 1;

	@Autowired
	private UserRepository repository;

	public User createUser(User user) throws IOException {
		 user.setId(System.currentTimeMillis());
		//user.setId(nextId++);
		return repository.save(user);
	}

	public User getUserById(long id) throws IOException, ClassNotFoundException {
		return repository.findById(id);
	}

	public List<User> getAllUsers() throws IOException, ClassNotFoundException {
		return repository.findAll();
	}

	public User updateUser(User user) throws IOException, ClassNotFoundException {
		return repository.updateUser(user);
	}

	public void deleteUser(long id) {
		repository.delete(id);
	}

}
