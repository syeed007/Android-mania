package com.example.myviewgrouptutorial;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerActivity extends ActionBarActivity {

	private TextView mShowDateTxt = null;
	private Button mDatePickBtn = null;
	private int mDAY;
	private int mMONTH;
	private int mYEAR;

	final int DATE_PICKER_DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker);

		// get the text and button
		mShowDateTxt = (TextView) findViewById(R.id.text_show_date);
		mDatePickBtn = (Button) findViewById(R.id.btn_date_picker);

		// get the current date from the system.
		Calendar mCalender = Calendar.getInstance();
		mDAY = mCalender.get(Calendar.DAY_OF_MONTH);
		mMONTH = mCalender.get(Calendar.MONTH);
		mYEAR = mCalender.get(Calendar.YEAR);

		// update the text view with the current date
		this.updateTextView();

		// add listener to the button. This listener will call a generic dialog
		// box
		// which in turn calls the date picker dialog.
		this.setListenerToBtn();
	}

	private void updateTextView() {
		// format the date with string builder
		// add 1 to month as it starts from 0
		StringBuilder formattedDate = new StringBuilder().append(mDAY)
				.append("-").append(mMONTH + 1).append("-").append(mYEAR);
		mShowDateTxt.setText(formattedDate);
	}

	private void setListenerToBtn() {
		mDatePickBtn.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// invoke the dialog
				showDialog(DATE_PICKER_DIALOG_ID);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		//if the call is for a date picker then open the date picker dialog
		case DATE_PICKER_DIALOG_ID:
			return new DatePickerDialog(DatePickerActivity.this, mDateSetListener, mYEAR, mMONTH, mDAY);
		}
		return null;
	}

	// The callback received when the user "sets" the date in the Dialog
		private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mYEAR = year;
				mMONTH = monthOfYear;
				mDAY = dayOfMonth;
				updateTextView();
			}
		};
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_picker, menu);
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
