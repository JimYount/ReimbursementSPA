package com.revature.service;

import org.springframework.stereotype.Service;

import com.revature.beans.User;

@Service
public class UserService {
	public User login(String username, String password) {
		if("bob".equals(username) && "pass".equals(password)) {
			User u = new User();
			u.setName("BobSteve");
			u.setUsername("bob");
			u.setPassword("pass");
			return u;
		}
		return null;
	}
}
