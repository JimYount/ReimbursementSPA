package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.data.UserDAO;

@Service
public class UserService {
	@Autowired
	private UserDAO ud;
	public User login(String username, String password) {
		return ud.getUser(username, password);
	}
}
