package com.springboot.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.User;
import com.springboot.Service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@GetMapping
	public List<User> getAllUsers() throws IOException, ClassNotFoundException {
		return adminService.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) throws IOException, ClassNotFoundException {
		return adminService.getUserById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) throws IOException, ClassNotFoundException {
		String str = adminService.deleteUser(id);
		return ResponseEntity.ok(str);
	}
}