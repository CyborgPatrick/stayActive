package com.levo.stayactive.view;

import java.util.Observable;
import java.util.Observer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.levo.activityapptest.R;
import com.levo.stayactive.model.Activities;
import com.levo.stayactive.model.Model;


public class SelectView implements Observer{
	Model model;
	View view;
	LinearLayout layout;
	Button yesButton;
	Button noButton;
	Button btn;
	final TextView website;
	AlertDialog dialog;
	int i = 0;

	public SelectView(View view, Model model) {
		model.addObserver(this);
		layout = new LinearLayout(view.getContext());
		layout.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0, 3f));
		layout.setGravity(Gravity.CENTER);
		((LinearLayout) view.findViewById(R.id.select_activity)).addView(layout);
		this.model = model;
		website = new TextView(view.getContext());
		this.view = view;
		newActivity();
		initiateFields();
	}
	
	public void newActivity() {
		layout.removeAllViews();
		
		Activities a = model.getActivity();
		btn = new Button(view.getContext());
		btn.setText(a.getName());
		btn.setTag(a);
		btn.setId(1);
		try {
			layout.addView(btn);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("ad");
				try {
					info();
				} catch (Exception e) {
					System.out.println("Exception: " + e);
				}
			}
			
		});
		website.setText(model.getActivity().getName());
		String link = "<a href='	" + model.getWebsite() + "'> website </a>";
		website.setText(Html.fromHtml(link));
		website.setMovementMethod(LinkMovementMethod.getInstance());
		website.setClickable(true);
	}
	
	public void initiateFields() {
		System.out.println("dhaö");
		LinearLayout buttonLayout = new LinearLayout(view.getContext());
		buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f));
		buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout yesLayout = new LinearLayout(view.getContext());
		yesLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f));
		yesLayout.setGravity(Gravity.CENTER);
		yesButton = new Button(view.getContext());
		yesButton.setText("yes");
		yesLayout.addView(yesButton);
		buttonLayout.addView(yesLayout);
		LinearLayout noLayout = new LinearLayout(view.getContext());
		noLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f));
		noLayout.setGravity(Gravity.CENTER);
		noButton = new Button(view.getContext());
		noButton.setText("no");
		noLayout.addView(noButton);
		buttonLayout.addView(noLayout);
		((LinearLayout) view.findViewById(R.id.select_activity)).addView(buttonLayout);
	}

	public void info() {
		System.out.println(website.getText());
		AlertDialog.Builder info = new AlertDialog.Builder(view.getContext());
		info.setView(null);
		info.setView(website);
		if(i == 0) {

		info.setNegativeButton("ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				
			}
		});
		
		dialog = info.create();
		}
		
		dialog.show();
		i++;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		newActivity();
	}
}