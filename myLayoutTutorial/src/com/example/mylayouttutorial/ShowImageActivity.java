package com.example.mylayouttutorial;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class ShowImageActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Intent getIntent = getIntent();
		ImageView imageView = new ImageView(getApplicationContext());
		
		Integer imageId = getIntent.getIntExtra(GridLayoutActivity.EXTRA_RES_ID, 0);
		
		
		
		imageView.setImageResource(imageId);
		
		setContentView(imageView);
		/*
		// Get the Intent used to start this Activity
				Intent intent = getIntent();
				
				// Make a new ImageView
				ImageView imageView = new ImageView(getApplicationContext());
				
				// Get the ID of the image to display and set it as the image for this ImageView
				imageView.setImageResource(intent.getIntExtra(GridLayoutActivity.EXTRA_RES_ID, 0));
				
				setContentView(imageView);
				*/
	}

	
}
