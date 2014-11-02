package mahbubul.syeed.mythreadtest;

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

	private String [] mList;
	private ArrayAdapter<String> mAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        mList = getResources().getStringArray(R.array.thread_types);
        mAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item_layout, mList);
        getListView().setAdapter(mAdapter);
        
        this.setListener();
    }


    private void setListener(){
    	getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(position){
				case 0:
					Intent mInt = new Intent(MainActivity.this, NoThreadActivity.class);
					startActivity(mInt);
					break;
				case 1:
					Intent mInts = new Intent(MainActivity.this, ThreadActivity.class);
					startActivity(mInts);
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
