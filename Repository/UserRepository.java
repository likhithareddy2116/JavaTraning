package com.springboot.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.Entity.User;

@Repository
public class UserRepository {
	/**
	 * This is Folder Location From my local directory
	 */
	private final String dataDir = "E:/springMVC_WS/UserManagementApplication/data/";

	/**
	 * To saved the user data into file
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	public User save(User user) throws IOException {
		File file = new File(dataDir + user.getId() + ".ser");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(user);
		}
		return user;
	}

	/**
	 * This method is used for to get the user details based on Id
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public User findById(Long id) throws IOException, ClassNotFoundException {
		File file = new File(dataDir + id + ".ser");
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			return (User) in.readObject();
		}
	}

	/**
	 * This method is getting All the user details from files
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public List<User> findAll() throws IOException, ClassNotFoundException {
		List<User> users = new ArrayList<>();
		File dir = new File(dataDir);
		for (File file : dir.listFiles()) {
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
				users.add((User) in.readObject());
			}
		}
		return users;
	}

	/**
	 * Update the User Details
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public User updateUser(User user) throws IOException, ClassNotFoundException {
		File file = new File(dataDir + user.getId() + ".ser");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(user);
		}
		return user;
	}

	/**
	 * Delete the User details file
	 */

	public void delete(long id) {
		File file = new File(dataDir + id + ".ser");
		file.delete();

	}

	public String getDataDirectory() {
		return dataDir;
	}

	public User getUserById(long userId) throws IOException, ClassNotFoundException {
		String filename = dataDir + userId + ".ser";
		File file = new File(filename);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				return (User) ois.readObject();
			}
		}
		return null;
	}

}
