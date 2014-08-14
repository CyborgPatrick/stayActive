package com.levo.stayactive.model;

import com.levo.activityapptest.R;

public class Activities {
	String name;
	String website;
	String description;
	int distance;
	int i;
	String[] myImageList;
	
	public Activities(String name, String website, int distance, String description) {
		this.name = name;
		this.website = website;
		this.description = description;
		this.distance = distance;
		
	    myImageList = new String[] { name + "1", name + "2" , name + "3"};
	    
		i = myImageList.length;
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

	public String getDescription() {
		return description;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public String getFirstPicture() {
		i = 0;
		return myImageList[i];
	}
	
	public String getNextPicture() {
		i++;
		if(i >= myImageList.length) {
			i = 0;
		}
		return myImageList[i];
	}
	
	public String getPreviousPicture() {
		i--;
		if(i < 0) {
			i = myImageList.length - 1;
		}
		return myImageList[i];
	}
	
	public String getCurrentPicture() {
		return myImageList[i];
	}
}
