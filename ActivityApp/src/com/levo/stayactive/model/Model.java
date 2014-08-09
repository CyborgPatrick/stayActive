package com.levo.stayactive.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

public class Model extends Observable {
	ArrayList<Activities> activities = new ArrayList<Activities>();
	int counter;
	Activities activity;
	
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
		for(int i = 1; i <= 10; i++) {
			s = "activity" + i;
			activities.add(new Activities(s, t));
		}
	}
	
	public String getWebsite() {
		return activity.getWebsite();
	}
	
	public Activities getActivity() {
		return activity;
	}
}
