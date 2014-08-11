package com.levo.activityapptest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
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

import com.levo.stayactive.model.Activities;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
int windowwidth;
int screenCenter;
int x_cord, y_cord;
int Likes = 0;
LinearLayout parentView;
ArrayList<Activities> activities = new ArrayList<Activities>();
int counter;
Activities activity;
float alphaValue = 0;
int[] myImageList;
Random random = new Random();
RelativeLayout parentLayout;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initiateDatabase();
    setActivity();
    parentView = (LinearLayout) findViewById(R.id.layoutview);
    windowwidth = getWindowManager().getDefaultDisplay().getWidth();
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
		LinearLayout buttonsLayout = new LinearLayout(this);
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
		final RelativeLayout myRelView = new RelativeLayout(this);
        myRelView
                .setLayoutParams(new LayoutParams((windowwidth - 80), (windowwidth - 80)));
        myRelView.setX(40);
        myRelView.setY(40);
        myRelView.setBackgroundResource(myImageList[1]);
        System.out.println(activity.getPicture());
        myRelView.setBackgroundColor(Color.parseColor(activity.getPicture()));
        final Button imageLike = new Button(this);
        imageLike.setLayoutParams(new LayoutParams(100, 50));
        imageLike.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.yes));
        imageLike.setX(20);
        imageLike.setY(80);
        imageLike.setAlpha(alphaValue);
        myRelView.addView(imageLike);
        final Button imagePass = new Button(this);
        imagePass.setLayoutParams(new LayoutParams(100, 50));
        imagePass.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.no));
        imagePass.setX((windowwidth - 200));
        imagePass.setY(100);
        imagePass.setRotation(45);
        imagePass.setAlpha(alphaValue);
        myRelView.setClickable(true);
        myRelView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("hej");
			}
        	
        });
        myRelView.addView(imagePass);
        myRelView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x_cord = (int) event.getRawX();
                y_cord = (int) event.getRawY();
                myRelView.setX(x_cord - screenCenter + 40);
                //myRelView.setY(y_cord - (windowwidth - 160));
                switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	System.out.println("hej");
                	return false;
                case MotionEvent.ACTION_MOVE:
                    x_cord = (int) event.getRawX();
                    y_cord = (int) event.getRawY();
                    myRelView.setX(x_cord - screenCenter + 40);
                   // myRelView.setY(y_cord - (windowwidth - 160));
                    if (x_cord >= screenCenter) {
                        myRelView
                                .setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                        if (x_cord > (screenCenter + (screenCenter / 2))) {
                            imageLike.setAlpha(1);
                            if (x_cord > (windowwidth - (screenCenter / 4))) {
                                Likes = 2;
                            } else {
                                Likes = 0;
                            }
                        } else {
                            Likes = 0;
                            imageLike.setAlpha(0);
                        }
                        imagePass.setAlpha(0);
                    } else {
                        // rotate
                        myRelView
                                .setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                        if (x_cord < (screenCenter / 2)) {
                            imagePass.setAlpha(1);
                            if (x_cord < screenCenter / 4) {
                                Likes = 1;
                            } else {
                                Likes = 0;
                            }
                        } else {
                            Likes = 0;
                            imagePass.setAlpha(0);
                        }
                        imageLike.setAlpha(0);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    x_cord = (int) event.getRawX();
                    y_cord = (int) event.getRawY();
                    Log.e("X Point", "" + x_cord + " , Y " + y_cord);
                    imagePass.setAlpha(0);
                    imageLike.setAlpha(0);
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
            }
        });
        parentLayout.addView(myRelView);
	}
}