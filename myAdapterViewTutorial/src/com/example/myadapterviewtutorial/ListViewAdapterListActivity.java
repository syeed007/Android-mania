package com.example.myadapterviewtutorial;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



/**
 * this activity extends the list activity.
 * 
 * @author rajit
 *
 */
public class ListViewAdapterListActivity extends ListActivity {

	String [] mListItems;
	ArrayAdapter<String> mListAdapter;
	ListView mList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//no need to inflate the layout from xml as it extends the list list activity
		//setContentView(R.layout.activity_list_view_adapter_list);
		
		mListItems = getResources().getStringArray(R.array.colors);
		mListAdapter = new ArrayAdapter<String>(ListViewAdapterListActivity.this, R.layout.list_item, mListItems);
		
		
		//enables the text search option and add the adapter
		mList = getListView();
		
		mList.setAdapter(mListAdapter);
		mList.setTextFilterEnabled(true);
		
		this.setListListener();
	}

	
	private void setListListener(){
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String itemSelected = parent.getItemAtPosition(position).toString();
				
				Toast.makeText(ListViewAdapterListActivity.this, "Item selected: " + itemSelected, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_adapter_list, menu);
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
