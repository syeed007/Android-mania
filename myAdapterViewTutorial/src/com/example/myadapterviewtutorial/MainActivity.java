package com.example.myadapterviewtutorial;

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

	static String[] mAdapterViewList = null;
	static ArrayAdapter<String> mAdapterViewListAdapter = null;
	static ListView mListView = null;

	static final int LIST_VIEW = 0;
	static final int LIST_VIEW_LIST_ACTIVITY =1;
	static final int GALARY = 2;
	static final int SPINNER = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAdapterViewList = getResources().getStringArray(
				R.array.adapterViewGroup_types);

		mListView = (ListView) findViewById(R.id.adapter_view_list);

		mAdapterViewListAdapter = new ArrayAdapter<String>(MainActivity.this,
				R.layout.list_item_layout, mAdapterViewList);

		mListView.setAdapter(mAdapterViewListAdapter);

		this.setListItemSelectionListener();
	}

	private void setListItemSelectionListener() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent callActivityIntent = null;
				switch (position) {
				case LIST_VIEW:
					callActivityIntent = new Intent(MainActivity.this,
							ListViewAdapterActivity.class);
						Toast.makeText(getBaseContext(), "position: " + position + " NULL", Toast.LENGTH_LONG).show();
					startActivity(callActivityIntent);
					break;
				case LIST_VIEW_LIST_ACTIVITY:
					callActivityIntent = new Intent(MainActivity.this,
							ListViewAdapterListActivity.class);
						Toast.makeText(getBaseContext(), "position: " + position + " NULL", Toast.LENGTH_LONG).show();
					startActivity(callActivityIntent);
					break;
				case GALARY:
					callActivityIntent = new Intent(MainActivity.this,
							GalaryActivity.class);
						//Toast.makeText(getBaseContext(), "position: " + position + " NULL", Toast.LENGTH_LONG).show();
					startActivity(callActivityIntent);
					break;
				case SPINNER:
					callActivityIntent = new Intent(MainActivity.this, SpinnerAdapterActivity.class);
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
