package com.levo.stayactive.view;

import android.view.View;
import android.view.View.OnClickListener;

import com.levo.stayactive.model.Model;

public class SelectController implements OnClickListener{
	
	Model model;
	SelectView view;
	
	public SelectController(SelectView view, Model model) {
		this.view = view;
		this.model = model;
		loadViews();
	}
	
	public void loadViews() {
		view.yesButton.setOnClickListener(this);
		view.noButton.setOnClickListener(this);
		//view.view.findViewById(1).setOnClickListener(this);
		view.website.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v == view.yesButton) {
			//Set this in your bucket list
		} else if (v == view.noButton) {
			model.setActivity();
		} else if (v == view.website) {
			System.out.println("ajdf");
			view.dialog.dismiss();
		}
	}

}
