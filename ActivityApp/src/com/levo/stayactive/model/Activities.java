package com.levo.stayactive.model;

public class Activities {
	String name;
	String website;
	String picture;
	
	public Activities(String name, String website, String picture) {
		this.name = name;
		this.website = website;
		this.picture = picture;
	}
	
	public String getType() {
		return "Activity";
	}
	
	public String getName() {
		return name;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public String getPicture() {
		return picture;
	}
}
