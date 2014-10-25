package com.example.myviewgrouptutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	static String[] mViewGroupList = null;
	static ArrayAdapter<String> mViewGroupListAdapter = null;
	static ListView mListView = null;

	static final int RadioGroup = 0;
	static final int TimePicker = 1;
	static final int DatePicker = 2;
	static final int WebView = 3;
	static final int MapView = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get the list items
		mViewGroupList = getResources().getStringArray(R.array.viewGroup_types);

		// get the list view
		mListView = (ListView) findViewById(R.id.view_group_list);

		// get the view adapter
		mViewGroupListAdapter = new ArrayAdapter<String>(MainActivity.this,
				R.layout.list_item_layout, mViewGroupList);

		// assign the adapter to the list
		mListView.setAdapter(mViewGroupListAdapter);

		this.addListenerToList();
	}

	private void addListenerToList() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"Selected item: " + mViewGroupList[position],
						Toast.LENGTH_SHORT).show();

				Intent callActivityIntent = null;
				switch (position) {
				case RadioGroup:
					callActivityIntent = new Intent(MainActivity.this, RadioGroupActivity.class);
					startActivity(callActivityIntent);
					break;
				case TimePicker:
					callActivityIntent = new Intent(MainActivity.this, TimePickerActivity.class);
					startActivity(callActivityIntent);
					break;
				case DatePicker:
					callActivityIntent = new Intent(MainActivity.this, DatePickerActivity.class);
					startActivity(callActivityIntent);
					break;
				case WebView:
					callActivityIntent = new Intent(MainActivity.this, WebViewActivity.class);
					startActivity(callActivityIntent);
					break;
				case MapView:
					callActivityIntent = new Intent(MainActivity.this, MapViewActivity.class);
					startActivity(callActivityIntent);
					break;
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
