package com.springboot.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.User;
import com.springboot.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User createUser(@RequestBody User user) throws IOException {
		return userService.createUser(user);

	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) throws IOException, ClassNotFoundException {
		return userService.getUserById(id);
	}

	@GetMapping
	public List<User> getAll() throws IOException, ClassNotFoundException {
		return userService.getAllUsers();
	}

	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) throws IOException, ClassNotFoundException {
		user.setId(id);
		return userService.updateUser(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

}
