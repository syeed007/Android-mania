package com.example.myadapterviewtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerAdapterActivity extends ActionBarActivity {

	private Spinner mSpin;
	private String[] mSpinContent;
	private ArrayAdapter<String> mSpinAdapter;
	private RelativeLayout parentLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner_adapter);

		//get the parent layout. it will be used to change color based on spinner selection
		parentLayout = (RelativeLayout) findViewById(R.id.spinner_parent_id);

		//
		mSpinContent = getResources().getStringArray(R.array.colors);

		mSpin = (Spinner) findViewById(R.id.spinner_color);

		mSpinAdapter = new ArrayAdapter<String>(SpinnerAdapterActivity.this,
				R.layout.spinner_item_layout, mSpinContent);

		mSpin.setAdapter(mSpinAdapter);

		this.setSelectionListener();

	}

	private void setSelectionListener() {
		mSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String itemSelected = parent.getItemAtPosition(position)
						.toString();
				Toast.makeText(SpinnerAdapterActivity.this,
						"The color is " + itemSelected, Toast.LENGTH_LONG)
						.show();

				//change background color based on selected color. not work for all
				parentLayout.setBackgroundColor(Color.RED);

				if (itemSelected.equalsIgnoreCase("orange"))
					parentLayout.setBackgroundColor(Color.GRAY);
				if (itemSelected.equalsIgnoreCase("yellow"))
					parentLayout.setBackgroundColor(Color.YELLOW);
				if (itemSelected.equalsIgnoreCase("green"))
					parentLayout.setBackgroundColor(Color.GREEN);
				if (itemSelected.equalsIgnoreCase("blue"))
					parentLayout.setBackgroundColor(Color.BLUE);
				// String itemSelected = parent.getItemIdAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spinner_adapter, menu);
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
