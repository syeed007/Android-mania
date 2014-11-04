package mahbubul.syeed.mylistviewfunctionalitytest;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
			"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
			"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
			"OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
			"Android", "iPhone", "WindowsMobile" };
	
	//variables for default array adapter test
	final ArrayList<String> list = new ArrayList<String>();
	ArrayAdapter<String> adapter; 
	
	//variables for custom array adapter test
	CustomArrayAdapter  mCustomAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		/**
		 * Testing the default array adapter
		 */
		//this.testDefaultArrayAdapter();
		
		/**
		 * Testing the Customized array adapter for custom layout
		 */
		this.testCustomArrayAdapter();

		/**
		 * set click and long click listener
		 */
		this.setOnClickListener();
		//this.setOnLongClickListener();
		this.setOnLongClickListenerForCustomArrayAdapter();
		
		
	}

	
	/**
	 * Default array adapter test
	 */
	private void testDefaultArrayAdapter(){
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}		
		adapter = new ArrayAdapter<String>(
				MainActivity.this, R.layout.listitem_layout, list);
		
		getListView().setAdapter(adapter);
	}
	
	/**
	 * Custom array adapter test
	 */
	private void testCustomArrayAdapter(){
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}	
		mCustomAdapter = new CustomArrayAdapter(
				MainActivity.this, list);
		
		getListView().setAdapter(mCustomAdapter);
		
		//getListView().setEmptyView(findViewById(R.id.em));
	}
	
	
	
	
	/**
	 * show a toast for clicking the item
	 */
	private void setOnClickListener() {
		getListView().setOnItemClickListener(
				new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							final View view, int position, long id) {
						final String item = (String) parent
								.getItemAtPosition(position);
						Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
					}

				});
	}

	
	/**
	 * delete item on long press on that list item.
	 */
	private void setOnLongClickListener() {
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, final View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final String item = (String) parent
						.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0)
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								list.remove(item);
								adapter.notifyDataSetChanged();
								view.setAlpha(1);
							}
						});
				return false;
			}
		});
	}
	
	
	/**
	 * delete item on long press on that list item.
	 */
	private void setOnLongClickListenerForCustomArrayAdapter() {
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, final View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final String item = (String) parent
						.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0)
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								list.remove(item);
								mCustomAdapter.notifyDataSetChanged();
								view.setAlpha(1);
							}
						});
				return false;
			}
		});
	}
}
