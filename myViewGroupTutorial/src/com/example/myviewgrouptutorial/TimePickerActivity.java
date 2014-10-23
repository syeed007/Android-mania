package com.example.myviewgrouptutorial;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePickerActivity extends Activity {

	private TextView mTimeTxt;
	private Button mTimePickerBtn;
	
	private int mMin;
	private int mSec;
	private int mHor;
	
	final int DIALOG_TIME_PICKER = 1; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_picker);
		
		//get the text view and the button.
		//text view displays the time
		//the button popups the time picker dialog
		mTimeTxt = (TextView) findViewById(R.id.text_time_display);
		mTimePickerBtn = (Button) findViewById(R.id.btn_time_picker);
		
		//get the current time from the calender
		Calendar mCalender = Calendar.getInstance();
		mSec = mCalender.get(Calendar.SECOND);
		mMin = mCalender.get(Calendar.MINUTE);
		mHor = mCalender.get(Calendar.HOUR_OF_DAY);
		
		//show the current time in the text box and in a toast
		this.updateDisplay();
		
		//add a click listerner to the button.
		this.setListenerToTimePickerBtn();
	}

	
	private void updateDisplay(){
		StringBuilder timeFormatted = new StringBuilder().append(mHor).append("-").append(mMin).append("-").append(mSec);
		mTimeTxt.setText(timeFormatted);
		Toast.makeText(TimePickerActivity.this, "Time: " + timeFormatted, Toast.LENGTH_LONG).show();
	}
	
	
	private void setListenerToTimePickerBtn(){
		mTimePickerBtn.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// Clicking the button fires a general dialog, which in turn opens the time picker
				//based on the selection int.
				showDialog(DIALOG_TIME_PICKER);
			}
		});
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		// based on the id, this method fires appropriate dialog.
		switch(id){
		case DIALOG_TIME_PICKER:
			return new TimePickerDialog(TimePickerActivity.this, timeSetListener, mHor, mMin, false);
		}
		return null;
	}
	
	private TimePickerDialog.OnTimeSetListener timeSetListener = new OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			mHor = hourOfDay;
			mMin = minute;
			updateDisplay();
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_picker, menu);
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
