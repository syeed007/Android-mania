package com.example.mylayouttutorial;



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

	static String[] mLayoutType = null;
	static ArrayAdapter<String> mAdapterLayoutType = null;
	static ListView mListView = null;

	static final int LINEAR = 0;
	static final int GRID =1;
	static final int TABLE = 2;
	static final int RELATIVE = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mLayoutType = getResources().getStringArray(
				R.array.layoutTypes_array);

		mListView = (ListView) findViewById(R.id.layout_list);

		mAdapterLayoutType = new ArrayAdapter<String>(MainActivity.this,
				R.layout.list_item, mLayoutType);

		mListView.setAdapter(mAdapterLayoutType);

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
				case LINEAR:
					callActivityIntent = new Intent(MainActivity.this,
							LinearLayoutActivity.class);
						Toast.makeText(getBaseContext(), "position: " + position, Toast.LENGTH_LONG).show();
					startActivity(callActivityIntent);
					break;
				case GRID:
					callActivityIntent = new Intent(MainActivity.this,
							GridLayoutActivity.class);
						Toast.makeText(getBaseContext(), "position: " + position, Toast.LENGTH_LONG).show();
					startActivity(callActivityIntent);
					break;
				case TABLE:
					callActivityIntent = new Intent(MainActivity.this,
							TableLayoutActivity.class);
						Toast.makeText(getBaseContext(), "position: " + position, Toast.LENGTH_LONG).show();
					startActivity(callActivityIntent);
					break;
				case RELATIVE:
					callActivityIntent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
					Toast.makeText(getBaseContext(), "position: " + position, Toast.LENGTH_LONG).show();
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
