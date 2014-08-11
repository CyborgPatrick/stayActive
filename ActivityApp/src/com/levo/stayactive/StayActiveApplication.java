package com.levo.stayactive;

import android.app.Application;

import com.levo.stayactive.model.Model;


public class StayActiveApplication extends Application {
	Model model = new Model();
	
	public Model getModel() {
		return model;
	}
}
