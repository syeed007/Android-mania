package mahbubul.syeed.mynotificationtutorial;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	private String[] mListItem;
	private ArrayAdapter<String> mListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		mListItem = getResources().getStringArray(R.array.notification_array);
		mListAdapter = new ArrayAdapter<String>(MainActivity.this,
				R.layout.list_item, mListItem);

		getListView().setAdapter(mListAdapter);

		this.setListSelectionListener();
	}

	private void setListSelectionListener() {
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent mIntent = null;
				
				switch (position) {
				case 0:
					mIntent = new Intent(MainActivity.this, ToastActivity.class);
					startActivity(mIntent);
					break;
				case 1:
					mIntent = new Intent(MainActivity.this, NotificationActivity.class);
					startActivity(mIntent);
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
