package com.bandlist.api.entity;

public class Member {
	private String full_name;
	private String role;
	private boolean alive;
	
	public static class Builder {
		private String full_name;
		private String role;
		private boolean alive;
		
		public Builder full_name(String full_name) {
			this.full_name = full_name;
			return this;
		}

		public Builder role(String role) {
			this.role = role;
			return this;
		}
		
		public Builder alive(boolean alive) {
			this.alive = alive;
			return this;
		}
		
		public Member build() {
			return new Member(this);
		}
	}
	
	private Member(Builder builder) {
		full_name = builder.full_name;
		role = builder.role;
		alive = builder.alive;
	}
	
	@Override
	public String toString() {
		return "Band{" +
				"'full_name': '" + full_name + "'" +
				"'role': '" + role + "'" +
				"'alive': " + alive + "'" +
				"}";
	}

	// getters needed for jackson bindings, to generate a json representation of this domain object.
	
	public String getFull_name() {
		return full_name;
	}

	public String getRole() {
		return role;
	}

	public boolean isAlive() {
		return alive;
	}
}
