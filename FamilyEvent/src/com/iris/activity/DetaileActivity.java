package com.iris.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.iris.familyevent.R;

public class DetaileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detaile);
		
		Intent intent = getIntent();
		String intentString =  intent.getStringExtra("value");
		Toast.makeText(getApplicationContext(), intentString, 1).show();
		
	}

}
