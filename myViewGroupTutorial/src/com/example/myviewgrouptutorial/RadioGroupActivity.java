package com.example.myviewgrouptutorial;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioGroupActivity extends ActionBarActivity {

	RadioButton mRadio1 = null;
	RadioButton mRadio2 = null;
	RadioButton mRadio3 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio_group);

		// get the radio buttons.
		mRadio1 = (RadioButton) findViewById(R.id.choice1);
		mRadio2 = (RadioButton) findViewById(R.id.choice2);
		mRadio3 = (RadioButton) findViewById(R.id.choice3);

		// define a generic onclick listener for all radio buttons
		final OnClickListener radioSelectionListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton) v;
				Toast.makeText(RadioGroupActivity.this,
						"Your Selection: " + rb.getText(), Toast.LENGTH_SHORT)
						.show();

			}
		};
		
		//set the selection listener to all the radio buttons.
		mRadio1.setOnClickListener(radioSelectionListener);
		mRadio2.setOnClickListener(radioSelectionListener);
		mRadio3.setOnClickListener(radioSelectionListener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.radio_group, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
