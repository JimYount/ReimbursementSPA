package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;

public class UserService {
	private static List<User> userDAO;
	static {
		userDAO = new ArrayList<User>();
		userDAO.add(new User("Richard", "Orr", "Purple", "rorr", "pass"));
		userDAO.add(new User("Andrew", "Lohse", "Teal", "luis", "soncrant"));
		userDAO.add(new User("Kerwin", "Yount", "Yellow", "adam", "Harvey"));
	}
	public User login(String username, String password) {
		for(User u: userDAO) {
			if(u.getUsername().equals(username) && u.getPassword().contentEquals(password))
				return u;
		}
		return null;
	}
	public void addUser(User u) {
		userDAO.add(u);
	}
}
