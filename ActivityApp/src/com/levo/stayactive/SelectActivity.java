package com.levo.stayactive;

import android.app.Activity;
import android.os.Bundle;

import com.levo.activityapptest.R;
import com.levo.stayactive.model.Model;
import com.levo.stayactive.view.SelectController;
import com.levo.stayactive.view.SelectView;

public class SelectActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);;
		setContentView(R.layout.select_activity);
		Model model = ((StayActiveApplication) this.getApplication()).getModel();
		SelectView view = new SelectView(findViewById(R.id.select_activity), model);
		//SelectController controller = new SelectController(view, model);
	}

}
