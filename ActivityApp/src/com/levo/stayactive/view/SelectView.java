package com.levo.stayactive.view;

import java.util.Observable;
import java.util.Observer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.levo.activityapptest.R;
import com.levo.stayactive.model.Activities;
import com.levo.stayactive.model.Model;


public class SelectView implements Observer{
	Model model;
	int xCord;
	int yCord;
	View view;
	int likes;
	final RelativeLayout layout;
	RelativeLayout parentLayout;
	Button yesButton;
	Button noButton;
	Button btn;
	final TextView website;
	AlertDialog dialog;
	float alphaValue = 0;
	int i = 0;

	public SelectView(View view, Model model) {
		model.addObserver(this);
		layout = new RelativeLayout(view.getContext());
		parentLayout = new RelativeLayout(view.getContext());
		parentLayout.setLayoutParams(new LinearLayout.LayoutParams((android.view.ViewGroup.LayoutParams.MATCH_PARENT), 0, 3f));
		parentLayout.setGravity(Gravity.CENTER);
		((LinearLayout) view.findViewById(R.id.select_activity)).addView(parentLayout);
		parentLayout.addView(layout);
		this.model = model;
		website = new TextView(view.getContext());
		this.view = view;
		newActivity();
		initiateFields();
		getMetrics();
	}
	

