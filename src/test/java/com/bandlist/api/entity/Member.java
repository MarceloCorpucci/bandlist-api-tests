package com.bandlist.api.entity;

public class Member {
	private String full_name;
	private String role;
	private boolean alive;
	
	public Member(String fullName, String role, boolean isAlive) {
		this.full_name = fullName;
		this.role = role;
		this.alive = isAlive;
	}

	public String getfull_name() {
		return full_name;
	}

	public String getRole() {
		return role;
	}

	public boolean isAlive() {
		return alive;
	}
	
}
