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

public class ListViewAdapterActivity extends ActionBarActivity {

	static String[] mAdapterViewList = null;
	static ArrayAdapter<String> mAdapterViewListAdapter = null;
	static ListView mListView = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAdapterViewList = getResources().getStringArray(
				R.array.colors);

		mListView = (ListView) findViewById(R.id.adapter_view_list);

		mAdapterViewListAdapter = new ArrayAdapter<String>(ListViewAdapterActivity.this,
				R.layout.list_item_layout, mAdapterViewList);

		mListView.setAdapter(mAdapterViewListAdapter);

		mListView.setTextFilterEnabled(true);
		
		this.setListItemSelectionListener();
	}

	private void setListItemSelectionListener() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// get the selected item and display it.
				String mItemSelected = parent.getItemAtPosition(position).toString();
				Toast.makeText(ListViewAdapterActivity.this, "List item: " + mItemSelected, Toast.LENGTH_SHORT).show();
			
				
				
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
	
	/**
	private ListView mlistView;
	private String[] colors;
	private ArrayAdapter<String> mListViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_adapter);

		// get the list view
		mlistView = (ListView) findViewById(R.id.adapter_view_list);

		// get the array of data for the list view to populate
		colors = getResources().getStringArray(R.array.colors);

		// create an string array adapter (its a default adapter with basic data
		// type)
		// in a later example we will create a custom list adapter..
		mListViewAdapter = new ArrayAdapter<String>(
				ListViewAdapterActivity.this,
				R.layout.list_item_layout, colors);
		
		//add the adapter to the list
		mlistView.setAdapter(mListViewAdapter);
		
		//add item click listener
		this.setListItemSelectionListener();
	}

	
	
	*/
}
