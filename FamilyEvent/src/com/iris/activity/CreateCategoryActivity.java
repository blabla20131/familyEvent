package com.iris.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.iris.familyevent.R;

public class CreateCategoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		//팝업창 띄우기
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		getWindow().setAttributes(layoutParams);
		
		
		setContentView(R.layout.activity_create_category);
	}
}
