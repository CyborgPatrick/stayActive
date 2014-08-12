package com.levo.stayactive.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.levo.stayactive.model.Model;

@SuppressLint("NewApi")
public class SelectViewte {
	
	View view;
	final Model model;
	Button yesButton;
	Button noButton;
	AlertDialog dialog;
	float alphaValue = 0;
	//final TextView website;
	//final RelativeLayout parentLayout;
	int xCord;
	int yCord;
	int likes;

	public SelectViewte(View findViewById, final Model model) {
		getMetrics();
		//parentLayout = new RelativeLayout(view.getContext());
		//parentLayout.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));
		//website = new TextView(view.getContext());
		this.view = view;
		this.model = model;
	}
	
	public void getMetrics() {
		int screenHeight;
		System.out.println("hej");
		int screenWidth;
		System.out.println("hej");
		Context da = view.getContext();
		//DisplayMetrics metrics = view.getContext().getResources().getDisplayMetrics();
		//screenHeight = metrics.heightPixels;
		System.out.println("hej");
		//screenWidth = metrics.widthPixels;
		System.out.println("hej");
		//model.setMetrics(screenHeight, screenWidth);
	}
	

}