	public void getMetrics() {
		int screenHeight;
		int screenWidth;
		WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		screenHeight = metrics.heightPixels;
		screenWidth = metrics.widthPixels;
		model.setMetrics(screenHeight, screenWidth);
	}
	

	
	@SuppressLint("NewApi")
	public void newActivity() {
		final RelativeLayout layout = new RelativeLayout(view.getContext());
		layout.setLayoutParams(new LayoutParams((model.getScreenWidth() - 80), 300));
		layout.setX(40);
		layout.setY(40);
		layout.setTag(model.getActivity());
		
		final Button imageLike = new Button(view.getContext());
		imageLike.setLayoutParams(new LayoutParams(100, 50));
		imageLike.setBackground(view.getResources().getDrawable(R.drawable.yes));
		imageLike.setX(20);
		imageLike.setY(80);
		imageLike.setAlpha(alphaValue);
		layout.setBackgroundResource(R.drawable.event1);
		layout.addView(imageLike);
		
		final Button imagePass = new Button(view.getContext());
		imagePass.setLayoutParams(new LayoutParams(100, 50));
		imagePass.setBackground(view.getResources().getDrawable(R.drawable.no));
		imagePass.setX(model.getScreenWidth() - 200);
		imagePass.setY(100);
		imagePass.setRotation(45);
		imagePass.setAlpha(alphaValue);
		layout.addView(imagePass);
		
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				xCord = (int) event.getRawX();
				yCord = (int) event.getRawY();
				
				layout.setX(xCord - model.getScreenCenter() + 40);
				layout.setY(yCord - 150);
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					model.stuffs();
					break;
				case MotionEvent.ACTION_MOVE:
					model.stuffs();
					xCord = (int) event.getRawX();
					yCord = (int) event.getRawY();
					layout.setX(xCord - model.getScreenCenter() + 40);
					layout.setY(yCord - 150);
					if(xCord >= model.getScreenCenter()) {
						layout.setRotation((float) ((xCord - model.getScreenCenter() * (Math.PI / 32))));
						if(xCord > (model.getScreenCenter() + (model.getScreenCenter() / 2))) {
							imageLike.setAlpha(1);
							if(xCord > (model.getScreenWidth() - (model.getScreenCenter() / 4))) {
								likes = 2;
							} else {
								likes = 0;
							}
						} else {
							likes = 0;
							imageLike.setAlpha(0);
						}
						imagePass.setAlpha(0);
					} else {
						layout.setRotation((float) ((xCord - model.getScreenCenter()) * (Math.PI / 32)));
						if(xCord < (model.getScreenCenter() / 2)) {
							imagePass.setAlpha(1);
							if(xCord < (model.getScreenCenter() / 4)) {
								likes = 1;
							} else {
								likes = 0;
							}
						} else {
							likes = 0;
							imagePass.setAlpha(0);
						}
						
						imageLike.setAlpha(0);
					}
					break;
				case MotionEvent.ACTION_UP:
					model.stuffs();
					xCord = (int) event.getRawX();
					yCord = (int) event.getRawY();
					
					Log.e("X point", "" + xCord + " , Y " + yCord);
					imagePass.setAlpha(0);
					imageLike.setAlpha(0);
					
					if(likes == 0) {
						Log.e("Event status", "Nothing");
						layout.setX(40);
						layout.setY(40);
						layout.setRotation(0);
					} else if(likes == 1) {
						Log.e("Event status", "Passed");
						parentLayout.removeView(layout);
					} else if(likes == 2) {
						Log.e("Event status", "Liked");
						parentLayout.removeView(layout);
					}
					break;
				default:
					break;
				}
				return false;
			}
			
		});
		
		parentLayout.addView(layout);
	}
	
	/*
	public void setupMovement() {
		layout.setOnTouchListener(new OnTouchListener() {
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				xCord = (int) event.getRawX();
				yCord = (int) event.getRawY();
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					break;
				case MotionEvent.ACTION_MOVE:
					xCord = (int) event.getRawX();
					yCord = (int) event.getRawY();
					layout.setX(xCord - model.getScreenWidth() + 40);
					layout.setY(yCord - 150);
					if (xCord >= (model.getScreenWidth() / 2)) {
						layout.setRotation((float) (xCord - (model.getScreenWidth() / 2) * (Math.PI / 32)));
						if (xCord > ((3 * model.getScreenWidth()) / 2)) {
							System.out.println("change alpha");
							if(xCord > (model.getScreenWidth() / 2) - (model.getScreenWidth() / 8)) {
								System.out.println("Ska bort");
								likes = 2;
							} else {
								System.out.println("Ska inte bort");
								likes = 0;
							}
						} else {
							likes = 0;
							System.out.println("Ska inte bort, change alpha");
						}
						System.out.println("Change alpha");
					} else {
						layout.setRotation((float) ((xCord - (model.getScreenWidth() / 2)) * (Math.PI / 32)));
						if(xCord < (model.getScreenWidth() / 4)) {
							System.out.println("change alpha");
							if(xCord < model.getScreenWidth() / 8) {
								System.out.println("ska bort");
								likes = 2;
							} else {
								System.out.println("Ska inte bort");
								likes = 0;
							}
						} else {
							likes = 0;
							System.out.println("Ska inte bort, change alpha");
						}
						System.out.println("change alpha");
					}
					break;
				case MotionEvent.ACTION_UP:
					xCord = (int) event.getRawX();
					yCord = (int) event.getRawY();
					
					Log.e("X point", "" + xCord + " , Y " + yCord);
					System.out.println("change alpha");
					if(likes == 0) {
						Log.e("Event status", "Nothing");
						layout.setX(40);
						layout.setY(40);
						layout.setRotation(0);
					} else {
						Log.e("Event status", "Passed");
						parentLayout.removeView(layout);
					}
					break;
				default:
					break;
				}
				// TODO Auto-generated method stub
				return false;
			}
			
		});
	}*/
	/*

                    imageLike.setAlpha(0);
                }

                if (Likes == 0) {
                    Log.e("Event Status", "Nothing");
                    myRelView.setX(40);
                    myRelView.setY(40);
                    myRelView.setRotation(0);
                } else if (Likes == 1) {
                    Log.e("Event Status", "Passed");
                    parentView.removeView(myRelView);
                } else if (Likes == 2) {

                    Log.e("Event Status", "Liked");
                    parentView.removeView(myRelView);
                }
                break;
            default:
                break;
            }
            return true;
        }
*/
	/*
	public void newActivity() {
		layout.removeAllViews();
		
		Activities a = model.getActivity();
		btn = new Button(view.getContext());
		btn.setText(a.getName());
		btn.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
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
	}*/
	
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