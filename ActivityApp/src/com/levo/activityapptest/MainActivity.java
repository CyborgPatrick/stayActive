package com.levo.activityapptest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.levo.stayactive.model.Activities;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
int windowwidth;
int windowheight;
int screenCenter;
int x_cord, y_cord;
int Likes = 0;
LinearLayout parentView;
ArrayList<Activities> activities = new ArrayList<Activities>();
int counter;
Activities activity;
float alphaValue = 0;
LinearLayout buttonsLayout;
int[] myImageList;
Random random = new Random();
RelativeLayout parentLayout;
TextView website;
AlertDialog dialog;
boolean helper = true;
int xTemp = 0;
boolean viewDecider = true;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initiateDatabase();
    setActivity();
    website = new TextView(this);
    parentView = (LinearLayout) findViewById(R.id.layoutview);
    windowwidth = getWindowManager().getDefaultDisplay().getWidth();
    windowheight = getWindowManager().getDefaultDisplay().getHeight();
    topView();
    screenCenter = windowwidth / 2;
    myImageList = new int[] { R.drawable.event1, R.drawable.event2,
            R.drawable.event3, R.drawable.event4, R.drawable.event5,
            R.drawable.event6 };
    	parentLayout = new RelativeLayout(this);
    	parentLayout.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));
    	parentView.addView(parentLayout);
    	buttons();
    	stuffs();
	}

	public void topView() {
		RelativeLayout topLayout = new RelativeLayout(this);
		topLayout.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, (windowheight / 12)));
		topLayout.setBackgroundColor(Color.parseColor("#00ff00"));
		parentView.addView(topLayout);
	}

	public void setActivity() {
		if(counter == activities.size()) {
			counter = 0;
			Collections.shuffle(activities);
		}
		activity = activities.get(counter++);
	}

	private void initiateDatabase() {
		String s;
		String t = "https://www.google.com";
		for(int i = 1; i <= 6; i++) {
			String pictureName = "#" + (100000 + random.nextInt(99999));
			s = "activity" + i;
			activities.add(new Activities(s, t, pictureName));
		}
	}

	public void buttons() {
		buttonsLayout = new LinearLayout(this);
		buttonsLayout.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
		buttonsLayout.setGravity(Gravity.CENTER);
		buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);
		parentView.addView(buttonsLayout);
		
		LinearLayout yesButtonLayout = new LinearLayout(this);
		yesButtonLayout.setLayoutParams(new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT, 1));
		yesButtonLayout.setGravity(Gravity.CENTER);
		yesButtonLayout.setBackgroundColor(Color.parseColor("#000000"));
		yesButtonLayout.setClickable(true);
		yesButtonLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("yes");
			}
		});
		buttonsLayout.addView(yesButtonLayout);
		
		LinearLayout noButtonLayout = new LinearLayout(this);
		noButtonLayout.setLayoutParams(new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT, 1));
		noButtonLayout.setGravity(Gravity.CENTER);
		noButtonLayout.setBackgroundColor(Color.parseColor("#ffffff"));
		noButtonLayout.setClickable(true);
		noButtonLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("no");
				stuffs();
			}
		});
		buttonsLayout.addView(noButtonLayout);
	}

	public void stuffs() {
		parentLayout.removeAllViews();
		setActivity();
		website.setText(activity.getName());
		website.setClickable(true);
		website.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent browserIntent_toUpplysingar = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
				startActivity(browserIntent_toUpplysingar);
			}
		});
		final RelativeLayout myRelView = new RelativeLayout(this);
        myRelView
                .setLayoutParams(new LayoutParams((windowwidth - 80), (windowwidth - 80)));
        myRelView.setX(40);
        myRelView.setY(40);
        myRelView.setBackgroundResource(myImageList[1]);
        myRelView.setBackgroundColor(Color.parseColor(activity.getPicture()));
        final Button imageLike = new Button(this);
        imageLike.setLayoutParams(new LayoutParams(100, 50));
        imageLike.setBackground(getResources().getDrawable(R.drawable.yes));
        imageLike.setX(20);
        imageLike.setY(80);
        imageLike.setAlpha(alphaValue);
        myRelView.addView(imageLike);
        final Button imagePass = new Button(this);
        imagePass.setLayoutParams(new LayoutParams(100, 50));
        imagePass.setBackground(getResources().getDrawable(R.drawable.no));
        imagePass.setX((windowwidth - 200));
        imagePass.setY(100);
        imagePass.setRotation(45);
        imagePass.setAlpha(alphaValue);
        myRelView.addView(imagePass);
        final LinearLayout informationLayout = new LinearLayout(this);
        final LinearLayout moreInfoLayout = new LinearLayout(this);
        myRelView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
        		x_cord = (int) event.getRawX();
        		y_cord = (int) event.getRawY();
            	if(viewDecider) {
            		switch (event.getAction()) {
            		case MotionEvent.ACTION_DOWN:
            			
            			xTemp = x_cord;
            			break;
            		case MotionEvent.ACTION_MOVE:
            			x_cord = (int) event.getRawX();
            			y_cord = (int) event.getRawY();
            			if(x_cord > xTemp) {
	                    	float xInit = xTemp;
	                    	float xNow = x_cord;
	                    	float screen = screenCenter;
	                    	alphaValue = (xNow - xInit) / screen;
	                    	imageLike.setAlpha(alphaValue);
            			} else if(xTemp > x_cord) {
            				float xInit = xTemp;
            				float xNow = x_cord;
            				float screen = screenCenter;
            				alphaValue = (xInit - xNow) / screen;
            				imagePass.setAlpha(alphaValue);
            			}
                    
            			if(xTemp - 5 < x_cord && x_cord < xTemp + 5) {
            				break;
            			} else {
            				myRelView.setX(x_cord - screenCenter + 40);
            				if (x_cord > (screenCenter + (screenCenter / 2))) {
            					myRelView
            					.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
            					if (x_cord > xTemp) {
            						if (alphaValue > 0.9) {
            							Likes = 2;
            						} else {
            							Likes = 0;
            						}
            					} else {
            						Likes = 0;
            					}
            				} else {
            					myRelView
                                .setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
            					if (x_cord < (screenCenter / 2)) {
            						if (alphaValue > 0.9) {
            							Likes = 1;
            						} else {
            							Likes = 0;
            						}
            					} else {
            						Likes = 0;
            					}
            				}
            				break;
            			}
                	
            		case MotionEvent.ACTION_UP:
            			x_cord = (int) event.getRawX();
            			y_cord = (int) event.getRawY();
            			Log.e("X Point", "" + x_cord + " , Y " + y_cord);
            			if(xTemp - 5 < x_cord && x_cord < xTemp + 5) {
            				//information();
            				parentView.removeView(buttonsLayout);
            		        informationLayout.addView(moreInfoLayout);
            				viewDecider = false;
            			}
            			alphaValue = 0;
            			imagePass.setAlpha(alphaValue);
            			imageLike.setAlpha(alphaValue);
            			if (Likes == 0) {
            				Log.e("Event Status", "Nothing");
            				myRelView.setX(40);
            				myRelView.setY(40);
            				myRelView.setRotation(0);
            			} else if (Likes == 1) {
            				Log.e("Event Status", "Passed");
            				parentLayout.removeView(myRelView);
            				stuffs();
            			} else if (Likes == 2) {

            				Log.e("Event Status", "Liked");
            				parentLayout.removeView(myRelView);
            				stuffs();
            			}
            			break;
            		default:
            			break;
            		}
            		return true;
            	} else {
            		switch(event.getAction()) {
            		case MotionEvent.ACTION_DOWN:
            			xTemp = x_cord;
            			System.out.println(xTemp + " " + x_cord);
            			break;
            		case MotionEvent.ACTION_MOVE:
            			x_cord = (int) event.getRawX();
            			y_cord = (int) event.getRawY();
            			if(xTemp - 5 < x_cord && x_cord < xTemp + 5) {
            				break;
            			} else {
            				myRelView.setX(x_cord - screenCenter + 40);
            				if (x_cord > (screenCenter + (screenCenter / 2))) {
            					/*
            					if (x_cord > xTemp) {
            						if (alphaValue > 0.9) {
            							Likes = 2;
            						} else {
            							Likes = 0;
            						}
            					} else {
            						Likes = 0;
            					}*/
            				} else {
            					/*
            					if (x_cord < (screenCenter / 2)) {
            						if (alphaValue > 0.9) {
            							Likes = 1;
            						} else {
            							Likes = 0;
            						}
            					} else {
            						Likes = 0;
            					}*/
            				}
            				break;
            			}
            		case MotionEvent.ACTION_UP:
            			x_cord = (int) event.getRawX();
            			y_cord = (int) event.getRawY();
            			System.out.println(xTemp + " " + x_cord);
            			if(xTemp - 10 < x_cord && x_cord < xTemp + 10) {
            				//information();
            				parentView.addView(buttonsLayout);
            		        informationLayout.removeView(moreInfoLayout);
            				viewDecider = true;
            			}
            			if(x_cord > (windowwidth - ((screenCenter / 4)))) {
            				System.out.println("next picture");
            				stuffs();
            			} else if (x_cord < screenCenter / 4) {
            				System.out.println("previous picture");
            				stuffs();
            			} else {
            				myRelView.setX(40);
            				
            			}
        				break;
            		}
            		return true;
            		
            		/*
            			alphaValue = 0;
            			imagePass.setAlpha(alphaValue);
            			imageLike.setAlpha(alphaValue);
            			if (Likes == 0) {
            				Log.e("Event Status", "Nothing");
            				myRelView.setX(40);
            				myRelView.setY(40);
            				myRelView.setRotation(0);
            			} else if (Likes == 1) {
            				Log.e("Event Status", "Passed");
            				parentLayout.removeView(myRelView);
            				stuffs();
            			} else if (Likes == 2) {

            				Log.e("Event Status", "Liked");
            				parentLayout.removeView(myRelView);
            				stuffs();
            			}
            			break;*/
            	}
            }
        });
        parentLayout.addView(myRelView);
        
        
        informationLayout.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,(windowheight - windowwidth)));
        informationLayout.setOrientation(LinearLayout.VERTICAL);
        

        LinearLayout titleLayout = new LinearLayout(this);
        titleLayout.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,(windowheight / 12)));
        titleLayout.setOrientation(LinearLayout.HORIZONTAL);

        LayoutParams lp = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT, 1);
        LinearLayout nameLayout = new LinearLayout(this);
        nameLayout.setLayoutParams(lp);
        nameLayout.setGravity(Gravity.CENTER);
        TextView nameView = new TextView(this);
        nameView.setText(activity.getName());
        nameLayout.addView(nameView);
        titleLayout.addView(nameLayout);
        
        LinearLayout typeLayout = new LinearLayout(this);
        typeLayout.setLayoutParams(lp);
        typeLayout.setGravity(Gravity.CENTER);
        TextView typeView = new TextView(this);
        typeView.setText(activity.getType());
        typeLayout.addView(typeView);
        titleLayout.addView(typeLayout);
        
        LinearLayout distanceLayout = new LinearLayout(this);
        distanceLayout.setLayoutParams(lp);
        distanceLayout.setGravity(Gravity.CENTER);
        TextView distanceView = new TextView(this);
        distanceView.setText("500 m");
        distanceLayout.addView(distanceView);
        titleLayout.addView(distanceLayout);
        
        parentLayout.addView(informationLayout);
        informationLayout.addView(titleLayout);
        informationLayout.setY(windowwidth);
        
        moreInfoLayout.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        moreInfoLayout.setOrientation(LinearLayout.HORIZONTAL);
        
        LinearLayout textLayout = new LinearLayout(this);
        textLayout.setLayoutParams(lp);
        moreInfoLayout.addView(textLayout);
        
        TextView information = new TextView(this);
        information.setPadding(5, 0, 5, 0);
        information.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volu");
        textLayout.addView(information);
        
        LinearLayout placeholder = new LinearLayout(this);
        placeholder.setLayoutParams(lp);
        placeholder.setOrientation(LinearLayout.VERTICAL);
        moreInfoLayout.addView(placeholder);
        
        LinearLayout locationLayout = new LinearLayout(this);
        locationLayout.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        locationLayout.setPadding(0, 10, 0, 10);
        locationLayout.setGravity(Gravity.CENTER);
        placeholder.addView(locationLayout);
        
        TextView location = new TextView(this);
        location.setText("Reykjavik");
        locationLayout.addView(location);
        
        LinearLayout webLayout = new LinearLayout(this);
        webLayout.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        webLayout.setPadding(0, 10, 0, 10);
        webLayout.setGravity(Gravity.CENTER);
        placeholder.addView(webLayout);
        
        TextView website = new TextView(this);
        website.setText(activity.getWebsite());
        webLayout.addView(website);
        if(!viewDecider) {
        	informationLayout.addView(moreInfoLayout);
        }
	}
}