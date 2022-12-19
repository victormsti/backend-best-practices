package com.example.bestpractices.configuration.context;

import com.example.bestpractices.model.user.User;

public class UserContext {
	
	private static UserContext instance = new UserContext();
	
	ThreadLocal<User> globalUser = new ThreadLocal<User>();


	private UserContext() {
	}

	public static UserContext getInstance() {
		return instance;
	}
	
	public User getUser() {
		return globalUser.get();
	}

	public void setUser(User user) {
		globalUser.set(user);
	}

}
