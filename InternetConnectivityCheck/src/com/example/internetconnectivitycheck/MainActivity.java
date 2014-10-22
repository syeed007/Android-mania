package com.example.internetconnectivitycheck;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	// flag for Internet connection status
	Boolean isInternetPresent = false;

	// Connection detector class
	DetectConnectivity mDc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnStatus = (Button) findViewById(R.id.btn_check);
		final TextView showStatus = (TextView) findViewById(R.id.t_view);

		// creating connection detector class instance
		mDc = new DetectConnectivity(getApplicationContext());

		/**
		 * Check Internet status button click event
		 * */
		btnStatus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// get Internet status
				isInternetPresent = mDc.isConnectingToInternet();

				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present
					// make HTTP requests
					showStatus.setText("Internet Connection is ok!!");

				} else {
					// Internet connection is not present
					// Ask user to connect to Internet
					showStatus.setText("No Connection! pls reconnect..");

				}
			}

		});

	}

	
}