package com.levo.stayactive.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

public class Model extends Observable {
	ArrayList<Activities> activities = new ArrayList<Activities>();
	int counter;
	Activities activity;
	int screenHeight;
	int screenWidth;
	int screenCenter;
	
	public Model() {
		initiateDatabase();
		counter = activities.size();
		setActivity();
	}
	
	public void setActivity() {
		if(counter == activities.size()) {
			counter = 0;
			Collections.shuffle(activities);
		}
		activity = activities.get(counter++);
		setChanged();
		notifyObservers(1);
	}
	
	private void initiateDatabase() {
		String s;
		String t = "https://www.google.com";
		for(int i = 1; i <= 6; i++) {
			String pictureName = "event" + i;
			s = "activity" + i;
			activities.add(new Activities(s, t, 5, "sdö"));
		}
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public int getScreenCenter() {
		return screenCenter;
	}
	
	public String getWebsite() {
		return activity.getWebsite();
	}
	
	public Activities getActivity() {
		return activity;
	}

	public void setMetrics(int screenHeight, int screenWidth) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		screenCenter = screenWidth / 2;
		System.out.println("Screen height is: " + screenHeight + "Screen width is: " + screenWidth);
	}
	public void stuffs() {
		setChanged();
		notifyObservers();
	}
}
