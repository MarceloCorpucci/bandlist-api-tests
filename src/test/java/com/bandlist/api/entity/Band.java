package com.bandlist.api.entity;

import java.util.List;

public class Band {
	private String name;
	private String genre;
	private List<Member> members;
	
	public static class Builder {
		private String name;
		private String genre;
		private List<Member> members;

		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder genre(String genre) {
			this.genre = genre;
			return this;
		}

		public Builder members(List<Member> members) {
			this.members = members;
			return this;
		}
		
		public Band build() {
			return new Band(this);
		}
	}
	
	private Band(Builder builder) {
		name = builder.name;
		genre = builder.genre;
		members = builder.members;
	}

	@Override
	public String toString() {
		return "Band{" +
				"'name': '" + name + "'" +
				"'genre': '" + genre + "'" +
				"'members': " + members + "'" +
				"}";
	}

	//getters needed for jackson bindings, to generate a json representation of this domain object.
	
	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public List<Member> getMembers() {
		return members;
	}
}
